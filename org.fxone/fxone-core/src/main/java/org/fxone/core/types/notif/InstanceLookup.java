package org.fxone.core.types.notif;

import java.util.Collection;
import java.util.Map;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationType;

public final class InstanceLookup extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4928494115746216477L;
	private static final String FIELD_TYPE = "type";
	private static final String FIELD_CONTEXT_DATA = "attributes";

	public static final NotificationType NOTIFICATION_DEF = new NotificationType.Builder(
			"common", "Instance:lookup",
			"Collects all instances that provide a given type/interface.")
			.defineParameter(FIELD_TYPE, Class.class, true)
			.defineParameter(FIELD_CONTEXT_DATA, Map.class, false)
			.addResult(Collection.class)
			.buildAndRegister();

	InstanceLookup(Class<?> type, Map<?, ?> params) {
		super(NOTIFICATION_DEF);
		setAttribute(FIELD_TYPE, type);
		if (params != null) {
			setAttribute(FIELD_CONTEXT_DATA, params);
		}

	}

	InstanceLookup(Class<?> type) {
		super(NOTIFICATION_DEF);
		setAttribute(FIELD_TYPE, type);

	}

	public Class<?> getInstanceType() {
		return getAttribute(FIELD_TYPE, Class.class);
	}

	public Map<?, ?> getContextData() {
		return getAttribute(FIELD_CONTEXT_DATA, Map.class);
	}

}
