package io.multimodule.data.typehandlers.bigmoney;

import org.joda.money.CurrencyUnit;

import io.multimodule.data.typehandlers.GenericFromToStringHandler;

public class CurrencyUnitTypeHandler extends GenericFromToStringHandler<CurrencyUnit> {
	public CurrencyUnitTypeHandler() {
		super(new FromString<CurrencyUnit>() {
			@Override
			public String toString(CurrencyUnit value) {
				return value.getCode();
			}

			@Override
			public CurrencyUnit fromString(String currencyCode) {
				if (currencyCode != null) return CurrencyUnit.of(currencyCode);
				return null;
			}
		});
	}
}
