package org.fxone.ui.model.view.cmd;

import java.util.Map;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;

public final class ViewCommand extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6541926919693382107L;

	private static final String VIEW = "view";
	private static final String VIEW_ID = "viewID";
	private static final String VIEW_CONTAINER = "viewContainer";
	private static final String VIEW_CONTAINER_ID = "viewContainerID";
	// private static final String ALL_VIEWS = "allCurrentViews";
	private static final String PARAMS = "params";
	// private static final String RETURN_PATH = "returnPath";

	public static final NotificationType NOTIFTYPE_VIEW_OPENED = new NotificationType.Builder(
			"UI", "View:opened", "A view was opened (notification).",
			Severity.DEBUG).defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER, ViewContainer.class, true)
			.buildAndRegister();;
	public static final NotificationType NOTIFTYPE_VIEW_CLOSED = new NotificationType.Builder(
			"UI", "View:closed", "A view was closed (notification).",
			Severity.DEBUG).defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER, ViewContainer.class, true)
			.buildAndRegister();;
	public static final NotificationType NOTIFTYPE_OPEN_VIEW = new NotificationType.Builder(
			"UI", "View:open", "Open a view.", Severity.INFO)
			.defineParameter(VIEW_ID, String.class, true)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.buildAndRegister();
	public static NotificationType NOTIFTYPE_CLOSE_VIEW = new NotificationType.Builder(
			"UI", "View:close", "Close a view as passed by viewID.",
			Severity.INFO).defineParameter(VIEW_ID, String.class, true)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.buildAndRegister();;

	public ViewCommand(NotificationType notif) {
		super(notif);
	}

	public View<?> getView() {
		return getAttribute(VIEW, View.class);
	}

	public String getViewID() {
		return getAttribute(VIEW_ID, String.class);
	}

	public ViewContainer getViewContainer() {
		return getAttribute(VIEW_CONTAINER, ViewContainer.class);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getParameters() {
		return getAttribute(PARAMS, Map.class);
	}

	public void setView(View view) {
		if (view == null) {
			throw new IllegalArgumentException("view is required.");
		}
		setAttribute(VIEW, view);
	}

	public void setViewContainer(ViewContainer container) {
		setAttribute(VIEW_CONTAINER, container);
	}

	public void setViewID(String viewID) {
		if (viewID == null) {
			throw new IllegalArgumentException("viewID is required.");
		}
		setAttribute(VIEW_ID, viewID);
	}

	public void setContainerID(String viewContainerID) {
		setAttribute(VIEW_CONTAINER_ID, viewContainerID);
	}

	public String getViewContainerID() {
		return getAttribute(VIEW_CONTAINER_ID, String.class);
	}

	public void setViewContainerID(String viewContainerID) {
		setAttribute(VIEW_CONTAINER_ID, viewContainerID);
	}

	// public void setReturnPath(String returnPath) {
	// setData(RETURN_PATH, returnPath);
	// }

}
