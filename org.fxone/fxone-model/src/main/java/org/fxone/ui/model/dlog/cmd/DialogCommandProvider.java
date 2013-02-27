package org.fxone.ui.model.dlog.cmd;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationProvider;

@NotificationExtension
public final class DialogCommandProvider implements NotificationProvider {

	@Override
	public NotificationDefinition[] getNotificationDefinitions() {
		return new NotificationDefinition[] { DialogRequest.NOTIFTYPE_OPEN,
				DialogRequest.NOTIFTYPE_GETCURRENT,
				DialogRequest.NOTIFTYPE_CLOSE,
				DialogRequest.NOTIFTYPE_CLOSEALL,
				DialogRequest.NOTIFTYPE_ISOPEN};
	}

}
