package io.multimodule.data.mappers;

import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

@Mapper
public interface SetNamesMapper {
	
	public static final String SET_NAMES_UTF8MB4 = "SET NAMES utf8mb4";
	public static final String SET_NAMES_UTF8 = "SET NAMES utf8";

	@Select(SET_NAMES_UTF8MB4)
	public void utf8mb4();

	@Select(SET_NAMES_UTF8)
	public void utf8();

}
