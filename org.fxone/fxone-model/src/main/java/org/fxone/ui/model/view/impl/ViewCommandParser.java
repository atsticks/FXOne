package org.fxone.ui.model.view.impl;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.cmd.ViewCommand;
import org.fxone.ui.model.view.cmd.ViewContext;
import org.fxone.ui.model.view.cmd.Views;

@NotificationExtension
public final class ViewCommandParser extends AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& (!ViewCommand.NOTIFTYPE_VIEW_OPEN.getGroup().equals(
						result.group))) {
			return null;
		}
		try {
			String viewID = result.params.get("view");
			if (viewID == null) {
				throw new IllegalArgumentException("missing view.");
			}
			String viewContainerID = result.params.get("viewContainerID");
			ViewContext ctx = new ViewContext(viewID, viewContainerID,
					result.params);
			View view = Views.createView(ctx);
			if (view != null) {
				if (result.name.equals(ViewCommand.NOTIFTYPE_VIEW_OPEN
						.getName())) {
					return Views.openView(view, viewContainerID).get();
				} else if (result.name.equals(ViewCommand.NOTIFTYPE_VIEW_CLOSE
						.getName())) {
					return Views.closeView(view, viewContainerID).get();
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
