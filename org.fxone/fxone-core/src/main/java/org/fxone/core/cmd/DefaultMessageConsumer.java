package org.fxone.core.cmd;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.MessageEvent;
import org.fxone.core.events.Severity;

@NotificationExtension
public final class DefaultMessageConsumer extends AbstractNotificationConsumer {

	@Override
	public AbstractNotification parseNotification(ParseResult result) {
		if (MessageEvent.NOTIFICATION_DEF.isMatching(result)) {
			String message = (String) result.params.get("message");
			String severity = (String) result.params.get("severity");
			if (message == null || message.isEmpty()) {
				return null;
			}
			if (severity != null) {
				Severity sev = Severity.valueOf(severity);
				return Core.Messages.createMessage(result.owner, sev, message);
			} else {
				return Core.Messages.createMessage(result.owner, message);
			}
		}
		return null;
	}

}
