package io.multimodule.data.domain.utils.jackson;
import java.io.IOException;

import org.joda.money.BigMoney;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class BigMoneyDeserializerTypeHandler extends JsonDeserializer<BigMoney> {

	@Override
	public BigMoney deserialize(JsonParser jp, DeserializationContext ds) throws IOException, JsonProcessingException {
        String value = jp.getValueAsString();
        if(value != null && !value.isEmpty() && !value.equals("null"))
            return BigMoney.parse(value);
		return null;
	}

	@Override
	public Class<BigMoney> handledType() {
		return BigMoney.class;
	}
}