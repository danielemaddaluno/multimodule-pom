package io.multimodule.ejb.i18n;

import io.multimodule.ejb.configs.ConfigsUtils;

class MessageBundleKey {

	public final String msgsBundle;
	public final String key;

	private MessageBundleKey(String msgsBundle, String key) {
		this.msgsBundle = msgsBundle;
		this.key = key;
	}

	public static MessageBundleKey of(MessageBundle msgsBundle, String key) {
		return new MessageBundleKey(msgsBundle.bundleName, key);
	}

	public static MessageBundleKey ofWithBuildType(MessageBundle msgsBundle, String key) {
		return new MessageBundleKey(msgsBundle.bundleName + "_" + ConfigsUtils.BUILD_TYPE.toString().toLowerCase(), key);
	}

}
