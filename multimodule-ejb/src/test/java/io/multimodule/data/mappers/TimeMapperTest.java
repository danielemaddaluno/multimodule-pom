package io.multimodule.data.mappers;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mybatis.cdi.Transactional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional(rollbackOnly=true)
public class TimeMapperTest extends MapperTest{
	@Test
	public void t1_select_one(){
		System.out.println(timeMapper.now());
	}
}
