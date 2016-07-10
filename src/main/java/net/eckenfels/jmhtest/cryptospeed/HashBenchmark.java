package net.eckenfels.jmhtest.cryptospeed;


import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
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
    @Param(value = { "0", "100", "1024", "1048576" })
    int size;

    @Param(value = { "SHA1", "SHA-224", "SHA-256", "SHA-384", "SHA-512", "MD5" /* , "MD2" */ })
    private String algo;

    @Param(value = { "null", "SUN", "IBMJCE" })
    private String provider;


    // pre-created MDs for the reuse case
    private MessageDigest current;
    // pre-allocated digest result
    private byte[] workOut;

    // thread local buffer to work on
    private byte[] inputBuf;


    @Setup
    public void init()
        throws NoSuchAlgorithmException, NoSuchProviderException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        Random r = new Random(42);
        inputBuf = new byte[size];
        r.nextBytes(inputBuf);

        if ("null".equalsIgnoreCase(provider))
            provider = null;

        if ("BC".equalsIgnoreCase(provider))
            Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").newInstance());

        current = allocateDigest();

        workOut = new byte[current.getDigestLength()];
    }

    // - alloc digest

    @Benchmark
    public byte[] digestFinalAllocCopy()
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        MessageDigest digest = allocateDigest();
        return digest.digest(inputBuf);
    }

    @Benchmark
    public byte[] digestUpdateAllocCopy()
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        MessageDigest digest = allocateDigest();
        digest.update(inputBuf);
        return digest.digest();
    }

    @Benchmark
    public byte[] digestUpdateAllocWork()
        throws NoSuchAlgorithmException, NoSuchProviderException, DigestException
    {
        MessageDigest digest = allocateDigest();
        digest.update(inputBuf);
        digest.digest(workOut, 0, workOut.length);
        return workOut;
    }

    // - reuse digest

    @Benchmark
    public byte[] digestUpdateReuseCopy()
    {
        current.update(inputBuf);
        return current.digest();
    }

    @Benchmark
    public byte[] digestUpdateReuseWork()
        throws DigestException
    {
        current.update(inputBuf);
        current.digest(workOut, 0, workOut.length);
        return workOut;
    }

    @Benchmark
    public byte[] digestFinalReuseCopy()
    {
        return current.digest(inputBuf);
    }


    private MessageDigest allocateDigest() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        if (provider != null)
            return MessageDigest.getInstance(algo, provider);

        return MessageDigest.getInstance(algo);
    }
}
