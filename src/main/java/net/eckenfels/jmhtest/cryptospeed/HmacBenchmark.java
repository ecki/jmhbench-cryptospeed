package net.eckenfels.jmhtest.cryptospeed;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
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

    @Param(value = { "null", "SUN", "IBMJCE" /*, "BC"*/ })
    private String provider;

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
        throws NoSuchAlgorithmException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        Random r = new Random(42);
        inputBuf = new byte[bufsize];
        r.nextBytes(inputBuf);

        if ("BC".equalsIgnoreCase(provider))
            Security.addProvider((Provider)Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider").newInstance());

        keyMD5 = generateKey("HmacMD5");
        keySHA1 = generateKey("HmacSHA1");
        keySHA224 = generateKey("HmacSHA224");
        keySHA256 = generateKey("HmacSHA256");
        keySHA384 = generateKey("HmacSHA384");
        keySHA512 = generateKey("HmacSHA512");
    }




    @Benchmark
    public byte[] HmacMD5()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacMD5");
        mac.init(keyMD5);
        return mac.doFinal(inputBuf);
    }

    @Benchmark
    public byte[] HmacSHA1()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacSHA1");
        mac.init(keySHA1);
        return mac.doFinal(inputBuf);
    }


    @Benchmark
    public byte[] HmacSHA224()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacSHA224");
        mac.init(keySHA224);
        return mac.doFinal(inputBuf);
    }


    @Benchmark
    public byte[] HmacSHA256()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacSHA256");
        mac.init(keySHA256);
        return mac.doFinal(inputBuf);
    }


    @Benchmark
    public byte[] HmacSHA384()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacSHA384");
        mac.init(keySHA384);
        return mac.doFinal(inputBuf);
    }

    @Benchmark
    public byte[] HmacSHA512()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacSHA512");
        mac.init(keySHA512);
        return mac.doFinal(inputBuf);
    }

    @Benchmark
    public byte[] HmacSHA512Short()
        throws NoSuchAlgorithmException, InvalidKeyException
    {
        Mac mac = getMac("HmacSHA256");
        mac.init(keySHA1); // ***
        return mac.doFinal(inputBuf);
    }


    private SecretKey generateKey(String alg) throws NoSuchAlgorithmException
    {
        if ("null".equalsIgnoreCase(provider))
            return KeyGenerator.getInstance(alg).generateKey();

        Provider p = Security.getProvider(provider);
        if (p == null)
            throw new NoSuchAlgorithmException("Provider " + provider + " not found");

        return KeyGenerator.getInstance(alg, p).generateKey();
    }

    private Mac getMac(String alg) throws NoSuchAlgorithmException
    {
        if ("null".equalsIgnoreCase(provider))
            return Mac.getInstance(alg);

        Provider p = Security.getProvider(provider);
        if (p == null)
            throw new NoSuchAlgorithmException("Provider " + provider + " not found");

        return Mac.getInstance(alg, p);
    }


}
