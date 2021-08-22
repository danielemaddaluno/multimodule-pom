package io.multimodule.data.domain.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Randomizer {
	// Declare as class variable so that it is not re-seeded every call
    private static Random random = new Random();
    
    /**
     * Returns a psuedo-random number between min and max (both inclusive)
     * @param min Minimim value
     * @param max Maximim value. Must be greater than min.
     * @return Integer between min and max (both inclusive)
     * @see java.util.Random#nextInt(int)
     */
	public static int nextInt(int min, int max) {
		// nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
		return random.nextInt((max - min) + 1);
	}
	
	 /**
     * Constructs a randomly generated BigInteger, uniformly distributed over
     * the range 0 to (2<sup>{@code numBits}</sup> - 1), inclusive.
     * The uniformity of the distribution assumes that a fair source of random
     * bits is provided in {@code rnd}.  Note that this constructor always
     * constructs a non-negative BigInteger.
     *
     * @param  numBits maximum bitLength of the new BigInteger.
     * @param  rnd source of randomness to be used in computing the new
     *         BigInteger.
     * @param  scale of the decimals
     * @throws IllegalArgumentException {@code numBits} is negative.
     * @see #bitLength()
     */
    public static BigInteger nextBigInteger(int numBits) {
        return new BigInteger(numBits, random);
    }
    
    public static BigDecimal nextBigDecimal(int minI, int maxI, int scale) {
    	BigDecimal min = new BigDecimal(minI);
		BigDecimal max = new BigDecimal(maxI);
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    
    public static BigDecimal nextBigDecimal(BigDecimal min, BigDecimal max, int scale) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

}
