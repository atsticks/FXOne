package org.fxone.ui.model.workbench;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationType;

public class WorkbenchEvent extends Notification {

	public static final NotificationType NOTIFTYPE_SET_TITLE = new NotificationType.Builder(
			"UI", "Workbench:setTitle", "Sets the current scene's title.")
			.defineParameter(SIMPLE_VALUE, String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_SET_AREA_TITLE = new NotificationType.Builder(
			"UI", "Workbench:setAreaTitle",
					"Sets the current display's main title.").defineParameter(SIMPLE_VALUE, String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_SET_AREA_SUBTITLE = new NotificationType.Builder(
			"UI",  "Workbench:setSubTitle",
					"Sets the current display's sub title.").defineParameter(SIMPLE_VALUE, String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_SET_AREA_DESCRIPTION = new NotificationType.Builder(
			"UI",  "Workbench:setDescription",
					"Sets the current display's main description.").defineParameter(SIMPLE_VALUE, String.class).buildAndRegister();

	public static final NotificationType NOTIFTYPE_GET_TITLE = new NotificationType.Builder(
			"UI",  "Workbench:getTitle",
					"Sets the current scene's title.").addResult(String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_GET_AREA_TITLE = new NotificationType.Builder(
			"UI",  "Workbench:getAreaTitle",
					"Sets the current display's main title.").addResult(String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_GET_AREA_SUBTITLE = new NotificationType.Builder(
			"UI",  "Workbench:getSubTitle",
					"Sets the current display's sub title.").addResult(String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_GET_AREA_DESCRIPTION = new NotificationType.Builder(
			"UI",  "Workbench:getDescription",
					"Sets the current display's main description.").addResult(String.class).buildAndRegister();

	public static final NotificationType NOTIFTYPE_SETSTATUS = new NotificationType.Builder(
			"UI",  "Workbench:setStatus",
					"Sets the current status info.").defineParameter(SIMPLE_VALUE, String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_GETSTATUS = new NotificationType.Builder(
			"UI",  "Workbench:getStatus",
					"Gets the current status info.").addResult(String.class).buildAndRegister();

	public static final NotificationType NOTIFTYPE_SETINFO = new NotificationType.Builder(
			"UI",  "Workbench:setInfo",
					"Sets the current status info.").defineParameter(SIMPLE_VALUE, String.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_GETINFO = new NotificationType.Builder(
			"UI",  "Workbench:getInfo",
					"Gets the current status info.").addResult(String.class).buildAndRegister();

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6956469546415671688L;

	public WorkbenchEvent(NotificationType def) {
		super(def);
	}

	public String getValue() {
		return getAttribute(SIMPLE_VALUE, String.class);
	}

	public void setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException("Value is required.");
		}
		setAttribute(SIMPLE_VALUE, value);
	}
}
