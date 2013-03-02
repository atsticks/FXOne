package org.fxone.ui.model.workbench.cmd;

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

	public static Future<WorkbenchEvent> setTitle(String value) {
		return NotificationService.get().publishEvent(
				createSetTitleNotif(value), WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> setAreaTitle(String value) {
		return NotificationService.get().publishEvent(
				createSetAreaTitleNotif(value), WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> setAreaSubTitle(String title) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_SET_AREA_SUBTITLE);
		notif.setData(NotificationDefinitionFactory.VALUE, title);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> setAreaDescription(
			String value) {
		return NotificationService.get().publishEvent(
				createSetAreaDescriptionNotif(value), WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> getTitle(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_GET_TITLE);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> getAreaTitle(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_GET_AREA_TITLE);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> getAreaSubTitle(String value) {
		return NotificationService.get().publishEvent(
				createSetAreaSubTitleNotif(value), WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> getAreaDescription(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_GET_AREA_DESCRIPTION);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}

	public String getValue() {
		return getData(NotificationDefinitionFactory.VALUE, String.class);
	}

	public static WorkbenchEvent createSetAreaDescriptionNotif(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_SET_AREA_DESCRIPTION);
		notif.setData(NotificationDefinitionFactory.VALUE, value);
		return notif;
	}

	public static WorkbenchEvent createSetAreaSubTitleNotif(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_GET_AREA_SUBTITLE);
		return notif;
	}

	public static WorkbenchEvent createSetAreaTitleNotif(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_SET_AREA_TITLE);
		notif.setData(NotificationDefinitionFactory.VALUE, value);
		return notif;
	}

	public static WorkbenchEvent createSetTitleNotif(String value) {
		WorkbenchEvent notif = new WorkbenchEvent(
				WorkbenchEvent.NOTIFTYPE_SET_TITLE);
		notif.setData(NotificationDefinitionFactory.VALUE, value);
		return notif;
	}
	
	public static Future<WorkbenchEvent> setSceneInfo(String info) {
		WorkbenchEvent notif = new WorkbenchEvent(WorkbenchEvent.NOTIFTYPE_SETINFO);
		notif.setData(NotificationDefinitionFactory.VALUE, info);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> getSceneInfo() {
		WorkbenchEvent notif = new WorkbenchEvent(WorkbenchEvent.NOTIFTYPE_GETINFO);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> setStatus(String value) {
		return NotificationService.get().publishEvent(
				createSetStatusCommand(value), WorkbenchEvent.class);
	}

	public static Future<WorkbenchEvent> getStatus() {
		WorkbenchEvent notif = new WorkbenchEvent(WorkbenchEvent.NOTIFTYPE_GETSTATUS);
		return NotificationService.get().publishEvent(notif, WorkbenchEvent.class);
	}


	public static WorkbenchEvent createSetStatusCommand(String value) {
		WorkbenchEvent evt = new WorkbenchEvent(WorkbenchEvent.NOTIFTYPE_SETSTATUS);
		evt.setValue(value);
		return evt;
	}
}
