package org.fxone.core.events;
//
//@SuppressWarnings("rawtypes")
//public final class NotificationDefinitionFactory {
//
//	public static final String SIMPLE_VALUE = "value";
//
//	private NotificationDefinitionFactory() {
//		// Singleton
//	}
//	
//	public static NotificationType createGetter(String type, String description){
//		return createGetter(type, description, String.class);
//	}
//	
//	public static NotificationType createGetter(String type, String description, Class type){
//		NotificationType def = new NotificationType(group.toString(), name, description, Severity.DEBUG);
//		def.setReadOnly();
//		return def;
//	}
//	
//	public static NotificationType createSetter(String type, String description){
//		return createSetter(type, description, String.class);
//	}
//	
//	public static NotificationType createSetter(String type, String description, Class type){
//		NotificationType def = new NotificationType(group.toString(), name, description, Severity.DEBUG);
//		def.defineParameter(SIMPLE_VALUE, type, false /* may be null also! */);
//		def.setReadOnly();
//		return def;
//	}
//	
//	public static NotificationType createCommand(String type, String description){
//		return new NotificationType(group.toString(), name, description, Severity.DEBUG);
//	}
//	
//	public static NotificationType createNotification(String type, String description, Class type){
//		NotificationType def = new NotificationType(group.toString(), name, description, Severity.DEBUG);
//		def.defineParameter("value", type);
//		def.setReadOnly();
//		return def;
//	}
//	
//}
//