package io.multimodule.ejb.i18n;

enum MessageBundle {
	CONFIGS("application.configs"),
	MESSAGES("i18n.messages");
	
	public final String bundleName;
	
	MessageBundle(String bundleName) {
		this.bundleName = bundleName;
	}
	
}
