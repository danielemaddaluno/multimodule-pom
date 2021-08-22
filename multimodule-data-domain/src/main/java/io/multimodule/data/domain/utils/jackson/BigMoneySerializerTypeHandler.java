package io.multimodule.data.domain.utils.jackson;

import java.io.IOException;

import org.joda.money.BigMoney;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BigMoneySerializerTypeHandler extends JsonSerializer<BigMoney> {

    @Override
    public void serialize(BigMoney value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        String stringValue = value != null ? value.toString() : null;
        if(stringValue != null) {
            jgen.writeString(stringValue);
        } else {
            jgen.writeNull();
        }
    }

    @Override
    public Class<BigMoney> handledType() {
        return BigMoney.class;
    }
}