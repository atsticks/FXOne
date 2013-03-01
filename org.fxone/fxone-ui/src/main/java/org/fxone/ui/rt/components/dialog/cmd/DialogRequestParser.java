package org.fxone.ui.rt.components.dialog.cmd;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer;
import org.fxone.core.events.Notification;
import org.fxone.ui.rt.components.dialog.Dialog;

@NotificationExtension
public final class DialogRequestParser extends AbstractNotificationConsumer {

	@SuppressWarnings("unchecked")
	@Override
	public Notification parseNotification(ParseResult result) {
		if (result.group != null
				&& (!DialogRequest.NOTIFTYPE_OPEN.getGroup().equals(
						result.group))) {
			return null;
		}
		try {
//			if (result.name.equals(DialogRequest.NOTIFTYPE_OPEN.getName())) {
//				Dialog dialog = (Dialog) result.params.get("dialog");
//				return Dialogs.createOpenDialogNotif(dialog);
//			} else 
				if (result.name.equals(DialogRequest.NOTIFTYPE_CLOSEALL
					.getName())) {
				String forceValue = result.params.get("force");
				if (forceValue != null) {
					return Dialogs.createCloseAllDialogsNotif(Boolean
							.parseBoolean(forceValue));
				}
				return Dialogs.createCloseAllDialogsNotif();
//			} else {
//				if (result.name.equals(DialogRequest.NOTIFTYPE_CLOSE.getName())) {
//					String forceValue = result.params.get("force");
//					if (forceValue != null) {
//						return Dialogs.createCloseDialogNotif(Boolean
//								.valueOf(forceValue));
//					}
//					return Dialogs.createCloseDialogNotif();
//				}
			}
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(
					"Failed to parse/instantiate DialogCommand from " + result,
					e);
		}
		return null;
	}

}
