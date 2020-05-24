/*
package com.mycompany.jmh;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;


*/
/**
 *
 BenchmarkMode，使用模式，默认是Mode.Throughput，表示吞吐量,其他参数还有AverageTime，表示每次执行时间，SampleTime表示采样时间，SingleShotTime表示只运行一次，用于测试冷启动消耗时间，All表示统计前面的所有指标
 Warmup 配置预热次数，默认是每次运行1秒，运行10次，我们的例子是运行3次
 Measurement 配置执行次数，本例是一次运行5秒，总共运行3次。在性能对比时候，采用默认1秒即可，如果我们用jvisualvm做性能监控，我们可以指定一个较长时间运行。
 Threads 配置同时起多少个线程执行，默认值世 Runtime.getRuntime().availableProcessors()，本例启动1个线程同时执行
 Fork，代表启动多个单独的进程分别测试每个方法，我们这里指定为每个方法启动一个进程。
 OutputTimeUnit 统计结果的时间单元，这个例子TimeUnit.SECONDS，我们在运行后会看到输出结果是统计每秒的吞吐量

 *//*

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JMHSample_01_HelloWorldTest {


    @Benchmark
    public static void  testStringKey(){
        //优化前的代码
    }
    @Benchmark
    public static void  testObjectKey(){
        //要测试的优化后代码
    }


    public void testJMH() throws RunnerException {

    System.out.println("start ");

        Options opt = new OptionsBuilder()
                .include(JMHSample_01_HelloWorldTest.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
*/
