package net.eckenfels.jmhtest.cryptospeed;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
public class HashBenchmark
{
    @Param(value = {"0", "100", "1024", "1048576"})
    int bufsize;

    // thread local buffer to work on
    public byte[] inputBuf;

    // pre created MDs for the reuse case
    private MessageDigest sha1;
    private MessageDigest sha224;
    private MessageDigest sha256;
    private MessageDigest sha384;
    private MessageDigest sha512;
    private MessageDigest md5;
    private MessageDigest md2;


    @Setup
    public void init()
        throws NoSuchAlgorithmException
    {
        Random r = new Random(42);
        inputBuf = new byte[bufsize];
        r.nextBytes(inputBuf);

        sha1 = MessageDigest.getInstance("SHA-1");
        sha224 = MessageDigest.getInstance("SHA-224");
        sha256 = MessageDigest.getInstance("SHA-256");
        sha384 = MessageDigest.getInstance("SHA-384");
        sha512 = MessageDigest.getInstance("SHA-512");
        md5 = MessageDigest.getInstance("MD5");
        md2 = MessageDigest.getInstance("MD2");
    }


    @Benchmark
    public byte[] SHA1()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA224()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-224");
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA256()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA384()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-384");
        return digest.digest(inputBuf);
    }


    @Benchmark
    public byte[] SHA512()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        return digest.digest(inputBuf);
    }


    @Benchmark
    public byte[] SHA1Reuse()
    {
        MessageDigest digest = sha1;
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA224Reuse()
    {
        MessageDigest digest = sha224;
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA256Reuse()
    {
        MessageDigest digest = sha256;
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA384Reuse()
    {
        MessageDigest digest = sha384;
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] SHA512Reuse()
    {
        MessageDigest digest = sha512;
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] MD5()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] MD2()
        throws NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance("MD2");
        return digest.digest(inputBuf);
    }


    @Benchmark
    public byte[] MD5Reuse()
    {
        MessageDigest digest = md5;
        return digest.digest(inputBuf);
    }


    @Benchmark
    public byte[] MD2Reuse()
    {
        MessageDigest digest = md2;
        return digest.digest(inputBuf);
    }

}
