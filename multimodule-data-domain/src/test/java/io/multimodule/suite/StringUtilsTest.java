package io.multimodule.suite;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.multimodule.data.domain.utils.StringUtils;

public class StringUtilsTest {
	@Test
	public void testConcat() throws Exception {
		String actual = StringUtils.join("+", "fasd", null, "asd");
		assertEquals("fasd+asd", actual);
	}
	
}
