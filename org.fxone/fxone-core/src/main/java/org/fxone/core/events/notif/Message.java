package org.fxone.core.events.notif;

import java.util.Formatter;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;

public final class Message extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4928494115746216477L;
	private static final String FIELD_MESSAGE = "message";
	private static final String FIELD_CONTEXT_DATA = "contextData";

	public static final NotificationType NOTIFICATION_DEF = new NotificationType.Builder(
			"common", "Message:show",
			"Displays/triggers an arbitrary message.", Severity.INFO)
			.defineParameter(FIELD_MESSAGE, String.class, true)
			.defineParameter(FIELD_CONTEXT_DATA, Object[].class, false)
			.buildAndRegister();

	Message(Object owner, String message, Object... contextData) {
		super(NOTIFICATION_DEF);
		setOwner(owner);
		setAttribute(FIELD_MESSAGE, message);
		if (contextData != null) {
			setAttribute(FIELD_CONTEXT_DATA, contextData);
		}

	}

	Message(Object owner, Severity severity, String message,
			Object... contextData) {
		super(NOTIFICATION_DEF, severity);
		setOwner(owner);
		setAttribute(FIELD_MESSAGE, message);
		if (contextData != null) {
			setAttribute(FIELD_CONTEXT_DATA, contextData);
		}

	}

	public String getMessage() {
		return getAttribute(FIELD_MESSAGE, String.class);
	}

	public String getFormattedMessage() {
		Object[] contextData = getContextData();
		if (contextData != null) {

			Formatter f = new Formatter();
			try {
				return f.format(getMessage(), contextData).toString();
			} finally {
				f.close();
			}
		}
		return getMessage();
	}

	public Object[] getContextData() {
		return getAttribute(FIELD_CONTEXT_DATA, Object[].class);
	}

}
