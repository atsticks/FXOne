package org.fxone.ui.model.res.cmd;

import java.util.Locale;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;

public final class ResourceRequest extends AbstractNotification<String> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4928494115746216477L;

	private String family;
	private String key;
	private Locale locale;
	private Object[] contextData = new Object[0];

	public static NotificationType NOTIF_TYPE = new NotificationType.Builder(
			"UI", "Resource:getResource",
			"Access a resourceID using the application's UI bundle.",
			Severity.DEBUG).define(ResourceRequest.class)
			.addResult(String.class).buildAndRegister();

	public ResourceRequest(String key, Locale locale, Object... contextData) {
		super(DefaultGroups.COMMON.name());
		this.key = key;
		this.locale = locale;
		if (contextData != null) {
			this.contextData = contextData;
		}
	}

	public ResourceRequest(String family, String key, Locale locale,
			Object... contextData) {
		super(DefaultGroups.COMMON.name());
		this.family = family;
		this.key = key;
		this.locale = locale;
		if (contextData != null) {
			this.contextData = contextData;
		}
	}

	public String getFamily() {
		return family;
	}

	public String getKey() {
		return key;
	}

	public Object[] getContextData() {
		return this.contextData;
	}

	public Locale getLocale() {
		return locale;
	}

}
