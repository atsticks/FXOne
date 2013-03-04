package org.fxone.ui.model.view.impl;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;
import org.fxone.ui.model.view.ViewCommand;
import org.fxone.ui.model.view.cmd.Views;

@NotificationExtension
public final class ViewCommandParser extends AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& (!ViewCommand.NOTIFTYPE_OPEN_VIEW.getGroup().equals(
						result.group))) {
			return null;
		}
		try {
			String viewID = result.params.get("view");
			if (viewID == null) {
				throw new IllegalArgumentException("missing view.");
			}
			String returnPath = result.params.get("returnPath");
			if (result.name.equals(ViewCommand.NOTIFTYPE_OPEN_VIEW.getName())) {
				return Views.openView(viewID, returnPath).get();
			} else if (result.name.equals(ViewCommand.NOTIFTYPE_CLOSE_VIEW
					.getName())) {
				return Views.closeView(viewID, returnPath).get();
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"Failed to parse/instantiate NavigationCommand from "
							+ result, e);
		}
		return null;
	}

}
