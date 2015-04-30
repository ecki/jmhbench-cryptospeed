# jmhbench-cryptospeed

JMH Benchmarks to time crypto primitives in Java.

## Building

This uses [JMH](http://openjdk.java.net/projects/code-tools/jmh/) micro benchmark framework and is built with Apache Maven:

    > mvn clean validate
    ...
    > java -jar target/jmhbench-cryptospeed.jar -h
    Usage: java -jar ... [regexp*] [options]
      [arguments]                 Benchmarks to run (regexp+).
    ...
License: Apache Software License 2.0 - Bernd Eckenfels, Germany.

## Sample Output

The following is produced on a german Windows, if you want to see different decimal separators, use `-Duser.language.format=en`.

    > java -jar target\jmhbench-cryptospeed.jar -prof gc -t 4
    # JMH 1.9.1 (released 6 days ago)
    # VM invoker: C:\Program Files (x86)\Java\jre1.8.0_45\bin\java.exe
    # VM options: <none>
    # Warmup: 2 iterations, 1 s each
    # Measurement: 3 iterations, 5 s each
    # Timeout: 10 min per iteration
    # Threads: 4 threads, will synchronize iterations
    # Benchmark mode: Average time, time/op
    # Benchmark: net.eckenfels.jmhtest.cryptospeed.HashBenchmark.MD2
    # Parameters: (bufsize = 0)
    
    # Run progress: 0,00% complete, ETA 00:20:24
    # Fork: 1 of 1
    # Warmup Iteration   1: 12,095 ±(99.9%) 3,554 us/op
    # Warmup Iteration   2: 11,415 ±(99.9%) 4,281 us/op
    Iteration   1: 11,588 ±(99.9%) 0,973 us/op
                     ·gc.alloc.rate:                210,054 MB/sec
                     ·gc.alloc.rate.norm:           638,060 B/op
                     ·gc.churn.Eden_Space:          211,307 MB/sec
                     ·gc.churn.Eden_Space.norm:     641,866 B/op
                     ·gc.churn.Survivor_Space:      0,008 MB/sec
                     ·gc.churn.Survivor_Space.norm: 0,024 B/op
                     ·gc.count:                     245,000 counts
                     ·gc.time:                      120,000 ms
    
    Iteration   2: 11,577 ±(99.9%) 1,162 us/op
                     ·gc.alloc.rate:                210,270 MB/sec
                     ·gc.alloc.rate.norm:           638,118 B/op
                     ·gc.churn.Eden_Space:          210,442 MB/sec
                     ·gc.churn.Eden_Space.norm:     638,639 B/op
                     ·gc.churn.Survivor_Space:      0,007 MB/sec
                     ·gc.churn.Survivor_Space.norm: 0,022 B/op
                     ·gc.count:                     244,000 counts
                     ·gc.time:                      116,000 ms
    
    Iteration   3: 12,333 ±(99.9%) 0,446 us/op
                     ·gc.alloc.rate:                197,408 MB/sec
                     ·gc.alloc.rate.norm:           638,230 B/op
                     ·gc.churn.Eden_Space:          197,497 MB/sec
                     ·gc.churn.Eden_Space.norm:     638,517 B/op
                     ·gc.churn.Survivor_Space:      0,007 MB/sec
                     ·gc.churn.Survivor_Space.norm: 0,021 B/op
                     ·gc.count:                     229,000 counts
                     ·gc.time:                      123,000 ms
    
    ...
    # Run complete. Total time: 00:21:46
    
    Benchmark                                                    (bufsize)  Mode  Cnt       Score        Error   Units
    HashBenchmark.MD2                                                    0  avgt    3      11,833 ±      7,900   us/op
    HashBenchmark.MD2:·gc.alloc.rate                                     0  avgt    3     205,911 ±    134,356  MB/sec
    HashBenchmark.MD2:·gc.alloc.rate.norm                                0  avgt    3     638,136 ±      1,576    B/op
    HashBenchmark.MD2:·gc.churn.Eden_Space                               0  avgt    3     206,415 ±    141,128  MB/sec
    HashBenchmark.MD2:·gc.churn.Eden_Space.norm                          0  avgt    3     639,674 ±     34,653    B/op
    HashBenchmark.MD2:·gc.churn.Survivor_Space                           0  avgt    3       0,007 ±      0,013  MB/sec
    HashBenchmark.MD2:·gc.churn.Survivor_Space.norm                      0  avgt    3       0,022 ±      0,028    B/op
    HashBenchmark.MD2:·gc.count                                          0  avgt    3     718,000               counts
    HashBenchmark.MD2:·gc.time                                           0  avgt    3     359,000                   ms
    HashBenchmark.MD2                                                  100  avgt    3      39,077 ±     27,332   us/op
    HashBenchmark.MD2:·gc.alloc.rate                                   100  avgt    3      62,316 ±     44,571  MB/sec
    HashBenchmark.MD2:·gc.alloc.rate.norm                              100  avgt    3     637,747 ±      3,395    B/op
    HashBenchmark.MD2:·gc.churn.Eden_Space                             100  avgt    3      62,667 ±     48,104  MB/sec
    HashBenchmark.MD2:·gc.churn.Eden_Space.norm                        100  avgt    3     641,299 ±     83,403    B/op
    HashBenchmark.MD2:·gc.churn.Survivor_Space                         100  avgt    3       0,005 ±      0,009  MB/sec
    HashBenchmark.MD2:·gc.churn.Survivor_Space.norm                    100  avgt    3       0,053 ±      0,125    B/op
    HashBenchmark.MD2:·gc.count                                        100  avgt    3     218,000               counts
    HashBenchmark.MD2:·gc.time                                         100  avgt    3     136,000                   ms
    HashBenchmark.MD2                                                 1024  avgt    3     275,974 ±    126,191   us/op
    HashBenchmark.MD2:·gc.alloc.rate                                  1024  avgt    3       8,820 ±      3,852  MB/sec
    HashBenchmark.MD2:·gc.alloc.rate.norm                             1024  avgt    3     637,906 ±      9,318    B/op
    HashBenchmark.MD2:·gc.churn.Eden_Space                            1024  avgt    3       8,909 ±      9,021  MB/sec
    HashBenchmark.MD2:·gc.churn.Eden_Space.norm                       1024  avgt    3     644,407 ±    590,024    B/op
    HashBenchmark.MD2:·gc.churn.Survivor_Space                        1024  avgt    3       0,014 ±      0,407  MB/sec
    HashBenchmark.MD2:·gc.churn.Survivor_Space.norm                   1024  avgt    3       1,007 ±     29,051    B/op
    HashBenchmark.MD2:·gc.count                                       1024  avgt    3      31,000               counts
    HashBenchmark.MD2:·gc.time                                        1024  avgt    3      35,000                   ms
    HashBenchmark.MD2                                              1048576  avgt    3  318915,162 ± 418789,130   us/op
    HashBenchmark.MD2:·gc.alloc.rate                               1048576  avgt    3       0,008 ±      0,013  MB/sec
    HashBenchmark.MD2:·gc.alloc.rate.norm                          1048576  avgt    3     718,177 ±   1891,179    B/op
    HashBenchmark.MD2:·gc.count                                    1048576  avgt    3         ? 0               counts
    HashBenchmark.MD2Reuse                                               0  avgt    3      15,503 ±     10,928   us/op
    HashBenchmark.MD2Reuse:·gc.alloc.rate                                0  avgt    3       7,966 ±      5,873  MB/sec
    HashBenchmark.MD2Reuse:·gc.alloc.rate.norm                           0  avgt    3      32,292 ±      0,543    B/op
    HashBenchmark.MD2Reuse:·gc.churn.Eden_Space                          0  avgt    3       8,049 ±      9,087  MB/sec
    HashBenchmark.MD2Reuse:·gc.churn.Eden_Space.norm                     0  avgt    3      32,616 ±     17,870    B/op
    HashBenchmark.MD2Reuse:·gc.churn.Survivor_Space                      0  avgt    3       0,013 ±      0,389  MB/sec
    HashBenchmark.MD2Reuse:·gc.churn.Survivor_Space.norm                 0  avgt    3       0,054 ±      1,584    B/op
    HashBenchmark.MD2Reuse:·gc.count                                     0  avgt    3      28,000               counts
    HashBenchmark.MD2Reuse:·gc.time                                      0  avgt    3      32,000                   ms
    HashBenchmark.MD2Reuse                                             100  avgt    3      63,934 ±     46,518   us/op
    HashBenchmark.MD2Reuse:·gc.alloc.rate                              100  avgt    3       1,945 ±      1,537  MB/sec
    HashBenchmark.MD2Reuse:·gc.alloc.rate.norm                         100  avgt    3      32,374 ±      1,548    B/op
    HashBenchmark.MD2Reuse:·gc.churn.Eden_Space                        100  avgt    3       2,012 ±      9,082  MB/sec
    HashBenchmark.MD2Reuse:·gc.churn.Eden_Space.norm                   100  avgt    3      33,312 ±    123,965    B/op
    HashBenchmark.MD2Reuse:·gc.churn.Survivor_Space                    100  avgt    3      ? 10??               MB/sec
    HashBenchmark.MD2Reuse:·gc.churn.Survivor_Space.norm               100  avgt    3       0,001 ±      0,042    B/op
    HashBenchmark.MD2Reuse:·gc.count                                   100  avgt    3       7,000               counts
    HashBenchmark.MD2Reuse:·gc.time                                    100  avgt    3       9,000                   ms
    HashBenchmark.MD2Reuse                                            1024  avgt    3     303,618 ±    212,009   us/op
    HashBenchmark.MD2Reuse:·gc.alloc.rate                             1024  avgt    3       0,409 ±      0,070  MB/sec
    HashBenchmark.MD2Reuse:·gc.alloc.rate.norm                        1024  avgt    3      32,568 ±     17,061    B/op
    HashBenchmark.MD2Reuse:·gc.churn.Eden_Space                       1024  avgt    3       0,287 ±      9,082  MB/sec
    HashBenchmark.MD2Reuse:·gc.churn.Eden_Space.norm                  1024  avgt    3      23,889 ±    754,860    B/op
    HashBenchmark.MD2Reuse:·gc.churn.Survivor_Space                   1024  avgt    3       0,021 ±      0,679  MB/sec
    HashBenchmark.MD2Reuse:·gc.churn.Survivor_Space.norm              1024  avgt    3       1,787 ±     56,461    B/op
    HashBenchmark.MD2Reuse:·gc.count                                  1024  avgt    3       1,000               counts
    HashBenchmark.MD2Reuse:·gc.time                                   1024  avgt    3       6,000                   ms
    HashBenchmark.MD2Reuse                                         1048576  avgt    3  270353,311 ± 285375,921   us/op
    HashBenchmark.MD2Reuse:·gc.alloc.rate                          1048576  avgt    3       0,001 ±      0,022  MB/sec
    HashBenchmark.MD2Reuse:·gc.alloc.rate.norm                     1048576  avgt    3     101,195 ±   1417,945    B/op
    HashBenchmark.MD2Reuse:·gc.count                               1048576  avgt    3         ? 0               counts
    HashBenchmark.MD5                                                    0  avgt    3       4,818 ±      1,795   us/op
    HashBenchmark.MD5:·gc.alloc.rate                                     0  avgt    3     377,809 ±    139,744  MB/sec
    HashBenchmark.MD5:·gc.alloc.rate.norm                                0  avgt    3     477,012 ±      0,815    B/op
    HashBenchmark.MD5:·gc.churn.Eden_Space                               0  avgt    3     377,470 ±    134,005  MB/sec
    HashBenchmark.MD5:·gc.churn.Eden_Space.norm                          0  avgt    3     476,589 ±      7,088    B/op
    HashBenchmark.MD5:·gc.churn.Survivor_Space                           0  avgt    3       0,010 ±      0,017  MB/sec
    HashBenchmark.MD5:·gc.churn.Survivor_Space.norm                      0  avgt    3       0,013 ±      0,017    B/op
    HashBenchmark.MD5:·gc.count                                          0  avgt    3    1313,000               counts
    HashBenchmark.MD5:·gc.time                                           0  avgt    3     603,000                   ms
    HashBenchmark.MD5                                                  100  avgt    3       5,515 ±      3,205   us/op
    HashBenchmark.MD5:·gc.alloc.rate                                   100  avgt    3     330,325 ±    193,303  MB/sec
    HashBenchmark.MD5:·gc.alloc.rate.norm                              100  avgt    3     476,946 ±      1,950    B/op
    HashBenchmark.MD5:·gc.churn.Eden_Space                             100  avgt    3     329,727 ±    188,583  MB/sec
    HashBenchmark.MD5:·gc.churn.Eden_Space.norm                        100  avgt    3     476,089 ±      5,628    B/op
    HashBenchmark.MD5:·gc.churn.Survivor_Space                         100  avgt    3       0,009 ±      0,011  MB/sec
    HashBenchmark.MD5:·gc.churn.Survivor_Space.norm                    100  avgt    3       0,013 ±      0,009    B/op
    HashBenchmark.MD5:·gc.count                                        100  avgt    3    1148,000               counts
    HashBenchmark.MD5:·gc.time                                         100  avgt    3     573,000                   ms
    HashBenchmark.MD5                                                 1024  avgt    3      13,999 ±      0,848   us/op
    HashBenchmark.MD5:·gc.alloc.rate                                  1024  avgt    3     129,829 ±      7,417  MB/sec
    HashBenchmark.MD5:·gc.alloc.rate.norm                             1024  avgt    3     476,433 ±      2,510    B/op
    HashBenchmark.MD5:·gc.churn.Eden_Space                            1024  avgt    3     129,943 ±      9,191  MB/sec
    HashBenchmark.MD5:·gc.churn.Eden_Space.norm                       1024  avgt    3     476,853 ±     14,540    B/op
    HashBenchmark.MD5:·gc.churn.Survivor_Space                        1024  avgt    3       0,004 ±      0,008  MB/sec
    HashBenchmark.MD5:·gc.churn.Survivor_Space.norm                   1024  avgt    3       0,015 ±      0,029    B/op
    HashBenchmark.MD5:·gc.count                                       1024  avgt    3     452,000               counts
    HashBenchmark.MD5:·gc.time                                        1024  avgt    3     228,000                   ms
    HashBenchmark.MD5                                              1048576  avgt    3   11019,521 ±   8356,518   us/op
    HashBenchmark.MD5:·gc.alloc.rate                               1048576  avgt    3       0,168 ±      0,110  MB/sec
    HashBenchmark.MD5:·gc.alloc.rate.norm                          1048576  avgt    3     484,218 ±    265,299    B/op
    HashBenchmark.MD5:·gc.churn.Eden_Space                         1048576  avgt    3       0,286 ±      9,048  MB/sec
    HashBenchmark.MD5:·gc.churn.Eden_Space.norm                    1048576  avgt    3     855,950 ±  27047,212    B/op
    HashBenchmark.MD5:·gc.count                                    1048576  avgt    3       1,000               counts
    HashBenchmark.MD5:·gc.time                                     1048576  avgt    3       3,000                   ms
    HashBenchmark.MD5Reuse                                               0  avgt    3       0,944 ±      0,358   us/op
    HashBenchmark.MD5Reuse:·gc.alloc.rate                                0  avgt    3     130,717 ±     50,138  MB/sec
    HashBenchmark.MD5Reuse:·gc.alloc.rate.norm                           0  avgt    3      32,333 ±      0,073    B/op
    HashBenchmark.MD5Reuse:·gc.churn.Eden_Space                          0  avgt    3     130,509 ±     50,709  MB/sec
    HashBenchmark.MD5Reuse:·gc.churn.Eden_Space.norm                     0  avgt    3      32,281 ±      0,713    B/op
    HashBenchmark.MD5Reuse:·gc.churn.Survivor_Space                      0  avgt    3       0,002 ±      0,009  MB/sec
    HashBenchmark.MD5Reuse:·gc.churn.Survivor_Space.norm                 0  avgt    3       0,001 ±      0,002    B/op
    HashBenchmark.MD5Reuse:·gc.count                                     0  avgt    3     454,000               counts
    HashBenchmark.MD5Reuse:·gc.time                                      0  avgt    3     204,000                   ms
    HashBenchmark.MD5Reuse                                             100  avgt    3       1,668 ±      2,544   us/op
    HashBenchmark.MD5Reuse:·gc.alloc.rate                              100  avgt    3      74,294 ±    118,654  MB/sec
    HashBenchmark.MD5Reuse:·gc.alloc.rate.norm                         100  avgt    3      32,321 ±      0,096    B/op
    HashBenchmark.MD5Reuse:·gc.churn.Eden_Space                        100  avgt    3      74,454 ±    119,173  MB/sec
    HashBenchmark.MD5Reuse:·gc.churn.Eden_Space.norm                   100  avgt    3      32,391 ±      1,619    B/op
    HashBenchmark.MD5Reuse:·gc.churn.Survivor_Space                    100  avgt    3       0,002 ±      0,010  MB/sec
    HashBenchmark.MD5Reuse:·gc.churn.Survivor_Space.norm               100  avgt    3       0,001 ±      0,004    B/op
    HashBenchmark.MD5Reuse:·gc.count                                   100  avgt    3     259,000               counts
    HashBenchmark.MD5Reuse:·gc.time                                    100  avgt    3     146,000                   ms
    HashBenchmark.MD5Reuse                                            1024  avgt    3      10,406 ±      8,516   us/op
    HashBenchmark.MD5Reuse:·gc.alloc.rate                             1024  avgt    3      11,851 ±      9,610  MB/sec
    HashBenchmark.MD5Reuse:·gc.alloc.rate.norm                        1024  avgt    3      32,285 ±      0,567    B/op
    HashBenchmark.MD5Reuse:·gc.churn.Eden_Space                       1024  avgt    3      12,075 ±     15,735  MB/sec
    HashBenchmark.MD5Reuse:·gc.churn.Eden_Space.norm                  1024  avgt    3      32,883 ±     27,163    B/op
    HashBenchmark.MD5Reuse:·gc.churn.Survivor_Space                   1024  avgt    3       0,013 ±      0,381  MB/sec
    HashBenchmark.MD5Reuse:·gc.churn.Survivor_Space.norm              1024  avgt    3       0,036 ±      1,019    B/op
    HashBenchmark.MD5Reuse:·gc.count                                  1024  avgt    3      42,000               counts
    HashBenchmark.MD5Reuse:·gc.time                                   1024  avgt    3      35,000                   ms
    HashBenchmark.MD5Reuse                                         1048576  avgt    3   10834,229 ±  11213,848   us/op
    HashBenchmark.MD5Reuse:·gc.alloc.rate                          1048576  avgt    3       0,012 ±      0,025  MB/sec
    HashBenchmark.MD5Reuse:·gc.alloc.rate.norm                     1048576  avgt    3      35,070 ±     64,504    B/op
    HashBenchmark.MD5Reuse:·gc.count                               1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA1                                                   0  avgt    3       7,541 ±      4,034   us/op
    HashBenchmark.SHA1:·gc.alloc.rate                                    0  avgt    3     373,206 ±    199,683  MB/sec
    HashBenchmark.SHA1:·gc.alloc.rate.norm                               0  avgt    3     736,546 ±      3,657    B/op
    HashBenchmark.SHA1:·gc.churn.Eden_Space                              0  avgt    3     373,705 ±    198,965  MB/sec
    HashBenchmark.SHA1:·gc.churn.Eden_Space.norm                         0  avgt    3     737,532 ±     11,085    B/op
    HashBenchmark.SHA1:·gc.churn.Survivor_Space                          0  avgt    3       0,016 ±      0,021  MB/sec
    HashBenchmark.SHA1:·gc.churn.Survivor_Space.norm                     0  avgt    3       0,032 ±      0,025    B/op
    HashBenchmark.SHA1:·gc.count                                         0  avgt    3    1301,000               counts
    HashBenchmark.SHA1:·gc.time                                          0  avgt    3     896,000                   ms
    HashBenchmark.SHA1                                                 100  avgt    3       9,543 ±      1,892   us/op
    HashBenchmark.SHA1:·gc.alloc.rate                                  100  avgt    3     294,358 ±     56,310  MB/sec
    HashBenchmark.SHA1:·gc.alloc.rate.norm                             100  avgt    3     735,924 ±      1,803    B/op
    HashBenchmark.SHA1:·gc.churn.Eden_Space                            100  avgt    3     294,916 ±     57,524  MB/sec
    HashBenchmark.SHA1:·gc.churn.Eden_Space.norm                       100  avgt    3     737,317 ±      3,171    B/op
    HashBenchmark.SHA1:·gc.churn.Survivor_Space                        100  avgt    3       0,012 ±      0,007  MB/sec
    HashBenchmark.SHA1:·gc.churn.Survivor_Space.norm                   100  avgt    3       0,030 ±      0,021    B/op
    HashBenchmark.SHA1:·gc.count                                       100  avgt    3    1027,000               counts
    HashBenchmark.SHA1:·gc.time                                        100  avgt    3     729,000                   ms
    HashBenchmark.SHA1                                                1024  avgt    3      32,321 ±     24,052   us/op
    HashBenchmark.SHA1:·gc.alloc.rate                                 1024  avgt    3      86,825 ±     66,382  MB/sec
    HashBenchmark.SHA1:·gc.alloc.rate.norm                            1024  avgt    3     734,789 ±      6,215    B/op
    HashBenchmark.SHA1:·gc.churn.Eden_Space                           1024  avgt    3      87,383 ±     65,490  MB/sec
    HashBenchmark.SHA1:·gc.churn.Eden_Space.norm                      1024  avgt    3     739,530 ±     30,397    B/op
    HashBenchmark.SHA1:·gc.churn.Survivor_Space                       1024  avgt    3       0,007 ±      0,015  MB/sec
    HashBenchmark.SHA1:·gc.churn.Survivor_Space.norm                  1024  avgt    3       0,061 ±      0,108    B/op
    HashBenchmark.SHA1:·gc.count                                      1024  avgt    3     304,000               counts
    HashBenchmark.SHA1:·gc.time                                       1024  avgt    3     163,000                   ms
    HashBenchmark.SHA1                                             1048576  avgt    3   22139,980 ±  32900,007   us/op
    HashBenchmark.SHA1:·gc.alloc.rate                              1048576  avgt    3       0,129 ±      0,272  MB/sec
    HashBenchmark.SHA1:·gc.alloc.rate.norm                         1048576  avgt    3     744,852 ±    464,249    B/op
    HashBenchmark.SHA1:·gc.count                                   1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA1Reuse                                              0  avgt    3       1,888 ±      0,937   us/op
    HashBenchmark.SHA1Reuse:·gc.alloc.rate                               0  avgt    3      65,341 ±     32,326  MB/sec
    HashBenchmark.SHA1Reuse:·gc.alloc.rate.norm                          0  avgt    3      32,321 ±      0,215    B/op
    HashBenchmark.SHA1Reuse:·gc.churn.Eden_Space                         0  avgt    3      65,543 ±     31,436  MB/sec
    HashBenchmark.SHA1Reuse:·gc.churn.Eden_Space.norm                    0  avgt    3      32,421 ±      1,843    B/op
    HashBenchmark.SHA1Reuse:·gc.churn.Survivor_Space                     0  avgt    3       0,002 ±      0,002  MB/sec
    HashBenchmark.SHA1Reuse:·gc.churn.Survivor_Space.norm                0  avgt    3       0,001 ±      0,001    B/op
    HashBenchmark.SHA1Reuse:·gc.count                                    0  avgt    3     228,000               counts
    HashBenchmark.SHA1Reuse:·gc.time                                     0  avgt    3     140,000                   ms
    HashBenchmark.SHA1Reuse                                            100  avgt    3       2,833 ±      2,467   us/op
    HashBenchmark.SHA1Reuse:·gc.alloc.rate                             100  avgt    3      43,579 ±     38,993  MB/sec
    HashBenchmark.SHA1Reuse:·gc.alloc.rate.norm                        100  avgt    3      32,311 ±      0,093    B/op
    HashBenchmark.SHA1Reuse:·gc.churn.Eden_Space                       100  avgt    3      43,695 ±     32,820  MB/sec
    HashBenchmark.SHA1Reuse:·gc.churn.Eden_Space.norm                  100  avgt    3      32,405 ±      5,314    B/op
    HashBenchmark.SHA1Reuse:·gc.churn.Survivor_Space                   100  avgt    3       0,005 ±      0,098  MB/sec
    HashBenchmark.SHA1Reuse:·gc.churn.Survivor_Space.norm              100  avgt    3       0,004 ±      0,076    B/op
    HashBenchmark.SHA1Reuse:·gc.count                                  100  avgt    3     152,000               counts
    HashBenchmark.SHA1Reuse:·gc.time                                   100  avgt    3      82,000                   ms
    HashBenchmark.SHA1Reuse                                           1024  avgt    3      24,189 ±     31,163   us/op
    HashBenchmark.SHA1Reuse:·gc.alloc.rate                            1024  avgt    3       5,113 ±      6,809  MB/sec
    HashBenchmark.SHA1Reuse:·gc.alloc.rate.norm                       1024  avgt    3      32,296 ±      0,466    B/op
    HashBenchmark.SHA1Reuse:·gc.churn.Eden_Space                      1024  avgt    3       4,887 ±      9,083  MB/sec
    HashBenchmark.SHA1Reuse:·gc.churn.Eden_Space.norm                 1024  avgt    3      30,928 ±     60,888    B/op
    HashBenchmark.SHA1Reuse:·gc.churn.Survivor_Space                  1024  avgt    3       0,013 ±      0,399  MB/sec
    HashBenchmark.SHA1Reuse:·gc.churn.Survivor_Space.norm             1024  avgt    3       0,083 ±      2,590    B/op
    HashBenchmark.SHA1Reuse:·gc.count                                 1024  avgt    3      17,000               counts
    HashBenchmark.SHA1Reuse:·gc.time                                  1024  avgt    3      24,000                   ms
    HashBenchmark.SHA1Reuse                                        1048576  avgt    3   22339,168 ±   6947,955   us/op
    HashBenchmark.SHA1Reuse:·gc.alloc.rate                         1048576  avgt    3       0,007 ±      0,022  MB/sec
    HashBenchmark.SHA1Reuse:·gc.alloc.rate.norm                    1048576  avgt    3      38,363 ±    135,480    B/op
    HashBenchmark.SHA1Reuse:·gc.count                              1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA256                                                 0  avgt    3       8,904 ±      3,957   us/op
    HashBenchmark.SHA256:·gc.alloc.rate                                  0  avgt    3     301,746 ±    132,102  MB/sec
    HashBenchmark.SHA256:·gc.alloc.rate.norm                             0  avgt    3     703,752 ±      1,547    B/op
    HashBenchmark.SHA256:·gc.churn.Eden_Space                            0  avgt    3     301,845 ±    133,558  MB/sec
    HashBenchmark.SHA256:·gc.churn.Eden_Space.norm                       0  avgt    3     703,980 ±     16,983    B/op
    HashBenchmark.SHA256:·gc.churn.Survivor_Space                        0  avgt    3       0,012 ±      0,016  MB/sec
    HashBenchmark.SHA256:·gc.churn.Survivor_Space.norm                   0  avgt    3       0,027 ±      0,026    B/op
    HashBenchmark.SHA256:·gc.count                                       0  avgt    3    1051,000               counts
    HashBenchmark.SHA256:·gc.time                                        0  avgt    3     711,000                   ms
    HashBenchmark.SHA256                                               100  avgt    3      11,307 ±      3,405   us/op
    HashBenchmark.SHA256:·gc.alloc.rate                                100  avgt    3     237,429 ±     68,936  MB/sec
    HashBenchmark.SHA256:·gc.alloc.rate.norm                           100  avgt    3     703,142 ±      2,385    B/op
    HashBenchmark.SHA256:·gc.churn.Eden_Space                          100  avgt    3     238,027 ±     68,226  MB/sec
    HashBenchmark.SHA256:·gc.churn.Eden_Space.norm                     100  avgt    3     704,915 ±     11,128    B/op
    HashBenchmark.SHA256:·gc.churn.Survivor_Space                      100  avgt    3       0,009 ±      0,006  MB/sec
    HashBenchmark.SHA256:·gc.churn.Survivor_Space.norm                 100  avgt    3       0,027 ±      0,025    B/op
    HashBenchmark.SHA256:·gc.count                                     100  avgt    3     828,000               counts
    HashBenchmark.SHA256:·gc.time                                      100  avgt    3     423,000                   ms
    HashBenchmark.SHA256                                              1024  avgt    3      50,667 ±     14,869   us/op
    HashBenchmark.SHA256:·gc.alloc.rate                               1024  avgt    3      52,887 ±     15,975  MB/sec
    HashBenchmark.SHA256:·gc.alloc.rate.norm                          1024  avgt    3     702,278 ±      5,404    B/op
    HashBenchmark.SHA256:·gc.churn.Eden_Space                         1024  avgt    3      53,181 ±     18,202  MB/sec
    HashBenchmark.SHA256:·gc.churn.Eden_Space.norm                    1024  avgt    3     706,175 ±     30,234    B/op
    HashBenchmark.SHA256:·gc.churn.Survivor_Space                     1024  avgt    3       0,008 ±      0,086  MB/sec
    HashBenchmark.SHA256:·gc.churn.Survivor_Space.norm                1024  avgt    3       0,102 ±      1,157    B/op
    HashBenchmark.SHA256:·gc.count                                    1024  avgt    3     185,000               counts
    HashBenchmark.SHA256:·gc.time                                     1024  avgt    3     129,000                   ms
    HashBenchmark.SHA256                                           1048576  avgt    3   36105,409 ±  14273,271   us/op
    HashBenchmark.SHA256:·gc.alloc.rate                            1048576  avgt    3       0,074 ±      0,044  MB/sec
    HashBenchmark.SHA256:·gc.alloc.rate.norm                       1048576  avgt    3     705,998 ±    209,628    B/op
    HashBenchmark.SHA256:·gc.count                                 1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA256Reuse                                            0  avgt    3       2,526 ±      1,667   us/op
    HashBenchmark.SHA256Reuse:·gc.alloc.rate                             0  avgt    3      73,266 ±     49,082  MB/sec
    HashBenchmark.SHA256Reuse:·gc.alloc.rate.norm                        0  avgt    3      48,469 ±      0,121    B/op
    HashBenchmark.SHA256Reuse:·gc.churn.Eden_Space                       0  avgt    3      73,289 ±     41,730  MB/sec
    HashBenchmark.SHA256Reuse:·gc.churn.Eden_Space.norm                  0  avgt    3      48,493 ±      9,450    B/op
    HashBenchmark.SHA256Reuse:·gc.churn.Survivor_Space                   0  avgt    3       0,002 ±      0,010  MB/sec
    HashBenchmark.SHA256Reuse:·gc.churn.Survivor_Space.norm              0  avgt    3       0,001 ±      0,008    B/op
    HashBenchmark.SHA256Reuse:·gc.count                                  0  avgt    3     255,000               counts
    HashBenchmark.SHA256Reuse:·gc.time                                   0  avgt    3     128,000                   ms
    HashBenchmark.SHA256Reuse                                          100  avgt    3       5,373 ±      2,832   us/op
    HashBenchmark.SHA256Reuse:·gc.alloc.rate                           100  avgt    3      34,436 ±     17,768  MB/sec
    HashBenchmark.SHA256Reuse:·gc.alloc.rate.norm                      100  avgt    3      48,465 ±      0,464    B/op
    HashBenchmark.SHA256Reuse:·gc.churn.Eden_Space                     100  avgt    3      34,497 ±     15,664  MB/sec
    HashBenchmark.SHA256Reuse:·gc.churn.Eden_Space.norm                100  avgt    3      48,554 ±      3,936    B/op
    HashBenchmark.SHA256Reuse:·gc.churn.Survivor_Space                 100  avgt    3       0,007 ±      0,178  MB/sec
    HashBenchmark.SHA256Reuse:·gc.churn.Survivor_Space.norm            100  avgt    3       0,011 ±      0,260    B/op
    HashBenchmark.SHA256Reuse:·gc.count                                100  avgt    3     120,000               counts
    HashBenchmark.SHA256Reuse:·gc.time                                 100  avgt    3      66,000                   ms
    HashBenchmark.SHA256Reuse                                         1024  avgt    3      40,769 ±     25,074   us/op
    HashBenchmark.SHA256Reuse:·gc.alloc.rate                          1024  avgt    3       4,543 ±      2,640  MB/sec
    HashBenchmark.SHA256Reuse:·gc.alloc.rate.norm                     1024  avgt    3      48,497 ±      0,869    B/op
    HashBenchmark.SHA256Reuse:·gc.churn.Eden_Space                    1024  avgt    3       4,600 ±      9,092  MB/sec
    HashBenchmark.SHA256Reuse:·gc.churn.Eden_Space.norm               1024  avgt    3      49,251 ±    128,920    B/op
    HashBenchmark.SHA256Reuse:·gc.churn.Survivor_Space                1024  avgt    3       0,010 ±      0,297  MB/sec
    HashBenchmark.SHA256Reuse:·gc.churn.Survivor_Space.norm           1024  avgt    3       0,100 ±      3,111    B/op
    HashBenchmark.SHA256Reuse:·gc.count                               1024  avgt    3      15,000               counts
    HashBenchmark.SHA256Reuse:·gc.time                                1024  avgt    3      22,000                   ms
    HashBenchmark.SHA256Reuse                                      1048576  avgt    3   36948,768 ±  18586,346   us/op
    HashBenchmark.SHA256Reuse:·gc.alloc.rate                       1048576  avgt    3       0,008 ±      0,088  MB/sec
    HashBenchmark.SHA256Reuse:·gc.alloc.rate.norm                  1048576  avgt    3      77,673 ±    829,353    B/op
    HashBenchmark.SHA256Reuse:·gc.count                            1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA384                                                 0  avgt    3      20,147 ±      6,876   us/op
    HashBenchmark.SHA384:·gc.alloc.rate                                  0  avgt    3     228,161 ±     77,394  MB/sec
    HashBenchmark.SHA384:·gc.alloc.rate.norm                             0  avgt    3    1203,968 ±      2,676    B/op
    HashBenchmark.SHA384:·gc.churn.Eden_Space                            0  avgt    3     229,977 ±     74,353  MB/sec
    HashBenchmark.SHA384:·gc.churn.Eden_Space.norm                       0  avgt    3    1213,565 ±     50,128    B/op
    HashBenchmark.SHA384:·gc.churn.Survivor_Space                        0  avgt    3       0,018 ±      0,018  MB/sec
    HashBenchmark.SHA384:·gc.churn.Survivor_Space.norm                   0  avgt    3       0,095 ±      0,074    B/op
    HashBenchmark.SHA384:·gc.count                                       0  avgt    3     800,000               counts
    HashBenchmark.SHA384:·gc.time                                        0  avgt    3     567,000                   ms
    HashBenchmark.SHA384                                               100  avgt    3      18,594 ±     35,745   us/op
    HashBenchmark.SHA384:·gc.alloc.rate                                100  avgt    3     248,965 ±    470,017  MB/sec
    HashBenchmark.SHA384:·gc.alloc.rate.norm                           100  avgt    3    1204,273 ±      4,179    B/op
    HashBenchmark.SHA384:·gc.churn.Eden_Space                          100  avgt    3     250,666 ±    474,002  MB/sec
    HashBenchmark.SHA384:·gc.churn.Eden_Space.norm                     100  avgt    3    1212,481 ±     31,812    B/op
    HashBenchmark.SHA384:·gc.churn.Survivor_Space                      100  avgt    3       0,019 ±      0,049  MB/sec
    HashBenchmark.SHA384:·gc.churn.Survivor_Space.norm                 100  avgt    3       0,092 ±      0,064    B/op
    HashBenchmark.SHA384:·gc.count                                     100  avgt    3     872,000               counts
    HashBenchmark.SHA384:·gc.time                                      100  avgt    3     457,000                   ms
    HashBenchmark.SHA384                                              1024  avgt    3     116,493 ±     23,197   us/op
    HashBenchmark.SHA384:·gc.alloc.rate                               1024  avgt    3      39,401 ±      7,881  MB/sec
    HashBenchmark.SHA384:·gc.alloc.rate.norm                          1024  avgt    3    1202,811 ±      4,583    B/op
    HashBenchmark.SHA384:·gc.churn.Eden_Space                         1024  avgt    3      39,958 ±      9,129  MB/sec
    HashBenchmark.SHA384:·gc.churn.Eden_Space.norm                    1024  avgt    3    1219,818 ±    199,304    B/op
    HashBenchmark.SHA384:·gc.churn.Survivor_Space                     1024  avgt    3       0,008 ±      0,110  MB/sec
    HashBenchmark.SHA384:·gc.churn.Survivor_Space.norm                1024  avgt    3       0,251 ±      3,298    B/op
    HashBenchmark.SHA384:·gc.count                                    1024  avgt    3     139,000               counts
    HashBenchmark.SHA384:·gc.time                                     1024  avgt    3     103,000                   ms
    HashBenchmark.SHA384                                           1048576  avgt    3   86390,034 ± 201135,926   us/op
    HashBenchmark.SHA384:·gc.alloc.rate                            1048576  avgt    3       0,054 ±      0,121  MB/sec
    HashBenchmark.SHA384:·gc.alloc.rate.norm                       1048576  avgt    3    1214,660 ±    455,323    B/op
    HashBenchmark.SHA384:·gc.count                                 1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA384Reuse                                            0  avgt    3      10,227 ±     10,590   us/op
    HashBenchmark.SHA384Reuse:·gc.alloc.rate                             0  avgt    3      24,154 ±     25,506  MB/sec
    HashBenchmark.SHA384Reuse:·gc.alloc.rate.norm                        0  avgt    3      64,595 ±      0,740    B/op
    HashBenchmark.SHA384Reuse:·gc.churn.Eden_Space                       0  avgt    3      24,147 ±     27,241  MB/sec
    HashBenchmark.SHA384Reuse:·gc.churn.Eden_Space.norm                  0  avgt    3      64,570 ±      9,296    B/op
    HashBenchmark.SHA384Reuse:·gc.churn.Survivor_Space                   0  avgt    3       0,014 ±      0,395  MB/sec
    HashBenchmark.SHA384Reuse:·gc.churn.Survivor_Space.norm              0  avgt    3       0,039 ±      1,088    B/op
    HashBenchmark.SHA384Reuse:·gc.count                                  0  avgt    3      84,000               counts
    HashBenchmark.SHA384Reuse:·gc.time                                   0  avgt    3      53,000                   ms
    HashBenchmark.SHA384Reuse                                          100  avgt    3      10,093 ±      0,936   us/op
    HashBenchmark.SHA384Reuse:·gc.alloc.rate                           100  avgt    3      24,418 ±      2,404  MB/sec
    HashBenchmark.SHA384Reuse:·gc.alloc.rate.norm                      100  avgt    3      64,571 ±      0,532    B/op
    HashBenchmark.SHA384Reuse:·gc.churn.Eden_Space                     100  avgt    3      24,723 ±      9,120  MB/sec
    HashBenchmark.SHA384Reuse:·gc.churn.Eden_Space.norm                100  avgt    3      65,378 ±     24,830    B/op
    HashBenchmark.SHA384Reuse:·gc.churn.Survivor_Space                 100  avgt    3       0,014 ±      0,395  MB/sec
    HashBenchmark.SHA384Reuse:·gc.churn.Survivor_Space.norm            100  avgt    3       0,038 ±      1,038    B/op
    HashBenchmark.SHA384Reuse:·gc.count                                100  avgt    3      86,000               counts
    HashBenchmark.SHA384Reuse:·gc.time                                 100  avgt    3      56,000                   ms
    HashBenchmark.SHA384Reuse                                         1024  avgt    3      87,439 ±    125,032   us/op
    HashBenchmark.SHA384Reuse:·gc.alloc.rate                          1024  avgt    3       2,835 ±      4,214  MB/sec
    HashBenchmark.SHA384Reuse:·gc.alloc.rate.norm                     1024  avgt    3      64,677 ±      0,915    B/op
    HashBenchmark.SHA384Reuse:·gc.churn.Eden_Space                    1024  avgt    3       2,875 ±      9,078  MB/sec
    HashBenchmark.SHA384Reuse:·gc.churn.Eden_Space.norm               1024  avgt    3      65,293 ±    108,874    B/op
    HashBenchmark.SHA384Reuse:·gc.churn.Survivor_Space                1024  avgt    3      ? 10??               MB/sec
    HashBenchmark.SHA384Reuse:·gc.churn.Survivor_Space.norm           1024  avgt    3       0,002 ±      0,048    B/op
    HashBenchmark.SHA384Reuse:·gc.count                               1024  avgt    3      10,000               counts
    HashBenchmark.SHA384Reuse:·gc.time                                1024  avgt    3      15,000                   ms
    HashBenchmark.SHA384Reuse                                      1048576  avgt    3   88306,416 ± 157980,327   us/op
    HashBenchmark.SHA384Reuse:·gc.alloc.rate                       1048576  avgt    3       0,004 ±      0,023  MB/sec
    HashBenchmark.SHA384Reuse:·gc.alloc.rate.norm                  1048576  avgt    3      88,124 ±    502,732    B/op
    HashBenchmark.SHA384Reuse:·gc.count                            1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA512                                                 0  avgt    3      19,727 ±      4,764   us/op
    HashBenchmark.SHA512:·gc.alloc.rate                                  0  avgt    3     236,065 ±     57,444  MB/sec
    HashBenchmark.SHA512:·gc.alloc.rate.norm                             0  avgt    3    1220,247 ±      1,703    B/op
    HashBenchmark.SHA512:·gc.churn.Eden_Space                            0  avgt    3     237,734 ±     63,739  MB/sec
    HashBenchmark.SHA512:·gc.churn.Eden_Space.norm                       0  avgt    3    1228,857 ±     55,156    B/op
    HashBenchmark.SHA512:·gc.churn.Survivor_Space                        0  avgt    3       0,018 ±      0,012  MB/sec
    HashBenchmark.SHA512:·gc.churn.Survivor_Space.norm                   0  avgt    3       0,091 ±      0,042    B/op
    HashBenchmark.SHA512:·gc.count                                       0  avgt    3     827,000               counts
    HashBenchmark.SHA512:·gc.time                                        0  avgt    3     536,000                   ms
    HashBenchmark.SHA512                                               100  avgt    3      20,238 ±     10,254   us/op
    HashBenchmark.SHA512:·gc.alloc.rate                                100  avgt    3     230,216 ±    116,620  MB/sec
    HashBenchmark.SHA512:·gc.alloc.rate.norm                           100  avgt    3    1220,307 ±      1,865    B/op
    HashBenchmark.SHA512:·gc.churn.Eden_Space                          100  avgt    3     231,975 ±    110,450  MB/sec
    HashBenchmark.SHA512:·gc.churn.Eden_Space.norm                     100  avgt    3    1229,670 ±     37,658    B/op
    HashBenchmark.SHA512:·gc.churn.Survivor_Space                      100  avgt    3       0,018 ±      0,027  MB/sec
    HashBenchmark.SHA512:·gc.churn.Survivor_Space.norm                 100  avgt    3       0,098 ±      0,185    B/op
    HashBenchmark.SHA512:·gc.count                                     100  avgt    3     807,000               counts
    HashBenchmark.SHA512:·gc.time                                      100  avgt    3     432,000                   ms
    HashBenchmark.SHA512                                              1024  avgt    3     106,849 ±     59,792   us/op
    HashBenchmark.SHA512:·gc.alloc.rate                               1024  avgt    3      43,545 ±     24,250  MB/sec
    HashBenchmark.SHA512:·gc.alloc.rate.norm                          1024  avgt    3    1218,862 ±      6,302    B/op
    HashBenchmark.SHA512:·gc.churn.Eden_Space                         1024  avgt    3      43,984 ±     15,801  MB/sec
    HashBenchmark.SHA512:·gc.churn.Eden_Space.norm                    1024  avgt    3    1231,420 ±    248,295    B/op
    HashBenchmark.SHA512:·gc.churn.Survivor_Space                     1024  avgt    3       0,008 ±      0,089  MB/sec
    HashBenchmark.SHA512:·gc.churn.Survivor_Space.norm                1024  avgt    3       0,236 ±      2,357    B/op
    HashBenchmark.SHA512:·gc.count                                    1024  avgt    3     153,000               counts
    HashBenchmark.SHA512:·gc.time                                     1024  avgt    3     111,000                   ms
    HashBenchmark.SHA512                                           1048576  avgt    3   76460,153 ±  76278,885   us/op
    HashBenchmark.SHA512:·gc.alloc.rate                            1048576  avgt    3       0,061 ±      0,050  MB/sec
    HashBenchmark.SHA512:·gc.alloc.rate.norm                       1048576  avgt    3    1229,704 ±    470,103    B/op
    HashBenchmark.SHA512:·gc.count                                 1048576  avgt    3         ? 0               counts
    HashBenchmark.SHA512Reuse                                            0  avgt    3       9,715 ±      4,658   us/op
    HashBenchmark.SHA512Reuse:·gc.alloc.rate                             0  avgt    3      31,721 ±     15,037  MB/sec
    HashBenchmark.SHA512Reuse:·gc.alloc.rate.norm                        0  avgt    3      80,747 ±      0,177    B/op
    HashBenchmark.SHA512Reuse:·gc.churn.Eden_Space                       0  avgt    3      31,624 ±     24,063  MB/sec
    HashBenchmark.SHA512Reuse:·gc.churn.Eden_Space.norm                  0  avgt    3      80,482 ±     29,931    B/op
    HashBenchmark.SHA512Reuse:·gc.churn.Survivor_Space                   0  avgt    3       0,014 ±      0,387  MB/sec
    HashBenchmark.SHA512Reuse:·gc.churn.Survivor_Space.norm              0  avgt    3       0,035 ±      0,968    B/op
    HashBenchmark.SHA512Reuse:·gc.count                                  0  avgt    3     110,000               counts
    HashBenchmark.SHA512Reuse:·gc.time                                   0  avgt    3      60,000                   ms
    HashBenchmark.SHA512Reuse                                          100  avgt    3      10,346 ±      3,228   us/op
    HashBenchmark.SHA512Reuse:·gc.alloc.rate                           100  avgt    3      29,775 ±      8,895  MB/sec
    HashBenchmark.SHA512Reuse:·gc.alloc.rate.norm                      100  avgt    3      80,740 ±      0,901    B/op
    HashBenchmark.SHA512Reuse:·gc.churn.Eden_Space                     100  avgt    3      29,899 ±      9,085  MB/sec
    HashBenchmark.SHA512Reuse:·gc.churn.Eden_Space.norm                100  avgt    3      81,076 ±      3,881    B/op
    HashBenchmark.SHA512Reuse:·gc.churn.Survivor_Space                 100  avgt    3       0,014 ±      0,395  MB/sec
    HashBenchmark.SHA512Reuse:·gc.churn.Survivor_Space.norm            100  avgt    3       0,039 ±      1,063    B/op
    HashBenchmark.SHA512Reuse:·gc.count                                100  avgt    3     104,000               counts
    HashBenchmark.SHA512Reuse:·gc.time                                 100  avgt    3      65,000                   ms
    HashBenchmark.SHA512Reuse                                         1024  avgt    3      86,037 ±     67,778   us/op
    HashBenchmark.SHA512Reuse:·gc.alloc.rate                          1024  avgt    3       3,593 ±      2,786  MB/sec
    HashBenchmark.SHA512Reuse:·gc.alloc.rate.norm                     1024  avgt    3      80,874 ±      2,540    B/op
    HashBenchmark.SHA512Reuse:·gc.churn.Eden_Space                    1024  avgt    3       3,737 ±      9,078  MB/sec
    HashBenchmark.SHA512Reuse:·gc.churn.Eden_Space.norm               1024  avgt    3      84,226 ±    215,279    B/op
    HashBenchmark.SHA512Reuse:·gc.churn.Survivor_Space                1024  avgt    3       0,004 ±      0,126  MB/sec
    HashBenchmark.SHA512Reuse:·gc.churn.Survivor_Space.norm           1024  avgt    3       0,089 ±      2,722    B/op
    HashBenchmark.SHA512Reuse:·gc.count                               1024  avgt    3      13,000               counts
    HashBenchmark.SHA512Reuse:·gc.time                                1024  avgt    3      17,000                   ms
    HashBenchmark.SHA512Reuse                                      1048576  avgt    3   85592,721 ±  93743,601   us/op
    HashBenchmark.SHA512Reuse:·gc.alloc.rate                       1048576  avgt    3       0,005 ±      0,022  MB/sec
    HashBenchmark.SHA512Reuse:·gc.alloc.rate.norm                  1048576  avgt    3     104,026 ±    511,657    B/op
    HashBenchmark.SHA512Reuse:·gc.count                            1048576  avgt    3         ? 0               counts
    HmacBenchmark.HmacMD5                                                0  avgt    3      23,776 ±      2,788   us/op
    HmacBenchmark.HmacMD5:·gc.alloc.rate                                 0  avgt    3     168,033 ±     18,145  MB/sec
    HmacBenchmark.HmacMD5:·gc.alloc.rate.norm                            0  avgt    3    1047,350 ±     23,766    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space                           0  avgt    3     168,461 ±     18,335  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space.norm                      0  avgt    3    1050,012 ±     22,272    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space                       0  avgt    3       0,008 ±      0,008  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space.norm                  0  avgt    3       0,048 ±      0,051    B/op
    HmacBenchmark.HmacMD5:·gc.count                                      0  avgt    3     586,000               counts
    HmacBenchmark.HmacMD5:·gc.time                                       0  avgt    3     556,000                   ms
    HmacBenchmark.HmacMD5                                              100  avgt    3      24,288 ±     14,186   us/op
    HmacBenchmark.HmacMD5:·gc.alloc.rate                               100  avgt    3     164,079 ±     96,521  MB/sec
    HmacBenchmark.HmacMD5:·gc.alloc.rate.norm                          100  avgt    3    1043,983 ±      6,443    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space                         100  avgt    3     164,144 ±     96,400  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space.norm                    100  avgt    3    1044,399 ±     41,504    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space                     100  avgt    3       0,008 ±      0,012  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space.norm                100  avgt    3       0,051 ±      0,050    B/op
    HmacBenchmark.HmacMD5:·gc.count                                    100  avgt    3     571,000               counts
    HmacBenchmark.HmacMD5:·gc.time                                     100  avgt    3     394,000                   ms
    HmacBenchmark.HmacMD5                                             1024  avgt    3      36,519 ±     19,100   us/op
    HmacBenchmark.HmacMD5:·gc.alloc.rate                              1024  avgt    3     109,531 ±     57,619  MB/sec
    HmacBenchmark.HmacMD5:·gc.alloc.rate.norm                         1024  avgt    3    1048,073 ±      6,574    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space                        1024  avgt    3     109,811 ±     50,758  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space.norm                   1024  avgt    3    1050,825 ±     78,729    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space                    1024  avgt    3       0,006 ±      0,002  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space.norm               1024  avgt    3       0,056 ±      0,047    B/op
    HmacBenchmark.HmacMD5:·gc.count                                   1024  avgt    3     382,000               counts
    HmacBenchmark.HmacMD5:·gc.time                                    1024  avgt    3     372,000                   ms
    HmacBenchmark.HmacMD5                                          1048576  avgt    3   10922,479 ±  19041,620   us/op
    HmacBenchmark.HmacMD5:·gc.alloc.rate                           1048576  avgt    3       0,389 ±      0,671  MB/sec
    HmacBenchmark.HmacMD5:·gc.alloc.rate.norm                      1048576  avgt    3    1109,108 ±    400,007    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space                     1048576  avgt    3       0,286 ±      9,049  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Eden_Space.norm                1048576  avgt    3     837,404 ±  26461,189    B/op
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space                 1048576  avgt    3       0,028 ±      0,894  MB/sec
    HmacBenchmark.HmacMD5:·gc.churn.Survivor_Space.norm            1048576  avgt    3      82,710 ±   2613,546    B/op
    HmacBenchmark.HmacMD5:·gc.count                                1048576  avgt    3       1,000               counts
    HmacBenchmark.HmacMD5:·gc.time                                 1048576  avgt    3       2,000                   ms
    HmacBenchmark.HmacSHA1                                               0  avgt    3      29,501 ±     11,555   us/op
    HmacBenchmark.HmacSHA1:·gc.alloc.rate                                0  avgt    3     169,046 ±     63,812  MB/sec
    HmacBenchmark.HmacSHA1:·gc.alloc.rate.norm                           0  avgt    3    1306,821 ±     12,227    B/op
    HmacBenchmark.HmacSHA1:·gc.churn.Eden_Space                          0  avgt    3     169,614 ±     65,695  MB/sec
    HmacBenchmark.HmacSHA1:·gc.churn.Eden_Space.norm                     0  avgt    3    1311,198 ±      7,402    B/op
    HmacBenchmark.HmacSHA1:·gc.churn.Survivor_Space                      0  avgt    3       0,010 ±      0,017  MB/sec
    HmacBenchmark.HmacSHA1:·gc.churn.Survivor_Space.norm                 0  avgt    3       0,078 ±      0,131    B/op
    HmacBenchmark.HmacSHA1:·gc.count                                     0  avgt    3     590,000               counts
    HmacBenchmark.HmacSHA1:·gc.time                                      0  avgt    3     579,000                   ms
    HmacBenchmark.HmacSHA1                                             100  avgt    3      29,800 ±     10,449   us/op
    HmacBenchmark.HmacSHA1:·gc.alloc.rate                              100  avgt    3     167,210 ±     59,390  MB/sec
    HmacBenchmark.HmacSHA1:·gc.alloc.rate.norm                         100  avgt    3    1305,996 ±     14,044    B/op
    HmacBenchmark.HmacSHA1:·gc.churn.Eden_Space                        100  avgt    3     167,314 ±     56,439  MB/sec
    HmacBenchmark.HmacSHA1:·gc.churn.Eden_Space.norm                   100  avgt    3    1306,826 ±     38,922    B/op
    HmacBenchmark.HmacSHA1:·gc.churn.Survivor_Space                    100  avgt    3       0,011 ±      0,008  MB/sec
    HmacBenchmark.HmacSHA1:·gc.churn.Survivor_Space.norm               100  avgt    3       0,082 ±      0,039    B/op
    HmacBenchmark.HmacSHA1:·gc.count                                   100  avgt    3     583,000               counts
    HmacBenchmark.HmacSHA1:·gc.time                                    100  avgt    3     547,000                   ms
    HmacBenchmark.HmacSHA1                                            1024  avgt    3      52,111 ±      3,792   us/op
    HmacBenchmark.HmacSHA1:·gc.alloc.rate                             1024  avgt    3      96,129 ±      6,179  MB/sec
    HmacBenchmark.HmacSHA1:·gc.alloc.rate.norm                        1024  avgt    3    1312,790 ±      4,902    B/op
    HmacBenchmark.HmacSHA1:·gc.churn.Eden_Space                       1024  avgt    3      96,592 ±      0,110  MB/sec
    HmacBenchmark.HmacSHA1:·gc.churn.Eden_Space.norm                  1024  avgt    3    1319,130 ±     90,293    B/op
    HmacBenchmark.HmacSHA1:·gc.churn.Survivor_Space                   1024  avgt    3       0,010 ±      0,004  MB/sec
    HmacBenchmark.HmacSHA1:·gc.churn.Survivor_Space.norm              1024  avgt    3       0,134 ±      0,057    B/op
    HmacBenchmark.HmacSHA1:·gc.count                                  1024  avgt    3     336,000               counts
    HmacBenchmark.HmacSHA1:·gc.time                                   1024  avgt    3     237,000                   ms
    HmacBenchmark.HmacSHA1                                         1048576  avgt    3   23420,796 ±  35794,042   us/op
    HmacBenchmark.HmacSHA1:·gc.alloc.rate                          1048576  avgt    3       0,222 ±      0,329  MB/sec
    HmacBenchmark.HmacSHA1:·gc.alloc.rate.norm                     1048576  avgt    3    1357,473 ±    133,819    B/op
    HmacBenchmark.HmacSHA1:·gc.count                               1048576  avgt    3         ? 0               counts
    HmacBenchmark.HmacSHA256                                             0  avgt    3      25,530 ±     11,929   us/op
    HmacBenchmark.HmacSHA256:·gc.alloc.rate                              0  avgt    3     185,577 ±     85,230  MB/sec
    HmacBenchmark.HmacSHA256:·gc.alloc.rate.norm                         0  avgt    3    1241,463 ±     10,064    B/op
    HmacBenchmark.HmacSHA256:·gc.churn.Eden_Space                        0  avgt    3     185,687 ±     95,040  MB/sec
    HmacBenchmark.HmacSHA256:·gc.churn.Eden_Space.norm                   0  avgt    3    1242,136 ±     58,466    B/op
    HmacBenchmark.HmacSHA256:·gc.churn.Survivor_Space                    0  avgt    3       0,011 ±      0,008  MB/sec
    HmacBenchmark.HmacSHA256:·gc.churn.Survivor_Space.norm               0  avgt    3       0,074 ±      0,068    B/op
    HmacBenchmark.HmacSHA256:·gc.count                                   0  avgt    3     647,000               counts
    HmacBenchmark.HmacSHA256:·gc.time                                    0  avgt    3     519,000                   ms
    HmacBenchmark.HmacSHA256                                           100  avgt    3      25,646 ±     15,773   us/op
    HmacBenchmark.HmacSHA256:·gc.alloc.rate                            100  avgt    3     184,683 ±    113,649  MB/sec
    HmacBenchmark.HmacSHA256:·gc.alloc.rate.norm                       100  avgt    3    1240,571 ±     11,981    B/op
    HmacBenchmark.HmacSHA256:·gc.churn.Eden_Space                      100  avgt    3     185,136 ±    120,311  MB/sec
    HmacBenchmark.HmacSHA256:·gc.churn.Eden_Space.norm                 100  avgt    3    1243,564 ±     36,981    B/op
    HmacBenchmark.HmacSHA256:·gc.churn.Survivor_Space                  100  avgt    3       0,011 ±      0,024  MB/sec
    HmacBenchmark.HmacSHA256:·gc.churn.Survivor_Space.norm             100  avgt    3       0,077 ±      0,143    B/op
    HmacBenchmark.HmacSHA256:·gc.count                                 100  avgt    3     644,000               counts
    HmacBenchmark.HmacSHA256:·gc.time                                  100  avgt    3     442,000                   ms
    HmacBenchmark.HmacSHA256                                          1024  avgt    3      66,031 ±     46,263   us/op
    HmacBenchmark.HmacSHA256:·gc.alloc.rate                           1024  avgt    3      72,818 ±     48,686  MB/sec
    HmacBenchmark.HmacSHA256:·gc.alloc.rate.norm                      1024  avgt    3    1258,957 ±     36,827    B/op
    HmacBenchmark.HmacSHA256:·gc.churn.Eden_Space                     1024  avgt    3      73,017 ±     55,333  MB/sec
    HmacBenchmark.HmacSHA256:·gc.churn.Eden_Space.norm                1024  avgt    3    1262,243 ±     89,965    B/op
    HmacBenchmark.HmacSHA256:·gc.churn.Survivor_Space                 1024  avgt    3       0,008 ±      0,012  MB/sec
    HmacBenchmark.HmacSHA256:·gc.churn.Survivor_Space.norm            1024  avgt    3       0,135 ±      0,277    B/op
    HmacBenchmark.HmacSHA256:·gc.count                                1024  avgt    3     254,000               counts
    HmacBenchmark.HmacSHA256:·gc.time                                 1024  avgt    3     206,000                   ms
    HmacBenchmark.HmacSHA256                                       1048576  avgt    3   38772,775 ±  17957,802   us/op
    HmacBenchmark.HmacSHA256:·gc.alloc.rate                        1048576  avgt    3       0,128 ±      0,037  MB/sec
    HmacBenchmark.HmacSHA256:·gc.alloc.rate.norm                   1048576  avgt    3    1298,727 ±    240,241    B/op
    HmacBenchmark.HmacSHA256:·gc.count                             1048576  avgt    3         ? 0               counts
    HmacBenchmark.HmacSHA384                                             0  avgt    3      69,631 ±     93,190   us/op
    HmacBenchmark.HmacSHA384:·gc.alloc.rate                              0  avgt    3     104,457 ±    145,808  MB/sec
    HmacBenchmark.HmacSHA384:·gc.alloc.rate.norm                         0  avgt    3    1898,704 ±      7,285    B/op
    HmacBenchmark.HmacSHA384:·gc.churn.Eden_Space                        0  avgt    3     105,214 ±    150,196  MB/sec
    HmacBenchmark.HmacSHA384:·gc.churn.Eden_Space.norm                   0  avgt    3    1912,299 ±     94,916    B/op
    HmacBenchmark.HmacSHA384:·gc.churn.Survivor_Space                    0  avgt    3       0,020 ±      0,015  MB/sec
    HmacBenchmark.HmacSHA384:·gc.churn.Survivor_Space.norm               0  avgt    3       0,362 ±      0,371    B/op
    HmacBenchmark.HmacSHA384:·gc.count                                   0  avgt    3     366,000               counts
    HmacBenchmark.HmacSHA384:·gc.time                                    0  avgt    3     346,000                   ms
    HmacBenchmark.HmacSHA384                                           100  avgt    3      70,994 ±     28,905   us/op
    HmacBenchmark.HmacSHA384:·gc.alloc.rate                            100  avgt    3     102,126 ±     40,695  MB/sec
    HmacBenchmark.HmacSHA384:·gc.alloc.rate.norm                       100  avgt    3    1899,367 ±     19,536    B/op
    HmacBenchmark.HmacSHA384:·gc.churn.Eden_Space                      100  avgt    3     102,336 ±     39,793  MB/sec
    HmacBenchmark.HmacSHA384:·gc.churn.Eden_Space.norm                 100  avgt    3    1903,289 ±    123,998    B/op
    HmacBenchmark.HmacSHA384:·gc.churn.Survivor_Space                  100  avgt    3       0,018 ±      0,023  MB/sec
    HmacBenchmark.HmacSHA384:·gc.churn.Survivor_Space.norm             100  avgt    3       0,343 ±      0,393    B/op
    HmacBenchmark.HmacSHA384:·gc.count                                 100  avgt    3     356,000               counts
    HmacBenchmark.HmacSHA384:·gc.time                                  100  avgt    3     257,000                   ms
    HmacBenchmark.HmacSHA384                                          1024  avgt    3     159,688 ±    250,764   us/op
    HmacBenchmark.HmacSHA384:·gc.alloc.rate                           1024  avgt    3      46,124 ±     75,630  MB/sec
    HmacBenchmark.HmacSHA384:·gc.alloc.rate.norm                      1024  avgt    3    1921,007 ±     21,632    B/op
    HmacBenchmark.HmacSHA384:·gc.churn.Eden_Space                     1024  avgt    3      45,994 ±     77,494  MB/sec
    HmacBenchmark.HmacSHA384:·gc.churn.Eden_Space.norm                1024  avgt    3    1915,318 ±     95,195    B/op
    HmacBenchmark.HmacSHA384:·gc.churn.Survivor_Space                 1024  avgt    3       0,010 ±      0,076  MB/sec
    HmacBenchmark.HmacSHA384:·gc.churn.Survivor_Space.norm            1024  avgt    3       0,423 ±      3,405    B/op
    HmacBenchmark.HmacSHA384:·gc.count                                1024  avgt    3     160,000               counts
    HmacBenchmark.HmacSHA384:·gc.time                                 1024  avgt    3     155,000                   ms
    HmacBenchmark.HmacSHA384                                       1048576  avgt    3   77223,203 ±  67744,681   us/op
    HmacBenchmark.HmacSHA384:·gc.alloc.rate                        1048576  avgt    3       0,096 ±      0,090  MB/sec
    HmacBenchmark.HmacSHA384:·gc.alloc.rate.norm                   1048576  avgt    3    1947,990 ±    420,733    B/op
    HmacBenchmark.HmacSHA384:·gc.count                             1048576  avgt    3         ? 0               counts
    HmacBenchmark.HmacSHA512                                             0  avgt    3      53,942 ±     38,942   us/op
    HmacBenchmark.HmacSHA512:·gc.alloc.rate                              0  avgt    3     136,565 ±     96,338  MB/sec
    HmacBenchmark.HmacSHA512:·gc.alloc.rate.norm                         0  avgt    3    1928,923 ±     12,348    B/op
    HmacBenchmark.HmacSHA512:·gc.churn.Eden_Space                        0  avgt    3     137,399 ±     95,261  MB/sec
    HmacBenchmark.HmacSHA512:·gc.churn.Eden_Space.norm                   0  avgt    3    1940,747 ±    174,822    B/op
    HmacBenchmark.HmacSHA512:·gc.churn.Survivor_Space                    0  avgt    3       0,025 ±      0,007  MB/sec
    HmacBenchmark.HmacSHA512:·gc.churn.Survivor_Space.norm               0  avgt    3       0,354 ±      0,322    B/op
    HmacBenchmark.HmacSHA512:·gc.count                                   0  avgt    3     478,000               counts
    HmacBenchmark.HmacSHA512:·gc.time                                    0  avgt    3     340,000                   ms
    HmacBenchmark.HmacSHA512                                           100  avgt    3      53,844 ±     39,109   us/op
    HmacBenchmark.HmacSHA512:·gc.alloc.rate                            100  avgt    3     136,946 ±     97,394  MB/sec
    HmacBenchmark.HmacSHA512:·gc.alloc.rate.norm                       100  avgt    3    1930,504 ±      5,816    B/op
    HmacBenchmark.HmacSHA512:·gc.churn.Eden_Space                      100  avgt    3     137,688 ±    106,808  MB/sec
    HmacBenchmark.HmacSHA512:·gc.churn.Eden_Space.norm                 100  avgt    3    1940,780 ±    142,017    B/op
    HmacBenchmark.HmacSHA512:·gc.churn.Survivor_Space                  100  avgt    3       0,024 ±      0,027  MB/sec
    HmacBenchmark.HmacSHA512:·gc.churn.Survivor_Space.norm             100  avgt    3       0,338 ±      0,145    B/op
    HmacBenchmark.HmacSHA512:·gc.count                                 100  avgt    3     479,000               counts
    HmacBenchmark.HmacSHA512:·gc.time                                  100  avgt    3     362,000                   ms
    HmacBenchmark.HmacSHA512                                          1024  avgt    3     129,354 ±     80,651   us/op
    HmacBenchmark.HmacSHA512:·gc.alloc.rate                           1024  avgt    3      57,625 ±     35,591  MB/sec
    HmacBenchmark.HmacSHA512:·gc.alloc.rate.norm                      1024  avgt    3    1951,995 ±     19,237    B/op
    HmacBenchmark.HmacSHA512:·gc.churn.Eden_Space                     1024  avgt    3      57,779 ±     41,629  MB/sec
    HmacBenchmark.HmacSHA512:·gc.churn.Eden_Space.norm                1024  avgt    3    1956,966 ±    258,531    B/op
    HmacBenchmark.HmacSHA512:·gc.churn.Survivor_Space                 1024  avgt    3       0,012 ±      0,084  MB/sec
    HmacBenchmark.HmacSHA512:·gc.churn.Survivor_Space.norm            1024  avgt    3       0,397 ±      3,151    B/op
    HmacBenchmark.HmacSHA512:·gc.count                                1024  avgt    3     201,000               counts
    HmacBenchmark.HmacSHA512:·gc.time                                 1024  avgt    3     158,000                   ms
    HmacBenchmark.HmacSHA512                                       1048576  avgt    3   84296,359 ±  45433,441   us/op
    HmacBenchmark.HmacSHA512:·gc.alloc.rate                        1048576  avgt    3       0,089 ±      0,030  MB/sec
    HmacBenchmark.HmacSHA512:·gc.alloc.rate.norm                   1048576  avgt    3    1983,132 ±    505,931    B/op
    HmacBenchmark.HmacSHA512:·gc.count                             1048576  avgt    3         ? 0               counts
    HmacBenchmark.HmacSHA512Short                                        0  avgt    3      32,260 ±      8,071   us/op
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate                         0  avgt    3     150,472 ±     38,535  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate.norm                    0  avgt    3    1272,341 ±      5,060    B/op
    HmacBenchmark.HmacSHA512Short:·gc.churn.Eden_Space                   0  avgt    3     150,635 ±     32,932  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.churn.Eden_Space.norm              0  avgt    3    1273,750 ±     45,640    B/op
    HmacBenchmark.HmacSHA512Short:·gc.churn.Survivor_Space               0  avgt    3       0,010 ±      0,008  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.churn.Survivor_Space.norm          0  avgt    3       0,085 ±      0,087    B/op
    HmacBenchmark.HmacSHA512Short:·gc.count                              0  avgt    3     524,000               counts
    HmacBenchmark.HmacSHA512Short:·gc.time                               0  avgt    3     496,000                   ms
    HmacBenchmark.HmacSHA512Short                                      100  avgt    3      34,805 ±     29,154   us/op
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate                       100  avgt    3     139,812 ±    117,349  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate.norm                  100  avgt    3    1273,944 ±      1,471    B/op
    HmacBenchmark.HmacSHA512Short:·gc.churn.Eden_Space                 100  avgt    3     139,983 ±    118,561  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.churn.Eden_Space.norm            100  avgt    3    1275,487 ±     10,133    B/op
    HmacBenchmark.HmacSHA512Short:·gc.churn.Survivor_Space             100  avgt    3       0,010 ±      0,014  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.churn.Survivor_Space.norm        100  avgt    3       0,090 ±      0,136    B/op
    HmacBenchmark.HmacSHA512Short:·gc.count                            100  avgt    3     488,000               counts
    HmacBenchmark.HmacSHA512Short:·gc.time                             100  avgt    3     342,000                   ms
    HmacBenchmark.HmacSHA512Short                                     1024  avgt    3      70,570 ±     57,699   us/op
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate                      1024  avgt    3      69,774 ±     54,560  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate.norm                 1024  avgt    3    1289,010 ±     18,803    B/op
    HmacBenchmark.HmacSHA512Short:·gc.churn.Eden_Space                1024  avgt    3      70,144 ±     59,564  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.churn.Eden_Space.norm           1024  avgt    3    1295,701 ±     80,675    B/op
    HmacBenchmark.HmacSHA512Short:·gc.churn.Survivor_Space            1024  avgt    3       0,008 ±      0,014  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.churn.Survivor_Space.norm       1024  avgt    3       0,148 ±      0,172    B/op
    HmacBenchmark.HmacSHA512Short:·gc.count                           1024  avgt    3     244,000               counts
    HmacBenchmark.HmacSHA512Short:·gc.time                            1024  avgt    3     226,000                   ms
    HmacBenchmark.HmacSHA512Short                                  1048576  avgt    3   36350,086 ±  12821,168   us/op
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate                   1048576  avgt    3       0,139 ±      0,043  MB/sec
    HmacBenchmark.HmacSHA512Short:·gc.alloc.rate.norm              1048576  avgt    3    1329,882 ±    220,775    B/op
    HmacBenchmark.HmacSHA512Short:·gc.count                        1048576  avgt    3         ? 0               counts

