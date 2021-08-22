package io.multimodule.data.domain.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class StringUtils {
	public static String join(String delim, Object... elements) {
		return join(delim, Arrays.asList(elements));
	}
	
	public static String join(String delim, Collection<?> col) {
		List<Object> list = new ArrayList<>();
		for(Object o : col) {
			if(o != null && !o.toString().trim().isEmpty())
				list.add(o);
		}
		return org.apache.commons.lang3.StringUtils.join(list, delim);
	}
	
	/**
	 * @param skipQuestionMark
	 * @param params the number of parameters must be odd
	 * @return
	 */
	public static String urlParameters(boolean skipQuestionMark, String... params) {
		String urlParams = org.apache.commons.lang3.StringUtils.EMPTY;
		if(params != null && params.length != 0 && (params.length % 2) == 0) {
			String questionMark = skipQuestionMark ? org.apache.commons.lang3.StringUtils.EMPTY : "?";
			for(int i= 0; i<params.length; i++) {
				urlParams += i== 0 ? questionMark : "&";
				String key = params[i];
				String value = params[++i];
				urlParams += key + "=" + value;
			}
		}
		
		return urlParams;
	}
}
