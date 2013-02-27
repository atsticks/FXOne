package org.fxone.ui.model.workbench;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;

@NotificationExtension
public final class SceneCommandParser extends
		AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& (!SceneEvent.NOTIFTYPE_SET_AREA_DESCRIPTION.getGroup()
						.equals(result.group))) {
			return null;
		}
		try {
			String value = result.params.get("value");
			if (result.name.equals(SceneEvent.NOTIFTYPE_SET_AREA_DESCRIPTION
					.getName())) {
				return WorkbenchCommands.createSetAreaDescriptionNotif(value);
			} else if (result.name
					.equals(SceneEvent.NOTIFTYPE_SET_AREA_SUBTITLE.getName())) {
				return WorkbenchCommands.createSetAreaSubTitleNotif(value);
			} else if (result.name
					.equals(SceneEvent.NOTIFTYPE_SET_AREA_TITLE.getName())) {
				return WorkbenchCommands.createSetAreaTitleNotif(value);
			} else if (result.name
					.equals(SceneEvent.NOTIFTYPE_SET_TITLE.getName())) {
				return WorkbenchCommands.createSetTitleNotif(value);
			} 
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"Failed to parse/instantiate NavigationCommand from " + result,
					e);
		}
		return null;
	}

}
