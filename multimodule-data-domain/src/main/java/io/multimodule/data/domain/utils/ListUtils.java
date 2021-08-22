package io.multimodule.data.domain.utils;

import java.util.List;

public abstract class ListUtils {
	
	/**
	 * Null safe implementation of List.size method
	 * @param list
	 * @return real list size
	 */
	public static Integer size(List<?> list) {
		return list != null ? list.size() : 0;
	}
}
