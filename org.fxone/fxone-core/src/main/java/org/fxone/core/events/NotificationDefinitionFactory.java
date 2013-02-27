package org.fxone.core.events;

@SuppressWarnings("rawtypes")
public final class NotificationDefinitionFactory {

	public static final String VALUE = "value";

	private NotificationDefinitionFactory() {
		// Singleton
	}
	
	public static NotificationDefinition createGetter(EventGroup group, String name, String description){
		return createGetter(group, name, description, String.class);
	}
	
	public static NotificationDefinition createGetter(EventGroup group, String name, String description, Class type){
		NotificationDefinition def = new NotificationDefinition(group.toString(), name, description, Severity.DEBUG);
		def.setReadOnly();
		return def;
	}
	
	public static NotificationDefinition createSetter(EventGroup group, String name, String description){
		return createSetter(group, name, description, String.class);
	}
	
	public static NotificationDefinition createSetter(EventGroup group, String name, String description, Class type){
		NotificationDefinition def = new NotificationDefinition(group.toString(), name, description, Severity.DEBUG);
		def.defineParameter(VALUE, type, false /* may be null also! */);
		def.setReadOnly();
		return def;
	}
	
	public static NotificationDefinition createCommand(EventGroup group, String name, String description){
		return new NotificationDefinition(group.toString(), name, description, Severity.DEBUG);
	}
	
	public static NotificationDefinition createNotification(EventGroup group, String name, String description, Class type){
		NotificationDefinition def = new NotificationDefinition(group.toString(), name, description, Severity.DEBUG);
		def.defineParameter("value", type);
		def.setReadOnly();
		return def;
	}
	
}
