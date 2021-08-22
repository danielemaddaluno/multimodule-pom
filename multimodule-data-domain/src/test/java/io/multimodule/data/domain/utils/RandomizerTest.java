package io.multimodule.data.domain.utils;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

public class RandomizerTest {

	@Test
	public void testNextRandomInt(){
		int min = 0;
		int max = 10;
		for(int i = 0; i<1000; i++){
			int rand = Randomizer.nextInt(min, max);
			assertTrue(rand <= max);
			assertTrue(rand >= min);
		}
	}
	
	@Test
	public void testNextRandomBigDecimal(){
		BigDecimal min = new BigDecimal(0);
		BigDecimal max = new BigDecimal(1);
		for(int i = 0; i<100000; i++){
			BigDecimal rand = Randomizer.nextBigDecimal(min, max, 3);
			assertTrue(rand.compareTo(min) >= 0);
			assertTrue(rand.compareTo(max) <= 0);
		}
	}
	
}
