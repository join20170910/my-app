package com.mycompany.jmh;

import com.mycompany.app.Ps;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import samples.JMHSample_27_Params;

/**
 * JMH的基本概念
 *
 * <p>Warmup 预热，由于JVM中对于特定代码会存在优化（本地化），预热对于测试结果很重要
 *
 * @warmup(iterations = 1,time = 3)在JVM启动的时候调用 1 次(也可以调用多次),即预热多少次，每次多少秒，这里指定的就是预热1次，每次3秒。(线程之间间隔3秒)
 *     Mesurement 总共执行多少次测试 Timeout，默认每次迭代在10分钟之内完成，完不成就是超时了。 Threads 线程数，由fork指定，@Fork(5)，指定5个线程测试。
 *     BenchmarkMode
 *     基准测试的模式，@BenchmarkMode(Mode.Throughput)，基准测试的模式，用的最多的就是Mode.Throughput，表示吞吐量(一秒能执行多少次)，(还有好多其他的)
 *     Benchmark 测试哪一段代码，如果只写了这一个注解，默认会执行10秒。 Measurement,@Measurement(iterations = 1,time =
 *     3)，整个测试测试多少遍，一般设置的比较长。
 */

@State(Scope.Benchmark)
public class TestPS {

  @Benchmark
  @Warmup(iterations = 1, time = 3)
  @Fork(5) // 指定5个线程
  @BenchmarkMode(Mode.Throughput) // 吞吐量
  @Measurement(iterations = 1, time = 3)
  public void testForEach() {
    // 运行后，通过qps可以发现，parallel性能高，因为parallel的 ops/s 我的电脑能到一秒4次左右，而foreach不到1次
    // PS.foreach();
    Ps.parallel();
  }


    public static void main(String[] args) throws RunnerException {
      Options opt = new OptionsBuilder()
              .include(TestPS.class.getSimpleName())
//                .param("arg", "41", "42") // Use this to selectively constrain/override parameters
              .build();

      new Runner(opt).run();
  }
}
