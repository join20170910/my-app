package com.mycompany.jmh;

import com.mycompany.app.Ps;
import org.openjdk.jmh.annotations.Benchmark;

public class PsTest {

    @Benchmark
    public void testForEach(){
        Ps.foreach();
    }
}
