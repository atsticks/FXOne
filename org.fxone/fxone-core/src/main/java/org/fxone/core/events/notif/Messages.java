package org.fxone.core.events.notif;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.core.events.Severity;

public final class Messages{

	private Messages() {
	}
	
	public static Future<Message> send(Object owner, Severity severity,
			String message, Object... contextData) {
		Message evt = new Message(owner, severity, message, contextData);
		evt.setReadOnly();
		return NotificationService.get().publishEvent(
				createMessage(owner, severity, message, contextData),
				Message.class);
	}

	public static Future<Message> warning(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.WARNING, message, contextData),
				Message.class);
	}

	public static Future<Message> alarm(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.ALARM, message, contextData),
				Message.class);
	}

	public static Future<Message> cleared(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.CLEARED, message, contextData),
				Message.class);
	}

	public static Future<Message> debug(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.DEBUG, message, contextData),
				Message.class);
	}

	public static Future<Message> error(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.ERROR, message, contextData),
				Message.class);
	}

	public static Future<Message> fatal(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.FATAL, message, contextData),
				Message.class);
	}

	public static Future<Message> info(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, Severity.INFO, message, contextData),
				Message.class);
	}

	public static Message createMessage(Object owner, Severity severity,
			String message, Object... contextData) {
		Message evt = new Message(owner, severity, message, contextData);
		evt.setReadOnly();
		return evt;
	}

	public static Future<Message> send(Object owner, String message,
			Object... contextData) {
		return NotificationService.get().publishEvent(
				createMessage(owner, message, contextData), Message.class);
	}

	public static Message createMessage(Object owner, String message,
			Object... contextData) {
		Message evt = new Message(owner, Severity.INFO, message, contextData);
		evt.setReadOnly();
		return evt;
	}

}
