package org.fxone.ui.model.view.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.view.View;

public final class CloseViewRequest extends AbstractNotification<Boolean> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6541926919693382107L;

	private View view;
	private String viewContainerID;

	public static final NotificationType NOTIFTYPE = new NotificationType.Builder(
			"UI", "View:close", "Close a view instance.", Severity.DEBUG)
			.define(CloseViewRequest.class).addResult(Boolean.class)
			.buildAndRegister();

	public CloseViewRequest() {
		super(DefaultGroups.UI);
	}
	
	public CloseViewRequest(String viewID) {
		super(DefaultGroups.UI);
	}

	public View getView() {
		return this.view;
	}

	public void setView(View view) {
		checkReadOnly();
		if (view == null) {
			throw new IllegalArgumentException("view is required.");
		}
		this.view = view;
	}

	public String getViewContainerID() {
		return this.viewContainerID;
	}

	public void setViewContainerID(String viewContainerID) {
		checkReadOnly();
		this.viewContainerID = viewContainerID;
	}

}
