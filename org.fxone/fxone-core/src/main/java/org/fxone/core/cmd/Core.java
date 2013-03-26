package org.fxone.core.cmd;

import java.util.concurrent.Future;

import org.fxone.core.events.MessageEvent;
import org.fxone.core.events.NotificationService;
import org.fxone.core.events.Severity;

public final class Core {

	private Core() {
	}

	public static final class Messages {

		private Messages() {
		}

		public static Future<MessageEvent> send(Object owner, Severity severity,
				String message, Object... contextData) {
			MessageEvent evt = new MessageEvent(owner, severity, message, contextData);
			evt.setReadOnly();
			return NotificationService.get().publishEvent(
					createMessage(owner, severity, message, contextData),
					MessageEvent.class);
		}

		public static Future<MessageEvent> warning(Object owner, String message,
				Object... contextData) {
			return NotificationService.get()
					.publishEvent(
							createMessage(owner, Severity.WARNING, message,
									contextData), MessageEvent.class);
		}

		public static Future<MessageEvent> alarm(Object owner, String message,
				Object... contextData) {
			return NotificationService.get().publishEvent(
					createMessage(owner, Severity.ALARM, message, contextData),
					MessageEvent.class);
		}

		public static Future<MessageEvent> cleared(Object owner, String message,
				Object... contextData) {
			return NotificationService.get()
					.publishEvent(
							createMessage(owner, Severity.CLEARED, message,
									contextData), MessageEvent.class);
		}

		public static Future<MessageEvent> debug(Object owner, String message,
				Object... contextData) {
			return NotificationService.get().publishEvent(
					createMessage(owner, Severity.DEBUG, message, contextData),
					MessageEvent.class);
		}

		public static Future<MessageEvent> error(Object owner, String message,
				Object... contextData) {
			return NotificationService.get().publishEvent(
					createMessage(owner, Severity.ERROR, message, contextData),
					MessageEvent.class);
		}

		public static Future<MessageEvent> fatal(Object owner, String message,
				Object... contextData) {
			return NotificationService.get().publishEvent(
					createMessage(owner, Severity.FATAL, message, contextData),
					MessageEvent.class);
		}

		public static Future<MessageEvent> info(Object owner, String message,
				Object... contextData) {
			return NotificationService.get().publishEvent(
					createMessage(owner, Severity.INFO, message, contextData),
					MessageEvent.class);
		}

		public static MessageEvent createMessage(Object owner, Severity severity,
				String message, Object... contextData) {
			MessageEvent evt = new MessageEvent(owner, severity, message, contextData);
			evt.setReadOnly();
			return evt;
		}

		public static Future<MessageEvent> send(Object owner, String message,
				Object... contextData) {
			return NotificationService.get().publishEvent(
					createMessage(owner, message, contextData), MessageEvent.class);
		}

		public static MessageEvent createMessage(Object owner, String message,
				Object... contextData) {
			MessageEvent evt = new MessageEvent(owner, Severity.INFO, message,
					contextData);
			evt.setReadOnly();
			return evt;
		}

	}

}
