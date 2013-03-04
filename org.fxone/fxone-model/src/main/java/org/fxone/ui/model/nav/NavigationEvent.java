package org.fxone.ui.model.nav;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;

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

//	private static final String TARGET = "target";
	private static final String TARGET_NODE = "targetNode";

	public static final NotificationType NOTIFTYPE_NAVIGATE_TO = new NotificationType.Builder(
			"UI", TO_NAME, "Navigate within the navigation tree.",
			Severity.DEBUG).defineParameter(NAVIGATION_TARGET, String.class,
			true).buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_TO_L1 = new NotificationType.Builder(
			"UI", TO_L1_NAME,
			"Navigate within the navigation tree (first sublevel).",
			Severity.DEBUG).defineParameter(NAVIGATION_TARGET, String.class,
			true).buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_TO_L2 = new NotificationType.Builder(
			"UI", TO_L22_NAME,
			"Navigate within the navigation tree (second sublevel).",
			Severity.DEBUG).defineParameter(NAVIGATION_TARGET, String.class,
			true).buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_TOVIEW = new NotificationType.Builder(
			"UI", TOVIEW_NAME, "Navigate within the navigation tree.",
			Severity.DEBUG).defineParameter(NAVIGATION_TARGET, String.class,
			true).buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_HOME = new NotificationType.Builder(
			"UI", HOME_NAME, "Navigate within the navigation tree to home.")
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_BACK = new NotificationType.Builder(
			"UI", BACK_NAME, "Navigate back within the navigation history.")
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_NEXT = new NotificationType.Builder(
			"UI", NEXT_NAME, "Navigate forward within the navigation history.")
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_NAVIGATE_UP = new NotificationType.Builder(
			"UI", UP_NAME, "Navigate back within the navigation history.")
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_CLEAR_HIST = new NotificationType.Builder(
			"UI", CLEARHIST_NAME, "Clear the current navigation history.")
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_PRINT_HIST = new NotificationType.Builder(
			"UI", PRINTHIST_NAME,
			"Prints/shows the current navigation history.").buildAndRegister();

	public NavigationEvent(NotificationType def) {
		super(def);
	}

	public String getNavigationTarget() {
		return getAttribute(NAVIGATION_TARGET, String.class);
	}

	public NavigationArea getTargetNode() {
		return getAttribute(TARGET_NODE, NavigationArea.class);
	}

	public void setNavigationTarget(String target) {
		setAttribute(NAVIGATION_TARGET, target);
	}

	public void setTargetNode(NavigationArea node) {
		setAttribute(TARGET_NODE, node);
	}
}
