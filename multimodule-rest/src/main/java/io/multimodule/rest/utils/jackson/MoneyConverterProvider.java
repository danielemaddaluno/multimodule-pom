package io.multimodule.rest.utils.jackson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.joda.money.BigMoney;
import org.joda.time.DateTime;

/**
 * https://stackoverflow.com/questions/37041353/jaxrs-could-not-find-my-custom-deserializers-for-joda-money-type/37045744?noredirect=1#comment61674458_37045744
 * 
 * @author madx
 *
 */
@Provider
public class MoneyConverterProvider implements ParamConverterProvider {

	private final BigMoneyParamConverter bigMoneyConverter = new BigMoneyParamConverter();
	private final DateTimeParamConverter dateTimeConverter = new DateTimeParamConverter();

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.equals(BigMoney.class))
			return (ParamConverter<T>) bigMoneyConverter;
		if (rawType.equals(DateTime.class))
			return (ParamConverter<T>) dateTimeConverter;
		return null;
	}

}