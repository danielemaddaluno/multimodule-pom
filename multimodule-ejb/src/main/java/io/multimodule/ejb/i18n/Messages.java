package io.multimodule.ejb.i18n;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

abstract class Messages {
	
	private static String getString(ResourceBundle bundle, String key) {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	private static String getMessageSimpleNoLocale(MessageBundleKey msgsBundleKey) {
		ResourceBundle bundle = ResourceBundle.getBundle(msgsBundleKey.msgsBundle);
		return getString(bundle, msgsBundleKey.key);
	}
	
	private static String getMessageSimple(Locale locale, MessageBundleKey msgsBundleKey) {
		ResourceBundle bundle = ResourceBundle.getBundle(msgsBundleKey.msgsBundle, locale);
		return getString(bundle, msgsBundleKey.key);
	}

	public static String getMessage(Locale locale, MessageBundleKey msgsBundleKey, Object... params) {
		String messageSimple = getMessageSimple(locale, msgsBundleKey);
		String message = params != null && params.length > 0 ? MessageFormat.format(messageSimple, params)
				: messageSimple;
		return message;
	}
	
	public static String getMessage(MessageBundleKey msgsBundleKey, Object... params) {
		String messageSimple = getMessageSimpleNoLocale(msgsBundleKey);
		String message = params != null && params.length > 0 ? MessageFormat.format(messageSimple, params)
				: messageSimple;
		return message;
	}
	
	public static String getMessageWithDefault(MessageBundleKey msgsBundleKey, String defaultValue, Object... params) {
		String messageSimple = getMessageSimpleNoLocale(msgsBundleKey);
		messageSimple = messageSimple != null ? messageSimple : defaultValue;
		String message = params != null && params.length > 0 ? MessageFormat.format(messageSimple, params)
				: messageSimple;
		return message;
	}
	
	public static String getI18n(Locale locale, MessageI18nKey msg, Object... params) {
		MessageBundleKey mbk = MessageBundleKey.of(MessageBundle.MESSAGES, msg.key);
		String message = getMessage(locale, mbk, params);
		return message;
	}
	
	public static String getI18n(MessageI18nKey msg, Object... params) {
		MessageBundleKey mbk = MessageBundleKey.of(MessageBundle.MESSAGES, msg.key);
		String message = getMessage(mbk, params);
		return message;
	}
	
	public static String fromUnicodeToUtf8(String unicode) {
	    try {
	    	byte[] utf8 = unicode.getBytes("ISO-8859-1");
			return new String(utf8, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return StringUtils.EMPTY;
		}
	}
	
	public static String fromUtf8ToUnicode(String utf8) {
	    try {
	    	byte[] unicode = utf8.getBytes("UTF-8");
			return new String(unicode, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			return StringUtils.EMPTY;
		}
	}
	
}
