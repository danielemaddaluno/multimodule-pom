package io.multimodule.ejb.configs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import io.multimodule.ejb.i18n.MessageString;

public abstract class ConfigsUtils {
	
	public static final String VERSION_NAME = MessageString.CONFIG_APPLICATION_VERSION.get();
	public static final boolean DEBUG = !isRelease();
	public static final BuildType BUILD_TYPE = DEBUG ? BuildType.DEBUG : BuildType.RELEASE;
	
	private static boolean isRelease() {
		String buildType = System.getProperty("build.type");
		boolean release1 = buildType != null && buildType.trim().contains("Release");
		boolean release2 = VERSION_NAME.contains("Release");
		return release1 || release2;
	}

	public static boolean isSuperAdmin(String email) {
		String adminsProp = MessageString.CONFIG_APPLICATION_SUPER_ADMINS_KEY.get();
		adminsProp = adminsProp != null ? adminsProp : StringUtils.EMPTY;
		Set<String> admins = new HashSet<String>(
				Arrays.asList(adminsProp.replaceAll(StringUtils.SPACE, StringUtils.EMPTY).split(",")));
		return admins.contains(email);
	}
	
}
