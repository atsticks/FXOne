package org.fxone.ui.model.workbench.cmd;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationProvider;

@NotificationExtension
public final class WorkbenchCommandProvider implements NotificationProvider {

	
	@Override
	public NotificationDefinition[] getNotificationDefinitions() {
		return new NotificationDefinition[] {
				WorkbenchEvent.NOTIFTYPE_GET_AREA_DESCRIPTION,
				WorkbenchEvent.NOTIFTYPE_GET_AREA_SUBTITLE,
				WorkbenchEvent.NOTIFTYPE_GET_AREA_TITLE,
				WorkbenchEvent.NOTIFTYPE_GET_TITLE,
				WorkbenchEvent.NOTIFTYPE_SET_AREA_DESCRIPTION,
				WorkbenchEvent.NOTIFTYPE_SET_AREA_SUBTITLE,
				WorkbenchEvent.NOTIFTYPE_SET_AREA_TITLE,
				WorkbenchEvent.NOTIFTYPE_SET_TITLE,
				WorkbenchEvent.NOTIFTYPE_GETINFO,
				WorkbenchEvent.NOTIFTYPE_SETINFO,
				WorkbenchEvent.NOTIFTYPE_GETSTATUS,
				WorkbenchEvent.NOTIFTYPE_SETSTATUS };
	}

}
