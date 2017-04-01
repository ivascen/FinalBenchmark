package com.fourcasters;

import com.fourcasters.finallyfinal.LocalFinalVsNonFinal;

import java.util.Random;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Random random = new Random();
        int[] rand = random.ints(10000).toArray();
        LocalFinalVsNonFinal testObject = new LocalFinalVsNonFinal(rand);
        int index = random.nextInt(10000);
        int x = testObject.addMeFinal(index);
        int y = testObject.addMeNonFinal(index);
        System.out.println(x + " should be equal to " + y);
        x = testObject.addMeFinalStaticString(index);
        y = testObject.addMeNonFinalStaticString(index);
        System.out.println(x + " should be equal to " + y);
        x = testObject.addMeFinalStaticStringAndStaticInt(index);
        y = testObject.addMeNonFinalStaticStringAndStaticInt(index);
        System.out.println(x + " should be equal to " + y);
    }
}
