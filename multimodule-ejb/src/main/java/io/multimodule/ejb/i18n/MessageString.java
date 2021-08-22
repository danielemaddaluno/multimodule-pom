package io.multimodule.ejb.i18n;

/**
 * Se useBuildType = false --> cerca in configs.properties e lo trova sicuramente
 * Se useBuildType = true  --> cerca in configs_build_type.properties e lo trova forse (il release forse, il debug sempre)
 *  - nel caso di DEBUG   va a guardare in configs_debug.properties
 *  - nel caso di RELEASE va a guardare in configs_release.properties, se non lo trova va a guardare nelle proprità di sistema
 * 
 * Il file di debug ci sarà sempre e sarà committato, il file di release conterrà alcune credenziali
 * e non sarà committato, ne visibile agli altri sviluppatori, sarà molto probabilmente configurato
 * nell'ambiente di wildfly di jelastic.
 * @author madx
 *
 */
public enum MessageString {

	CONFIG_APPLICATION_VERSION(MessageBundle.CONFIGS, "application_version", false),
	CONFIG_APPLICATION_SUPER_ADMINS_KEY(MessageBundle.CONFIGS, "application_super_admins", false),
	CONFIG_EMAIL_FROM(MessageBundle.CONFIGS, "email_from", false),
	CONFIG_EMAIL_USERNAME_KEY(MessageBundle.CONFIGS, "email_username", false),
	CONFIG_EMAIL_PASSWORD_KEY(MessageBundle.CONFIGS, "email_password", false),
	CONFIG_RECIPIENT_KEY_DASHBOARD_CONTACT_US(MessageBundle.CONFIGS, "email_recipient_contact_us", false),
	CONFIG_RECIPIENT_KEY_LOCATION_FORM(MessageBundle.CONFIGS, "email_recipient_location_form", false);

	private final MessageBundle messageBundle;
	private final String key;
	private final boolean useBuildType;

	MessageString(MessageBundle messageBundle, String key, boolean useBuildType) {
		this.messageBundle = messageBundle;
		this.key = key;
		this.useBuildType = useBuildType;
	}

	public String get() {
		if (this.useBuildType) {
			MessageBundleKey msk = MessageBundleKey.ofWithBuildType(this.messageBundle, this.key);
			String defaultValue = System.getProperty(this.key);
			return Messages.getMessageWithDefault(msk, defaultValue);
		} else {
			MessageBundleKey msk = MessageBundleKey.of(this.messageBundle, this.key);
			return Messages.getMessage(msk);
		}
	}
	
}
