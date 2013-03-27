package org.fxone.ui.model.res.impl;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.events.AbstractNotification;
import org.fxone.ui.model.res.ResourceProvider;
import org.fxone.ui.model.res.cmd.ResourceRequest;

@Singleton
public final class ResourceProviderImpl implements ResourceProvider {

	private Logger LOGGER = Logger.getLogger(ResourceProviderImpl.class);

	private static final String ICON_BASE = "/img/";
	private static final String BASE16 = "16x16/";
	private static final String BASE32 = "32x32/";
	private static final String BASE48 = "48x48/";
	private static final String BASE64 = "64x64/";
	private static final String BASE128 = "128x128/";
	private static final String BASESCALABLE = "scalable/";

	@Override
	public String getMessage(String key, Locale locale, Object... contextData) {
		return getMessage("translations", key, locale, contextData);
	}

	@Override
	public String getMessage(String family, String key, Locale locale,
			Object... contextData) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		try {
			String msg = null;
			String accessKey = family + '.' + key;
			if (family != null && bundle.containsKey(accessKey)) {
				msg = bundle.getString(accessKey);
				return MessageFormat.format(msg, contextData);
			}
			return MessageFormat.format(bundle.getString(key), contextData);
		} catch (MissingResourceException e) {
			LOGGER.debug("Text resource in 'i18n/translation' for key '" + key
					+ "' not found, returning placeholder.");
			if (contextData != null && contextData.length > 0) {
				return '<' + key + ',' + locale + ">("
						+ Arrays.toString(contextData) + ')';
			}
			return '<' + key + ',' + locale + '>';
		}
	}

	public void notified(AbstractNotification n) {
		if (ResourceRequest.NOTIF_TYPE.isMatching(n)) {
			ResourceRequest req = (ResourceRequest) n;
			String family = req.getFamily();
			String key = req.getKey();
			Locale locale = Locale.getDefault();
			if (req.getLocale() != null) {
				locale = req.getLocale();
			}
			Object[] contextData = req.getContextData();
			if (contextData == null) {
				contextData = new Object[0];
			}
			n.setResult(getMessage(family, key, locale, contextData));
			n.setCompleted();
		}
	}

	@Override
	public String getIconScalable(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".iconScalable";
		if (family != null && bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = family + '.' + key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASESCALABLE + path;
		}
		accessKey = key + ".iconScalable";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASESCALABLE + path;
		}
		LOGGER.debug("Icon(scalable) in 'i18n/ui' for key '" + key
				+ "' not found, returning null.");
		return null;
	}

	@Override
	public String getIcon16(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".icon16";
		if (family != null && bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = family + '.' + key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE16 + path;
		}
		accessKey = key + ".icon16";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE16 + path;
		}
		LOGGER.debug("Icon(16) in 'i18n/ui' for key '" + key
				+ "' not found, delegating to scalable...");
		return getIconScalable(family, key, locale);
	}

	@Override
	public String getIcon32(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".icon32";
		if (family != null && bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = family + '.' + key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE32 + path;
		}
		accessKey = key + ".icon32";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE32 + path;
		}
		LOGGER.debug("Icon(32) in 'i18n/ui' for key '" + key
				+ "' not found, delegating to 16...");
		return getIcon16(family, key, locale);
	}

	@Override
	public String getIcon48(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".icon48";
		if (family != null && bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = family + '.' + key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE48 + path;
		}
		accessKey = key + ".icon48";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE48 + path;
		}
		LOGGER.debug("Icon(48) in 'i18n/ui' for key '" + key
				+ "' not found, delegating to 32...");
		return getIcon32(family, key, locale);
	}

	@Override
	public String getIcon64(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".icon64";
		if (family != null && bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = family + '.' + key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE64 + path;
		}
		accessKey = key + ".icon64";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE64 + path;
		}
		LOGGER.debug("Icon(64) in 'i18n/ui' for key '" + key
				+ "' not found, delegating to 48...");
		return getIcon48(family, key, locale);
	}

	@Override
	public String getIcon128(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".icon128";
		if (family != null && bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = family + '.' + key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE128 + path;
		}
		accessKey = key + ".icon128";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return path;
		}
		accessKey = key + ".icon";
		if (bundle.containsKey(accessKey)) {
			String path = bundle.getString(accessKey);
			return ICON_BASE + BASE128 + path;
		}
		LOGGER.debug("Icon(128) in 'i18n/ui' for key '" + key
				+ "' not found, delegating to 64...");
		return getIcon64(family, key, locale);
	}

	@Override
	public String getImage(String family, String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(getBundleName(family),
				locale);
		String accessKey = family + '.' + key + ".image";
		if (family != null && bundle.containsKey(accessKey)) {
			return bundle.getString(accessKey);
		}
		accessKey = key + ".image";
		if (bundle.containsKey(accessKey)) {
			return bundle.getString(accessKey);
		}
		LOGGER.debug("Image in 'i18n/ui' for key '" + key + "' not found");
		return null;
	}

	@Override
	public String getName(Class<?> adapterClass, Locale locale) {
		String accessKey = adapterClass.getName() + ".name";
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(
					getBundleName("classes"), locale);

			if (bundle.containsKey(accessKey)) {
				return bundle.getString(accessKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.debug("Name 'i18n/ui' for key '" + accessKey + "' not found");
		return adapterClass.getSimpleName();
	}

	@Override
	public String getDescription(Class<?> adapterClass, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle(
				getBundleName("classes"), locale);
		String accessKey = adapterClass.getName() + ".description";
		if (bundle.containsKey(accessKey)) {
			return bundle.getString(accessKey);
		}
		LOGGER.debug("Description 'i18n/ui' for key '" + accessKey
				+ "' not found");
		return adapterClass.getSimpleName();
	}

	public String getBundleName(String name) {
		return "i18n/" + name;
	}

	@Override
	public String getIcon16(Class<?> clazz, Locale locale) {
		return getIcon16("classes", clazz.getName(), locale);
	}

	@Override
	public String getIcon32(Class<?> clazz, Locale locale) {
		return getIcon32("classes", clazz.getName(), locale);
	}

	@Override
	public String getIcon48(Class<?> clazz, Locale locale) {
		return getIcon48("classes", clazz.getName(), locale);
	}

	@Override
	public String getIcon64(Class<?> clazz, Locale locale) {
		return getIcon64("classes", clazz.getName(), locale);
	}

	@Override
	public String getIcon128(Class<?> clazz, Locale locale) {
		return getIcon128("classes", clazz.getName(), locale);
	}

	@Override
	public String getImage(Class<?> clazz, Locale locale) {
		return getImage("classes", clazz.getName(), locale);
	}

	@Override
	public String getName(String key, Locale locale) {
		return getMessage("translations", key + ".name", locale, key);
	}

	@Override
	public String getDescription(String key, Locale locale) {
		return getMessage("translations", key + ".description", locale, key);
	}

}
