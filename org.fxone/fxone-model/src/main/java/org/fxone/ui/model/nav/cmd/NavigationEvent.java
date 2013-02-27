package org.fxone.ui.model.nav.cmd;

import org.fxone.core.events.EventGroup;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationDefinitionFactory;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.nav.NavigationArea;

public class NavigationEvent extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 793323727444811623L;

	private static final String NAVIGATION_TARGET = "target";
	private static final String TO_NAME = "Navigate:to";
	private static final String TO_L1_NAME = "Navigate:toSubmenu1";
	private static final String TO_L22_NAME = "Navigate:toSubmenu1";
	private static final String HOME_NAME = "Navigate:home";
	private static final String BACK_NAME = "Navigate:back";
	private static final String NEXT_NAME = "Navigate:next";
	private static final String UP_NAME = "Navigate:up";
	private static final String CLEARHIST_NAME = "Navigate:clearHist";
	private static final String PRINTHIST_NAME = "Navigate:printHist";
	private static final String TOVIEW_NAME = "Navigate:toView";

	private static final String TARGET = "target";
	private static final String TARGET_NODE = "targetNode";

	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_TO = new NotificationDefinition(
			EventGroup.UI.toString(), TO_NAME,
			"Navigate within the navigation tree.", Severity.DEBUG)
			.defineParameter(NAVIGATION_TARGET, String.class, true)
			.setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_TO_L1 = new NotificationDefinition(
			EventGroup.UI.toString(), TO_L1_NAME,
			"Navigate within the navigation tree (first sublevel).",
			Severity.DEBUG).defineParameter(NAVIGATION_TARGET, String.class,
			true).setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_TO_L2 = new NotificationDefinition(
			EventGroup.UI.toString(), TO_L22_NAME,
			"Navigate within the navigation tree (second sublevel).",
			Severity.DEBUG).defineParameter(NAVIGATION_TARGET, String.class,
			true).setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_TOVIEW = new NotificationDefinition(
			EventGroup.UI.toString(), TOVIEW_NAME,
			"Navigate within the navigation tree.", Severity.DEBUG)
			.defineParameter(NAVIGATION_TARGET, String.class, true)
			.setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_HOME = NotificationDefinitionFactory
			.createCommand(EventGroup.UI, HOME_NAME,
					"Navigate within the navigation tree to home.");
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_BACK = NotificationDefinitionFactory
			.createCommand(EventGroup.UI, BACK_NAME,
					"Navigate back within the navigation history.");
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_NEXT = NotificationDefinitionFactory
			.createCommand(EventGroup.UI, NEXT_NAME,
					"Navigate forward within the navigation history.");
	public static final NotificationDefinition NOTIFTYPE_NAVIGATE_UP = NotificationDefinitionFactory
			.createCommand(EventGroup.UI, UP_NAME,
					"Navigate back within the navigation history.");
	public static final NotificationDefinition NOTIFTYPE_CLEAR_HIST = NotificationDefinitionFactory
			.createCommand(EventGroup.UI, CLEARHIST_NAME,
					"Clear the current navigation history.");
	public static final NotificationDefinition NOTIFTYPE_PRINT_HIST = NotificationDefinitionFactory
			.createCommand(EventGroup.UI, PRINTHIST_NAME,
					"Prints/shows the current navigation history.");

	NavigationEvent(NotificationDefinition def) {
		super(def);
	}

	public String getNavigationTarget() {
		return getData(NAVIGATION_TARGET, String.class);
	}

	public NavigationArea getTargetNode() {
		return getData(TARGET_NODE, NavigationArea.class);
	}

	public String getTarget() {
		return getData(TARGET, String.class);
	}

	public void setTarget(String target) {
		setData(NAVIGATION_TARGET, target);
	}

}
