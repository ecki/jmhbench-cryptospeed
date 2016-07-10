package net.eckenfels.jmhtest.cryptospeed;


import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(value=Threads.MAX)
@Fork(1)
@State(Scope.Thread)
public class AESGCMBenchmark
{

    @Param(value = {"0", "100", "1024", "1048576"})
    int bufsize;

    // thread local buffer to work on
    public byte[] inputBuf;

    // inputBuf encrypted
    public byte[] encBuf;


    // the password
    String pwd = "seeburger";

    // used for encryption
    SecretKeySpec secretKeyAlice;

    // used for decryption
    SecretKeySpec secretKeyBob;

    SecureRandom sr;


    @Setup
    public void init()
        throws Exception
    {

        sr = SecureRandom.getInstance("SHA1PRNG");
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "SunEC");
        ECGenParameterSpec ecsp = new ECGenParameterSpec("secp256r1");
        kpg.initialize(ecsp, sr);

        // ----- Alice -----

        // -- Alice Generates a static ECDSA Key Pair and Sends her Public Key (dsaPbAlice) to Bob.
        // -- Alice should securely store her ECDSA Private Key on disk using symmetric encryption.
        KeyPair dsaAlice = kpg.genKeyPair();
        byte[] dsaPbAlice = dsaAlice.getPublic().getEncoded();

        String dsaPfAlice = String.format("%064x", new BigInteger(1, sha256.digest(dsaPbAlice)));
//        System.out.println("Alice's Public ECDSA SHA-256 Hash: " + dsaPfAlice);

        // -- Bob recovers Alice's ECDSA Public Key and Verfies SHA-256 Hash Offline
        // -- Once verified, Bob should store this verified key (dsaPbAlice) for future authentication.
        PublicKey dsaPkAlice = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(dsaPbAlice));

        // ----- Bob -----

        // -- Bob Generates a static ECDSA Key Pair and Sends his Public Key (dsaPbBob) to Alice.
        // -- Bob should securely store his ECDSA Private Key on disk using symmetric encryption.
        KeyPair dsaBob = kpg.genKeyPair();
        byte[] dsaPbBob = dsaBob.getPublic().getEncoded();

        String dsaPfBob = String.format("%064x", new BigInteger(1, sha256.digest(dsaPbBob)));
