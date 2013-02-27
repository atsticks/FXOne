package org.fxone.ui.model.workbench;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;

@NotificationExtension
public final class StatusInfoNotifConsumer extends AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& (!SceneEvent.NOTIFTYPE_SETSTATUS.getGroup().equals(
						result.group))) {
			return null;
		}
		try {
			String value = result.params.get("value");
			if (result.name.equals(SceneEvent.NOTIFTYPE_SETSTATUS.getName())) {
				return WorkbenchCommands.setStatus(value).get();
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"Failed to parse/instantiate NavigationCommand from "
							+ result, e);
		}
		return null;
	}

}
