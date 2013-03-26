package org.fxone.ui.model.nav.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.nav.NavigateableArea;

public class NavigateTo extends AbstractNotification<Void> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 793323727444811623L;

	private String target;
	private NavigateableArea targetNode;

	public static final NotificationType NOTIFTYPE_NAVIGATE_TO = new NotificationType.Builder(
			"UI", "Navigate:to", "Navigate within the navigation tree.",
			Severity.DEBUG).define(NavigateTo.class).buildAndRegister();

	public NavigateTo() {
		super(DefaultGroups.NAVIGATION);
	}

	public String getNavigationTarget() {
		return target;
	}

	public NavigateableArea getTargetNode() {
		return targetNode;
	}

	public void setNavigationTarget(String target) {
		checkReadOnly();
		this.target = target;
	}

	public void setTargetNode(NavigateableArea targetNode) {
		checkReadOnly();
		this.targetNode = targetNode;
	}
}
