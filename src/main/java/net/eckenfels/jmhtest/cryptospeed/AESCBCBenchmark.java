package net.eckenfels.jmhtest.cryptospeed;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
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
public class AESCBCBenchmark
{

    @Param(value = {"0", "100", "1024", "1048576"})
    int bufsize;

    // thread local buffer to work on
    public byte[] inputBuf;

    // inputBuf encrypted
    public byte[] encBuf;


    // the password
    String pwd = "seeburger";

    private final static int PBKDF2Iterations = 100000;

    SecretKeySpec encKey;
    byte[] eSalt = new byte[20];

    SecretKeySpec hmacKey;
    byte[] hmacSalt = new byte[20];


    SecureRandom sr;


    @Setup
    public void init()
        throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {

        sr = SecureRandom.getInstance("SHA1PRNG");

        // generate 160 bit salt for encryption key
        sr.nextBytes(eSalt);
        // generate 128 bit encryption key
        encKey = deriveKey(pwd, "AES", eSalt, PBKDF2Iterations, 128);

        // generate 160 bit salt for HMAC key
        sr.nextBytes(hmacSalt);
        // generate 160 bit HMAC key
        hmacKey = deriveKey(pwd, "HmacSHA256", hmacSalt, PBKDF2Iterations, 160);

        Random r = new Random(42);
        inputBuf = new byte[bufsize];
        r.nextBytes(inputBuf);


        // prepare the samples for the decrypt
        encBuf = encrypt();

    }


    private SecretKeySpec deriveKey(String pwd, String algorithm, byte[] salt, int iterationCount, int outKeyBits) throws NoSuchAlgorithmException, InvalidKeySpecException
    {

        PBEKeySpec ks = new PBEKeySpec(pwd.toCharArray(), salt, iterationCount, outKeyBits);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] key = skf.generateSecret(ks).getEncoded();

        return new SecretKeySpec(key, algorithm);

      }


    @Benchmark
    public byte[] encrypt()
        throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {

        // perform encryption
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, encKey, new IvParameterSpec(new byte[16]));
        byte[] cipher = c.doFinal(inputBuf);

        // perform HMAC using SHA256 over cipher
        Mac m = Mac.getInstance("HmacSHA256");
        m.init(hmacKey);
        byte[] hmac = m.doFinal(cipher);


        byte[] output = new byte[40 + cipher.length + 32];

        System.arraycopy(eSalt, 0, output, 0, 20);
        System.arraycopy(hmacSalt, 0, output, 20, 20);
        System.arraycopy(cipher, 0, output, 40, cipher.length);
        System.arraycopy(hmac, 0, output, 40 + cipher.length, 32);

        return (output);
    }

    @Benchmark
    public void decrypt()
        throws Exception
    {

        // get the encrypted sample for decryption
        byte[] encSample = encBuf;

        // Check Minimum Length (ESALT (20) + HSALT (20) + HMAC (32))
        if (null == encSample || encSample.length <= 72)
        {
            throw new Exception("none or wrong sample for encryption");
        }
        // Recover Elements from String
        byte[] esalt = Arrays.copyOfRange(encSample, 0, 20);
        byte[] hsalt = Arrays.copyOfRange(encSample, 20, 40);
        byte[] cipher = Arrays.copyOfRange(encSample, 40, encSample.length - 32);
        byte[] hmac = Arrays.copyOfRange(encSample, encSample.length - 32, encSample.length);

        // Regenerate HMAC key using Recovered Salt (hsalt)
        SecretKeySpec hks = deriveKey(pwd, "HmacSHA256", hsalt, PBKDF2Iterations, 160);

        // Perform HMAC using SHA-256
        Mac m = Mac.getInstance("HmacSHA256");
        m.init(hks);
        byte[] chmac = m.doFinal(cipher);

        // Compare Computed HMAC vs Recovered HMAC
        if (!MessageDigest.isEqual(hmac, chmac))
        {
            throw new Exception("HMAC verification failed.");
        }
        // HMAC Verification Passed
        // Regenerate Encryption Key using Recovered Salt (esalt)
        SecretKeySpec eks = deriveKey(pwd, "AES", esalt, PBKDF2Iterations, 128);

        // Perform Decryption
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, eks, new IvParameterSpec(new byte[16]));
        byte[] output = c.doFinal(cipher);

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
