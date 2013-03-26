package org.fxone.core.events;

import java.util.Formatter;


public final class MessageEvent extends AbstractNotification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4928494115746216477L;
	
	private String message;
	private Object[] contextData = new Object[0];

	public static final NotificationType NOTIFICATION_DEF = new NotificationType.Builder(
			DefaultGroups.COMMON.name(), "MessageEvent:show",
			"Displays/triggers a message.", Severity.INFO)
			.define(MessageEvent.class)
			.buildAndRegister();

	public MessageEvent(Object owner, String message, Object... contextData) {
		super(DefaultGroups.COMMON.name());
		setOwner(owner);
		this.message = message;
		if (contextData != null) {
			this.contextData = contextData;
		}

	}

	public MessageEvent(Object owner, Severity severity, String message,
			Object... contextData) {
		super(DefaultGroups.COMMON.name(), severity);
		setOwner(owner);
		this.message = message;
		if (contextData != null) {
			this.contextData = contextData;
		}

	}

	public String getMessage() {
		return message;
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
		return this.contextData;
	}

}
