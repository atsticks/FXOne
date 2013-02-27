package org.fxone.core.types.notif;

import java.util.Map;

import org.fxone.core.events.EventGroup;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.Severity;
import org.fxone.core.events.notif.AsynchMethodCall;

public final class InstanceLookup extends AsynchMethodCall {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4928494115746216477L;
	private static final String FIELD_TYPE = "type";
	private static final String FIELD_CONTEXT_DATA = "attributes";

	public static final NotificationDefinition NOTIFICATION_DEF = new NotificationDefinition(
			EventGroup.COMMON.toString(), "Instance:lookup",
			"Collects all instances that provide a given type/interface.", Severity.INFO)
			.defineParameter(FIELD_TYPE, Class.class, true)
			.defineParameter(FIELD_CONTEXT_DATA, Map.class, false)
			.setReadOnly();

	InstanceLookup(Class<?> type, Map<?,?> params) {
		super(NOTIFICATION_DEF);
		setData(FIELD_TYPE, type);
		if (params != null) {
			setData(FIELD_CONTEXT_DATA, params);
		}

	}

	InstanceLookup(Class<?> type) {
		super(NOTIFICATION_DEF);
		setData(FIELD_TYPE, type);

	}

	public Class<?> getType() {
		return getData(FIELD_TYPE, Class.class);
	}


	public Map<?,?> getContextData() {
		return getData(FIELD_CONTEXT_DATA, Map.class);
	}

}
