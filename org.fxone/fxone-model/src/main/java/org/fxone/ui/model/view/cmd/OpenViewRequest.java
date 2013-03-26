package org.fxone.ui.model.view.cmd;

import java.util.Map;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.view.View;

public final class OpenViewRequest extends AbstractNotification<Boolean> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6541926919693382107L;

	private View view;
	private String viewContainerID;
	private Map<String, Object> params;

	public static final NotificationType NOTIFTYPE = new NotificationType.Builder(
			"UI", "View:open", "Open a view instance.", Severity.DEBUG)
			.define(OpenViewRequest.class).addResult(Boolean.class)
			.buildAndRegister();

	public OpenViewRequest() {
		super(DefaultGroups.UI);
	}
	
	public OpenViewRequest(String viewID) {
		super(DefaultGroups.UI);
	}

	public View getView() {
		return this.view;
	}

	public Map<String, Object> getParameters() {
		return params;
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
