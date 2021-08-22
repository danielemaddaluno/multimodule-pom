package io.multimodule.data.domain.utils;

import java.util.HashMap;
import java.util.Map;

import org.joda.money.CurrencyUnit;

public class CurrencySymbol {
	public final static String EUR = "€";
	public final static String GBP = "£";
	public final static String USD = "$";
	public final static String JPY = "¥";

	private static Map<CurrencyUnit, String> currencySymbolMap;
	static {
		currencySymbolMap = new HashMap<CurrencyUnit, String>();
		currencySymbolMap.put(CurrencyUnit.EUR, EUR);
		currencySymbolMap.put(CurrencyUnit.GBP, GBP);
		currencySymbolMap.put(CurrencyUnit.USD, USD);
		currencySymbolMap.put(CurrencyUnit.JPY, JPY);
	}
	
	public static String getCurrencySymbol(String currencyCode) {
		CurrencyUnit currencyUnit = getCurrencyUnitFromCurrencyCode(currencyCode);
		return getCurrencySymbol(currencyUnit);
	}

	public static String getCurrencySymbol(CurrencyUnit currencyUnit) {
		String symbol = currencySymbolMap.get(currencyUnit);
		return symbol == null ? USD : symbol;
	}

	private static CurrencyUnit getCurrencyUnitFromCurrencyCode(String currencyCode) {
		try {
			return currencyCode != null ? CurrencyUnit.of(currencyCode.toUpperCase()) : CurrencyUnit.EUR;
		} catch (Exception e) {
			return CurrencyUnit.EUR;
		}
	}
}