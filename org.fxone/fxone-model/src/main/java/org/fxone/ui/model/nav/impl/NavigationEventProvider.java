package org.fxone.ui.model.nav.impl;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationProvider;
import org.fxone.ui.model.nav.cmd.NavigationEvent;

@NotificationExtension
public final class NavigationEventProvider implements NotificationProvider {

	@Override
	public NotificationDefinition[] getNotificationDefinitions() {
		return new NotificationDefinition[] { NavigationEvent.NOTIFTYPE_NAVIGATE_BACK,
				NavigationEvent.NOTIFTYPE_CLEAR_HIST,
				NavigationEvent.NOTIFTYPE_NAVIGATE_HOME,
				NavigationEvent.NOTIFTYPE_NAVIGATE_NEXT,
				NavigationEvent.NOTIFTYPE_PRINT_HIST,
				NavigationEvent.NOTIFTYPE_NAVIGATE_TO,
				NavigationEvent.NOTIFTYPE_NAVIGATE_TOVIEW,
				NavigationEvent.NOTIFTYPE_NAVIGATE_UP };
	}

}