//        System.out.println("Bob's Public ECDSA SHA-256 Hash: " + dsaPfBob);

        // -- Alice recovers Bob's ECDSA Public Key and Verfies SHA-256 Hash Offline
        // -- Once verified, Alice should store this verified key (dsaPbBob) for future authentication.
        PublicKey dsaPkBob = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(dsaPbBob));

        // --- Now Alice and Bob want to have a secure conversation

        // ----- Alice -----

        // -- Alice Generates an ephemeral ECDH Key Pair
        KeyPair dhAlice = kpg.genKeyPair();
        byte[] dhPbAlice = dhAlice.getPublic().getEncoded();

        // -- Alice Signs her ephemeral ECDH Public Key with her static ECDSA Private Key
        // -- Alice sends Bob her ECDH Public Key (dhPbAlice) with the ECDSA Signature (dhSbAlice)
        Signature sAlice = Signature.getInstance("SHA256withECDSA", "SunEC");
        sAlice.initSign(dsaAlice.getPrivate());
        sAlice.update(dhPbAlice);
        byte[] dhSbAlice = sAlice.sign();

        // ----- Bob -----

        // -- Bob Generates an ephemeral ECDH Key Pair
        KeyPair dhBob = kpg.genKeyPair();
        byte[] dhPbBob = dhBob.getPublic().getEncoded();

        // -- Bob Signs his ephemeral ECDH Public Key with his static ECDSA Private Key
        // -- Bob sends Alice his ECDH Public Key (dhPbBob) with the ECDSA Signature (dhSbBob)
        Signature sBob = Signature.getInstance("SHA256withECDSA", "SunEC");
        sBob.initSign(dsaBob.getPrivate());
        sBob.update(dhPbBob);
        byte[] dhSbBob = sBob.sign();

        // ----- Alice -----

        // --- Alice Verifies Bob's Public Key (dhPbBob) and Signature (dhSbBob) using Bob's trusted ECDSA Public Key (dsaPkBob)
        Signature vsBob = Signature.getInstance("SHA256withECDSA", "SunEC");
        vsBob.initVerify(dsaPkBob);
        vsBob.update(dhPbBob);

        if (!vsBob.verify(dhSbBob))
        {
          throw new Exception("Alice can't verify signature of Bob's ECDH Public Key");
        }

        // ----- Bob -----

        // --- Bob Verifies Alice's Public Key (dhPbAlice) and Signature (dhSbAlice) using Alice's trusted ECDSA Public Key (dsaPkAlice)
        Signature vsAlice = Signature.getInstance("SHA256withECDSA", "SunEC");
        vsAlice.initVerify(dsaPkAlice);
        vsAlice.update(dhPbAlice);

        if (!vsAlice.verify(dhSbAlice))
        {
          throw new Exception("Bob can't verify signature of Alice's ECDH Public Key");
        }

        // ----- Alice -----

        // -- Alice Generates Secret Key (skAlice) using Alice's Private and Bob's Verified Public (dhPbBob)
        KeyAgreement kaAlice = KeyAgreement.getInstance("ECDH");
        // -- Convert received byte array back into Bob's Public Key
        PublicKey dhPkBob = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(dhPbBob));
        kaAlice.init(dhAlice.getPrivate());
        kaAlice.doPhase(dhPkBob, true);
        // -- Alice uses the first 16 bytes of the SHA-256 hash of the 256-bit secret shared key.
        byte[] skAlice = Arrays.copyOfRange(sha256.digest(kaAlice.generateSecret()), 0, 16);
        secretKeyAlice = new SecretKeySpec(skAlice, "AES");

        // ----- Bob -----

        // -- Bob Generates Secret Key (skBob) using Bob's Private and Alice's Verified Public (dhPbAlice)
        KeyAgreement kaBob = KeyAgreement.getInstance("ECDH");
        // -- Convert received byte array back into Alice's Public Key
        PublicKey dhPkAlice = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(dhPbAlice));
        kaBob.init(dhBob.getPrivate());
        kaBob.doPhase(dhPkAlice, true);
        // -- Bob uses the first 16 bytes of the SHA-256 hash of the 256-bit secret shared key.
        byte[] skBob = Arrays.copyOfRange(sha256.digest(kaBob.generateSecret()), 0, 16);
        secretKeyBob = new SecretKeySpec(skBob, "AES");


        Random r = new Random(42);
        inputBuf = new byte[bufsize];
        r.nextBytes(inputBuf);


        // prepare the samples for the decrypt
        encBuf = encrypt();

    }




    @Benchmark
    public byte[] encrypt()
        throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        // Generate 128 bit IV for Encryption
        byte[] iv = new byte[12]; sr.nextBytes(iv);

        Cipher c = Cipher.getInstance("AES/GCM/PKCS5Padding");

        // Generated Authentication Tag should be 128 bits
        c.init(Cipher.ENCRYPT_MODE, secretKeyAlice, new GCMParameterSpec(128, iv));
        byte[] es = c.doFinal(inputBuf);

        // Construct Output as "IV + CIPHERTEXT"
        byte[] output = new byte[12 + es.length];
        System.arraycopy(iv, 0, output, 0, 12);
        System.arraycopy(es, 0, output, 12, es.length);

        // Return a Base64 Encoded String
        return output;
    }

    @Benchmark
    public void decrypt()
        throws Exception
    {

        // get the encrypted sample for decryption
        byte[] encSample = encBuf;

        // Check Minimum Length (IV (12))
        if (null == encSample || encSample.length < 12)
        {
            throw new Exception("none or wrong sample for encryption");
        }
        byte[] iv = Arrays.copyOfRange(encSample, 0, 12);
        byte[] es = Arrays.copyOfRange(encSample, 12, encSample.length);

        // Perform Decryption
        Cipher c = Cipher.getInstance("AES/GCM/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secretKeyBob, new GCMParameterSpec(128, iv));
        byte[] output = c.doFinal(es);

        // Compare the decrypted output
        if (output.length != inputBuf.length)
        {
            throw new Exception("Decryption failed: output length is different");
        }

        if (!Arrays.equals(output, inputBuf))
        {
            throw new Exception("Decryption failed: output is different from input");
        }

    }

}
