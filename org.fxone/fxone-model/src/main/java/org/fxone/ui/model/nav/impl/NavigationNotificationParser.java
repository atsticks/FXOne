package org.fxone.ui.model.nav.impl;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;
import org.fxone.ui.model.nav.NavigationEvent;
import org.fxone.ui.model.nav.cmd.Navigation;

@NotificationExtension
public final class NavigationNotificationParser extends
		AbstractNotificationConsumer {

	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& (!NavigationEvent.NOTIFTYPE_NAVIGATE_HOME.getGroup()
						.equals(result.group))) {
			return null;
		}
		try {
			if (result.name.equals(NavigationEvent.NOTIFTYPE_CLEAR_HIST
					.getName())) {
				return Navigation.createClearHistoryNotif();
			} else if (result.name
					.equals(NavigationEvent.NOTIFTYPE_NAVIGATE_BACK.getName())) {
				return Navigation.createGoBackNotif();
			} else if (result.name
					.equals(NavigationEvent.NOTIFTYPE_NAVIGATE_HOME.getName())) {
				return Navigation.createGoHomeNotif();
			} else if (result.name
					.equals(NavigationEvent.NOTIFTYPE_NAVIGATE_NEXT.getName())) {
				return Navigation.createGoNextNotif();
			} else if (result.name
					.equals(NavigationEvent.NOTIFTYPE_NAVIGATE_UP.getName())) {
				return Navigation.createGoUpNotif();
			} else if (result.name
					.equals(NavigationEvent.NOTIFTYPE_NAVIGATE_TO.getName())) {
				String target = result.params.get("target");
				if(target==null){
					throw new IllegalArgumentException("Missing navigation target.");
				}
				return Navigation.createNavigateToNotif(target);
			}
			else if (result.name
					.equals(NavigationEvent.NOTIFTYPE_NAVIGATE_TOVIEW.getName())) {
				String target = result.params.get("target");
				if(target==null){
					throw new IllegalArgumentException("Missing navigation target view.");
				}
				return Navigation.createNavigateToViewNotif(target);
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"Failed to parse/instantiate NavigationCommand from " + result,
					e);
		}
		return null;
	}

}
