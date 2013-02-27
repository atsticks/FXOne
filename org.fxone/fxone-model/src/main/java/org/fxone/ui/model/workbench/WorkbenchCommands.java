package org.fxone.ui.model.workbench;

import java.util.concurrent.Future;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationDefinitionFactory;
import org.fxone.core.events.NotificationService;

public class WorkbenchCommands extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1131749928750548590L;

	private WorkbenchCommands(NotificationDefinition def) {
		super(def);
	}

	public static Future<SceneEvent> setTitle(String value) {
		return NotificationService.get().publishEvent(
				createSetTitleNotif(value), SceneEvent.class);
	}

	public static Future<SceneEvent> setAreaTitle(String value) {
		return NotificationService.get().publishEvent(
				createSetAreaTitleNotif(value), SceneEvent.class);
	}

	public static Future<SceneEvent> setAreaSubTitle(String title) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_SET_AREA_SUBTITLE);
		notif.setData(NotificationDefinitionFactory.VALUE, title);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}

	public static Future<SceneEvent> setAreaDescription(
			String value) {
		return NotificationService.get().publishEvent(
				createSetAreaDescriptionNotif(value), SceneEvent.class);
	}

	public static Future<SceneEvent> getTitle(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_GET_TITLE);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}

	public static Future<SceneEvent> getAreaTitle(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_GET_AREA_TITLE);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}

	public static Future<SceneEvent> getAreaSubTitle(String value) {
		return NotificationService.get().publishEvent(
				createSetAreaSubTitleNotif(value), SceneEvent.class);
	}

	public static Future<SceneEvent> getAreaDescription(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_GET_AREA_DESCRIPTION);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}

	public String getValue() {
		return getData(NotificationDefinitionFactory.VALUE, String.class);
	}

	public static SceneEvent createSetAreaDescriptionNotif(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_SET_AREA_DESCRIPTION);
		notif.setData(NotificationDefinitionFactory.VALUE, value);
		return notif;
	}

	public static SceneEvent createSetAreaSubTitleNotif(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_GET_AREA_SUBTITLE);
		return notif;
	}

	public static SceneEvent createSetAreaTitleNotif(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_SET_AREA_TITLE);
		notif.setData(NotificationDefinitionFactory.VALUE, value);
		return notif;
	}

	public static SceneEvent createSetTitleNotif(String value) {
		SceneEvent notif = new SceneEvent(
				SceneEvent.NOTIFTYPE_SET_TITLE);
		notif.setData(NotificationDefinitionFactory.VALUE, value);
		return notif;
	}
	
	public static Future<SceneEvent> setSceneInfo(String info) {
		SceneEvent notif = new SceneEvent(SceneEvent.NOTIFTYPE_SETINFO);
		notif.setData(NotificationDefinitionFactory.VALUE, info);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}

	public static Future<SceneEvent> getSceneInfo() {
		SceneEvent notif = new SceneEvent(SceneEvent.NOTIFTYPE_GETINFO);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}

	public static Future<SceneEvent> setStatus(String value) {
		return NotificationService.get().publishEvent(
				createSetStatusCommand(value), SceneEvent.class);
	}

	public static Future<SceneEvent> getStatus() {
		SceneEvent notif = new SceneEvent(SceneEvent.NOTIFTYPE_GETSTATUS);
		return NotificationService.get().publishEvent(notif, SceneEvent.class);
	}


	public static SceneEvent createSetStatusCommand(String value) {
		SceneEvent evt = new SceneEvent(SceneEvent.NOTIFTYPE_SETSTATUS);
		evt.setValue(value);
		return evt;
	}
}
