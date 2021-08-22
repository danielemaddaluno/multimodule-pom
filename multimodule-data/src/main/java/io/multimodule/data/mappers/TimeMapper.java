package io.multimodule.data.mappers;

import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;

@Mapper
public interface TimeMapper {
	@Select("SELECT NOW()")
	public String now();
}
