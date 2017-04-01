package com.fourcasters.finallyfinal;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by ivan on 19/03/17.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
public class FinalBenchmark {

    private static final Random random = new Random(10000);

    @State(Scope.Benchmark)
    public static class InnerLocalFinalVsNonFinal {

        private final int[] rand;
        private final String[] randS;

        public InnerLocalFinalVsNonFinal() {
            rand = random.ints(10000).toArray();
            randS = new String[rand.length];
            for (int i = 0; i < rand.length; i++) {
                String s = String.valueOf(rand[i]);
                s.hashCode();
                randS[i] = s;
            }
            "Hello".hashCode();
            "World".hashCode();
        }

        public InnerLocalFinalVsNonFinal(int[] array) {
            rand = Arrays.copyOf(array, array.length);
            randS = new String[rand.length];
            for (int i = 0; i < rand.length; i++) {
                String s = String.valueOf(rand[i]);
                s.hashCode();
                randS[i] = s;
            }
            "Hello".hashCode();
            "World".hashCode();
        }

        public int addMeFinal(int randIndex) {
            final int x = rand[randIndex];
            final String y = randS[randIndex];
            return x + y.hashCode();
        }
        public int addMeNonFinal(int randIndex) {
            int x = rand[randIndex];
            String y = randS[randIndex];
            return x + y.hashCode();
        }
        public int addMeFinalStaticString(int randIndex) {
            final int x = rand[randIndex];
            final String y = "Hello";
            return x + y.hashCode();
        }
        public int addMeNonFinalStaticString(int randIndex) {
            int x = rand[randIndex];
            String y = "Hello";
            return x + y.hashCode();
        }
        public int addMeFinalStaticStringAndStaticInt(int randIndex) {
            final int x = 121;
            final String y = "Hello";
            return x + y.hashCode();
        }
        public int addMeNonFinalStaticStringAndStaticInt(int randIndex) {
            int x = 73;
            String y = "Hello";
            return x + y.hashCode();
        }

    }

    @State(Scope.Benchmark)
    public static class ArrayIndex {
        private int value = random.nextInt(10000);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int callFinal(ArrayIndex arrayIndex, InnerLocalFinalVsNonFinal testObject) {
        final int x = testObject.rand[arrayIndex.value];
        final String y = testObject.randS[arrayIndex.value];
        return x + y.hashCode();
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int callFinalNonFinal(ArrayIndex arrayIndex, InnerLocalFinalVsNonFinal testObject) {
        int x = testObject.rand[arrayIndex.value];
        String y = testObject.randS[arrayIndex.value];
        return x + y.hashCode();
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int callFinalStaticString(ArrayIndex arrayIndex, InnerLocalFinalVsNonFinal testObject) {
        final int x = testObject.rand[arrayIndex.value];
        final String y = "Hello";
        return x + y.hashCode();
    }

    @org.openjdk.jmh.annotations.Benchmark
    public int callNonFinalStaticString(ArrayIndex arrayIndex, InnerLocalFinalVsNonFinal testObject) {
        int x = testObject.rand[arrayIndex.value];
        String y = "Hello";
        return x + y.hashCode();
    }
}
