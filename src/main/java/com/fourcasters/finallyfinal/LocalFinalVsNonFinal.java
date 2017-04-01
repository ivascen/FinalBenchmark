package com.fourcasters.finallyfinal;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by ivan on 18/03/17.
 */
public class LocalFinalVsNonFinal {

    private final int[] rand;
    private final String[] randS;
    private static int STATIC_VALUE = 1;

    public LocalFinalVsNonFinal(int[] array) {
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
