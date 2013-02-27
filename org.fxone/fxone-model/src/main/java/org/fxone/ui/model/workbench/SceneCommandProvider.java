package org.fxone.ui.model.workbench;

import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationProvider;

@NotificationExtension
public final class SceneCommandProvider implements NotificationProvider {

	
	@Override
	public NotificationDefinition[] getNotificationDefinitions() {
		return new NotificationDefinition[] {
				SceneEvent.NOTIFTYPE_GET_AREA_DESCRIPTION,
				SceneEvent.NOTIFTYPE_GET_AREA_SUBTITLE,
				SceneEvent.NOTIFTYPE_GET_AREA_TITLE,
				SceneEvent.NOTIFTYPE_GET_TITLE,
				SceneEvent.NOTIFTYPE_SET_AREA_DESCRIPTION,
				SceneEvent.NOTIFTYPE_SET_AREA_SUBTITLE,
				SceneEvent.NOTIFTYPE_SET_AREA_TITLE,
				SceneEvent.NOTIFTYPE_SET_TITLE,
				SceneEvent.NOTIFTYPE_GETINFO,
				SceneEvent.NOTIFTYPE_SETINFO,
				SceneEvent.NOTIFTYPE_GETSTATUS,
				SceneEvent.NOTIFTYPE_SETSTATUS };
	}

}
