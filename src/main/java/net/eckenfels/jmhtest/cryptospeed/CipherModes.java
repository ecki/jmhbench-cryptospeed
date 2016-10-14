package net.eckenfels.jmhtest.cryptospeed;


import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

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
public class CipherModes
{
    static final int K = 1024;

    @Param(value = {"100", ""+(200*K), ""+(400*K), ""+(1 * K * K)})
    int plainlen;
    private byte[] inputArray;
    private ByteBuffer inputBuffer;

    @Param(value = {"AES/CBC/NOPADDING", "AES/CBC/PKCS5Padding", "AES/CBC/ISO10126PADDING", "AES/GCM/NOPADDING", "AES/CTR/NOPADDING"})
    String cipher;

    @Param(value = {"null", "SunJCE", "IBMJCE" /*, "BC" */})
    String provider;

    @Param(value = {"128", "192", "256"})
    int keysize;
    private SecretKey encKey;

    private SecureRandom sr;
    private AlgorithmParameterSpec iv;
    private Cipher c;

    private byte[] workArray;
    private ByteBuffer workBuffer;


    @Setup
    public void init() throws NoSuchAlgorithmException, InvalidKeySpecException,
                           InvalidKeyException, NoSuchPaddingException,
                           InvalidAlgorithmParameterException, IllegalBlockSizeException,
                           BadPaddingException, NoSuchProviderException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        sr = SecureRandom.getInstance("SHA1PRNG");

        if ("BC".equalsIgnoreCase(provider))
            Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").newInstance());

        // one (random) key to rule them all
        KeyGenerator gen;
        if ("null".equals(provider))
            gen = KeyGenerator.getInstance("AES");
        else
            gen = KeyGenerator.getInstance("AES", provider);
        gen.init(keysize, sr);
        encKey = gen.generateKey();



        // setup input message
        // round up for blocked non-padding case
        int inputSize;
        if (cipher.contains("CBC/NOPADDING"))
        {
            inputSize = ((plainlen+1)/16)*16;
        }
        else
        {
            inputSize = plainlen;
        }


        // create randomly filled input array and buffer
        Random r = new Random(42);
        inputArray = new byte[inputSize];
        r.nextBytes(inputArray);

        inputBuffer = ByteBuffer.allocate(inputArray.length);
        inputBuffer.put(inputArray);


        // generate fixed 16 byte iv (all nul - not good!)
        int osize = 0;
        c = Cipher.getInstance(cipher, provider);
        if (cipher.contains("GCM"))
        {
            iv = new GCMParameterSpec(96, new byte[16]);
            c.init(Cipher.ENCRYPT_MODE, encKey, iv); // for getOutputLen
            osize = c.getOutputSize(inputArray.length); // padding and gcm-tag
            iv = new GCMParameterSpec(96, new byte[16]); // "no reuse"
        } else {
            iv = new IvParameterSpec(new byte[16]);
            c.init(Cipher.ENCRYPT_MODE, encKey, iv); // for getOutputLen
            osize = c.getOutputSize(inputArray.length); // padding and gcm-tag
        }

        // generate work area (used for output, array and ByteBuffer)
        workArray = new byte[osize];
        workBuffer = ByteBuffer.allocate(osize);

        // System.out.println("Setting up " + this + " with osize=" + osize);
    }

    @Benchmark
    public byte[] encryptFinalArrayCopy()
                    throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ShortBufferException, NoSuchProviderException
    {
        c = getCipher(cipher);
        c.init(Cipher.ENCRYPT_MODE, encKey, iv);
        return c.doFinal(inputArray);
    }

    @Benchmark
    public byte[] encryptFinalArrayWork()
                    throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ShortBufferException, NoSuchProviderException
    {
        c = getCipher(cipher);
        c.init(Cipher.ENCRYPT_MODE, encKey, iv);
        /*int olen =*/ c.doFinal(inputArray, 0, inputArray.length, workArray, 0);
        return workArray;
    }

    @Benchmark
    public byte[] encryptUpdateArrayWork()
                    throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, ShortBufferException, NoSuchProviderException
    {
        c = getCipher(cipher);
        c.init(Cipher.ENCRYPT_MODE, encKey, iv);
        int x = c.update(inputArray, 0, inputArray.length, workArray);
        /*int olen =*/ c.doFinal(workArray, x);
        return workArray;
    }

    @Benchmark
    public ByteBuffer encryptFinalBufferWork() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException
    {
        if (inputBuffer.position() != 0)
            inputBuffer.flip();
        if (workBuffer.position() != 0)
            workBuffer.clear();
        c = getCipher(cipher);
        c.init(Cipher.ENCRYPT_MODE, encKey, iv);
        c.doFinal(inputBuffer, workBuffer);
        return workBuffer;
    }

    private Cipher getCipher(String alg) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException
    {
        if ("null".equals(provider))
            return Cipher.getInstance(alg);
        else
            return Cipher.getInstance(alg, provider);
    }
}
