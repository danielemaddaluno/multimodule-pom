package io.multimodule.rest.utils.jackson;

import javax.ws.rs.ext.ParamConverter;

import org.joda.money.BigMoney;

public class BigMoneyParamConverter implements ParamConverter<BigMoney> {

	@Override
    public BigMoney fromString(String value) {
        if(value != null && !value.isEmpty() && !value.equals("null"))
            return BigMoney.parse(value);
		return null;
    }

	@Override
    public String toString(BigMoney value) {
        return value != null ? value.toString() : null;
    }

}