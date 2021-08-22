package io.multimodule.data.domain.utils;

import static org.junit.Assert.assertEquals;

import org.joda.money.CurrencyUnit;
import org.junit.Test;

public class CurrencySymbolTest {
	@Test
	public void getEurSymbol() throws Exception {
		assertEquals("€", CurrencySymbol.getCurrencySymbol(CurrencyUnit.EUR));
	}
	
	@Test
	public void getUsdSymbol() throws Exception {
		assertEquals("$", CurrencySymbol.getCurrencySymbol(CurrencyUnit.USD));
	}
	
	@Test
	public void getGbpSymbol() throws Exception {
		assertEquals("£", CurrencySymbol.getCurrencySymbol(CurrencyUnit.GBP));
	}
	
	@Test
	public void getChfSymbol() throws Exception {
		assertEquals("$", CurrencySymbol.getCurrencySymbol(CurrencyUnit.CHF));
	}
}
