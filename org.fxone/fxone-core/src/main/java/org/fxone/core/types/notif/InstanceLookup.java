package org.fxone.core.types.notif;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationService;
import org.fxone.core.events.NotificationType;

@SuppressWarnings("rawtypes")
public final class InstanceLookup extends AbstractNotification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4928494115746216477L;
	
	private Class<?> type;
	
	private final Map attributes = new HashMap();

	public static final NotificationType NOTIFICATION_DEF = new NotificationType.Builder(
			DefaultGroups.COMMON.name(), "Instance:lookup",
			"Collects all instances that provide a given type/interface.")
			.define(InstanceLookup.class)
			.addResult(Collection.class)
			.buildAndRegister();

	
	@SuppressWarnings("unchecked")
	InstanceLookup(Class<?> type, Map params) {
		super(DefaultGroups.COMMON.name());
		if(type==null){
			throw new IllegalArgumentException("Type is required.");
		}
		this.type = type;
		if (params != null) {
			this.attributes.putAll(params);
		}

	}

	InstanceLookup(Class<?> type) {
		super(DefaultGroups.COMMON.name());
		if(type==null){
			throw new IllegalArgumentException("Type is required.");
		}
		this.type = type;

	}

	public Class<?> getInstanceType() {
		return this.type;
	}

	public Map<?, ?> getContextData() {
		return this.attributes;
	}
	
	public static Future<InstanceLookup> lookup(Class<?> type, Map<?,?> attributes) {
		InstanceLookup evt = new InstanceLookup(type, attributes);
		evt.setReadOnly();
		return NotificationService.get().publishEvent(evt,
				InstanceLookup.class);
	}

	public static Future<InstanceLookup> lookup(Class<?> type) {
		InstanceLookup evt = new InstanceLookup(type);
		evt.setReadOnly();
		return NotificationService.get().publishEvent(evt,
				InstanceLookup.class);
	}

}
