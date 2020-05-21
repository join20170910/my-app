package com.mycompany.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author john
 */

@BenchmarkMode(Mode.All)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 4, timeUnit = TimeUnit.SECONDS)
@Threads(4)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BuilderVsBuffer {

    @Benchmark
    public void builder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("i");
        }
    }

    @Benchmark
    public void buffer() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            sb.append("i");
        }
    }

    public static void main(String[] args) throws RunnerException {
    Options options =
        new OptionsBuilder()
            .include(BuilderVsBuffer.class.getSimpleName())
            .output("E:\\demo\\logs\\BuilderVsBuffer.log")
            .build();
        new Runner(options).run();
    }
}
