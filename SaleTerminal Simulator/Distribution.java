// package poisson;

import java.util.Random;
import java.lang.Math;

/**
 * Distribution.java
 * This class generates various random variables for distributions
 * not directly supported in Java
 */

public class Distribution extends Random
{
    public double nextExponential(double b)
    {
        double randx;
        double result;
        randx = nextDouble();
        result = -1*b*Math.log(randx);
        return result+1;
    }
}
