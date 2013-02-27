package org.fxone.ui.model.view.impl;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationProvider;
import org.fxone.ui.model.view.cmd.ViewCommand;

@NotificationExtension
public final class ViewEventProvider implements NotificationProvider {

	@Override
	public NotificationDefinition[] getNotificationDefinitions() {
		return new NotificationDefinition[] { ViewCommand.NOTIFTYPE_OPEN_VIEW,
				ViewCommand.NOTIFTYPE_OPEN_VIEW, ViewCommand.NOTIFTYPE_VIEW_OPENED,
				ViewCommand.NOTIFTYPE_VIEW_CLOSED };
	}

}
