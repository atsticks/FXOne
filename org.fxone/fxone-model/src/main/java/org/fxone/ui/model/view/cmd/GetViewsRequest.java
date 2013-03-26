package org.fxone.ui.model.view.cmd;

import java.util.HashMap;
import java.util.Map;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.view.View;

public final class GetViewsRequest extends
		AbstractNotification<Map<String, View>> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6541926919693382107L;

	private String viewContainerID;

	public static final NotificationType NOTIFTYPE = new NotificationType.Builder(
			"UI", "View:getCurrentViews", "Get the current view(s).", Severity.UNKNOWN)
			.define(GetViewsRequest.class).addResult(Map.class)
			.buildAndRegister();

	public GetViewsRequest() {
		super(DefaultGroups.UI);
		setResult(new HashMap<String, View>());
	}

	public GetViewsRequest(String viewContainerID) {
		super(DefaultGroups.UI);
		setViewContainerID(viewContainerID);
		setResult(new HashMap<String, View>());
	}

	public String getViewContainerID() {
		return this.viewContainerID;
	}

	public void setViewContainerID(String viewContainerID) {
		checkReadOnly();
		this.viewContainerID = viewContainerID;
	}

	public void addResultView(String containerID, View view) {
		getResult().put(containerID, view);
	}
}
