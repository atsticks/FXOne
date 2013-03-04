package org.fxone.ui.model.workbench.impl;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;
import org.fxone.ui.model.workbench.WorkbenchEvent;
import org.fxone.ui.model.workbench.cmd.WorkbenchCommands;

@NotificationExtension
public final class WorkbenchCommandParser extends AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		try {
			if (WorkbenchEvent.NOTIFTYPE_SET_AREA_DESCRIPTION
					.isMatching(result)) {
				return WorkbenchCommands
						.createSetAreaDescriptionNotif(result.params
								.get("value"));
			} else if (WorkbenchEvent.NOTIFTYPE_SET_AREA_SUBTITLE
					.isMatching(result)) {
				return WorkbenchCommands
						.createSetAreaSubTitleNotif(result.params.get("value"));
			} else if (WorkbenchEvent.NOTIFTYPE_SET_AREA_TITLE
					.isMatching(result)) {
				return WorkbenchCommands.createSetAreaTitleNotif(result.params
						.get("value"));
			} else if (WorkbenchEvent.NOTIFTYPE_SET_TITLE.isMatching(result)) {
				return WorkbenchCommands.createSetTitleNotif(result.params
						.get("value"));
			} else if (WorkbenchEvent.NOTIFTYPE_SETSTATUS.isMatching(result)) {
				try {
					String value = result.params.get("value");
					if (result.name.equals(WorkbenchEvent.NOTIFTYPE_SETSTATUS
							.getName())) {
						return WorkbenchCommands.setStatus(value).get();
					}
				} catch (Exception e) {
					Logger.getLogger(getClass()).error(
							"Failed to parse/instantiate NavigationCommand from "
									+ result, e);
				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"Failed to parse/instantiate NavigationCommand from "
							+ result, e);
		}
		return null;
	}

}
