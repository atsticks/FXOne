package org.fxone.ui.model.res;

import java.util.Locale;

public interface ResourceProvider {

	public String getMessage(String bundle, String key, Locale locale,
			Object... contextData);

	public String getMessage(String bundle, Locale locale, Object... contextData);

	public String getIconScalable(String family, String key, Locale locale);

	public String getIcon16(String family, String key, Locale locale);

	public String getIcon32(String family, String key, Locale locale);

	public String getIcon48(String family, String key, Locale locale);

	public String getIcon64(String family, String key, Locale locale);

	public String getIcon128(String family, String key, Locale locale);

	public String getImage(String family, String key, Locale locale);

	public String getName(Class<?> clazz, Locale locale);

	public String getDescription(Class<?> clazz, Locale locale);
	
	public String getIcon16(Class<?> clazz, Locale locale);

	public String getIcon32(Class<?> clazz, Locale locale);

	public String getIcon48(Class<?> clazz, Locale locale);

	public String getIcon64(Class<?> clazz, Locale locale);

	public String getIcon128(Class<?> clazz, Locale locale);

	public String getImage(Class<?> clazz, Locale locale);

	public String getName(String key, Locale locale);
	
	public String getDescription(String key, Locale locale);

}
