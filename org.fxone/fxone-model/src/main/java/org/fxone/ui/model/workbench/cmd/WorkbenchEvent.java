package org.fxone.ui.model.workbench.cmd;

import org.fxone.core.events.EventGroup;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationDefinitionFactory;
import org.fxone.core.events.notif.AsynchMethodCall;

public class WorkbenchEvent extends AsynchMethodCall {
	
	public static final NotificationDefinition NOTIFTYPE_SET_TITLE = NotificationDefinitionFactory
			.createSetter(EventGroup.UI, "Workbench:setTitle",
					"Sets the current scene's title.");
	public static final NotificationDefinition NOTIFTYPE_SET_AREA_TITLE = NotificationDefinitionFactory
			.createSetter(EventGroup.UI, "Workbench:setAreaTitle",
					"Sets the current display's main title.");
	public static final NotificationDefinition NOTIFTYPE_SET_AREA_SUBTITLE = NotificationDefinitionFactory
			.createSetter(EventGroup.UI, "Workbench:setSubTitle",
					"Sets the current display's sub title.");
	public static final NotificationDefinition NOTIFTYPE_SET_AREA_DESCRIPTION = NotificationDefinitionFactory
			.createSetter(EventGroup.UI, "Workbench:setDescription",
					"Sets the current display's main description.");

	public static final NotificationDefinition NOTIFTYPE_GET_TITLE = NotificationDefinitionFactory
			.createGetter(EventGroup.UI, "Workbench:getTitle",
					"Sets the current scene's title.");
	public static final NotificationDefinition NOTIFTYPE_GET_AREA_TITLE = NotificationDefinitionFactory
			.createGetter(EventGroup.UI, "Workbench:getAreaTitle",
					"Sets the current display's main title.");
	public static final NotificationDefinition NOTIFTYPE_GET_AREA_SUBTITLE = NotificationDefinitionFactory
			.createGetter(EventGroup.UI, "Workbench:getSubTitle",
					"Sets the current display's sub title.");
	public static final NotificationDefinition NOTIFTYPE_GET_AREA_DESCRIPTION = NotificationDefinitionFactory
			.createGetter(EventGroup.UI, "Workbench:getDescription",
					"Sets the current display's main description.");
	
	public static final NotificationDefinition NOTIFTYPE_SETSTATUS = NotificationDefinitionFactory
			.createSetter(EventGroup.UI, "Workbench:setStatus",
					"Sets the current status info.");
	public static final NotificationDefinition NOTIFTYPE_GETSTATUS = NotificationDefinitionFactory
			.createGetter(EventGroup.UI, "Workbench:getStatus",
					"Gets the current status info.");
	
	public static final NotificationDefinition NOTIFTYPE_SETINFO = NotificationDefinitionFactory
			.createSetter(EventGroup.UI, "Workbench:setInfo",
					"Sets the current status info.");
	public static final NotificationDefinition NOTIFTYPE_GETINFO = NotificationDefinitionFactory
			.createGetter(EventGroup.UI, "Workbench:getInfo",
					"Gets the current status info.");
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6956469546415671688L;

	WorkbenchEvent(NotificationDefinition def) {
		super(def);
	}

	public String getValue() {
		return getData(NotificationDefinitionFactory.VALUE, String.class);
	}
	
	public void setValue(String value){
		if(value==null){
			throw new IllegalArgumentException("Value is required.");
		}
		setData(NotificationDefinitionFactory.VALUE, value);
	}
}
