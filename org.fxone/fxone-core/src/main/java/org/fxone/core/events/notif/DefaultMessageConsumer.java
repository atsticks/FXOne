package org.fxone.core.events.notif;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;
import org.fxone.core.events.Severity;

@NotificationExtension
public final class DefaultMessageConsumer extends AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& !Message.NOTIFICATION_DEF.getGroup().equals(result.group)) {
			return null;
		}
		if (result.name.equals(Message.NOTIFICATION_DEF.getName())) {
			String message = (String) result.params.get("message");
			String severity = (String) result.params.get("severity");
			if (message == null || message.isEmpty()) {
				return null;
			}
			if (severity != null) {
				Severity sev = Severity.valueOf(severity);
				return Messages.createMessage(result.owner, sev, message);
			} else {
				return Messages.createMessage(result.owner, message);
			}
		}
		return null;
	}

}
