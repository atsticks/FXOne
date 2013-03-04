package org.fxone.core.types.notif;

import java.util.Map;
import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;

public final class TypeFunctions{

	private TypeFunctions() {
	}
	
	public static Future<InstanceLookup> instanceLookup(Class<?> type, Map<?,?> attributes) {
		InstanceLookup evt = new InstanceLookup(type, attributes);
		evt.setReadOnly();
		return NotificationService.get().publishEvent(evt,
				InstanceLookup.class);
	}

	public static Future<InstanceLookup> instanceLookup(Class<?> type) {
		InstanceLookup evt = new InstanceLookup(type);
		evt.setReadOnly();
		return NotificationService.get().publishEvent(evt,
				InstanceLookup.class);
	}

}
