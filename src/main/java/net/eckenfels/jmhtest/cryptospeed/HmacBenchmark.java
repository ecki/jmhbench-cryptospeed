package net.eckenfels.jmhtest.cryptospeed;


import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

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
public class HmacBenchmark
{

    @Param(value = {"0", "100", "1024", "1048576"})
    int bufsize;

    // thread local buffer to work on
    public byte[] inputBuf;

    private SecretKey keyMD5;
    private SecretKey keySHA1;
    private SecretKey keySHA224;
    private SecretKey keySHA256;
    private SecretKey keySHA384;
    private SecretKey keySHA512;



    @Setup
    public void init()
        throws NoSuchAlgorithmException
    {
        Random r = new Random(42);
        inputBuf = new byte[bufsize];
        r.nextBytes(inputBuf);

        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        keyMD5 = keyGen.generateKey();
        keyGen = KeyGenerator.getInstance("HmacSHA1");
        keySHA1 = keyGen.generateKey();
        keyGen = KeyGenerator.getInstance("HmacSHA224");
        keySHA224 = keyGen.generateKey();
        keyGen = KeyGenerator.getInstance("HmacSHA256");
        keySHA256 = keyGen.generateKey();
        keyGen = KeyGenerator.getInstance("HmacSHA384");
        keySHA384 = keyGen.generateKey();
        keyGen = KeyGenerator.getInstance("HmacSHA512");
        keySHA512 = keyGen.generateKey();
    }


    @Benchmark
    public byte[] HmacMD5()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(keyMD5);
        return mac.doFinal(inputBuf);
    }

    @Benchmark
    public byte[] HmacSHA1()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(keySHA1);
        return mac.doFinal(inputBuf);
    }


    @Benchmark
    public byte[] HmacSHA224()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacSHA224");
        mac.init(keySHA224);
        return mac.doFinal(inputBuf);
    }


    @Benchmark
    public byte[] HmacSHA256()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySHA256);
        return mac.doFinal(inputBuf);
    }


    @Benchmark
    public byte[] HmacSHA384()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacSHA384");
        mac.init(keySHA384);
        return mac.doFinal(inputBuf);
    }

    @Benchmark
    public byte[] HmacSHA512()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(keySHA512);
        return mac.doFinal(inputBuf);
    }

    @Benchmark
    public byte[] HmacSHA512Short()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySHA1); // ***
        return mac.doFinal(inputBuf);
    }
}
