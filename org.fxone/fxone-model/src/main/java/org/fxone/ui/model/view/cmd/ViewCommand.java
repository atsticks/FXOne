package org.fxone.ui.model.view.cmd;

import java.util.Map;
import java.util.Set;

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
	private static final String VIEW_CONTEXT = "viewContext";
	private static final String VIEW_CONTAINER_ID = "viewContainerID";
	private static final String VIEW_CONTAINER = "viewContainer";
	private static final String PARAMS = "params";

	public static final NotificationType NOTIFTYPE_VIEW_OPEN = new NotificationType.Builder(
			"UI", "View:open", "Open a view instance.", Severity.INFO)
			.defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.addResult(Boolean.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_VIEW_OPENED = new NotificationType.Builder(
			"UI", "View:opened", "A view instance was openend.", Severity.INFO)
			.defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER, ViewContainer.class, false)
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_VIEW_CLOSED = new NotificationType.Builder(
			"UI", "View:closed", "A view instance was closed.", Severity.INFO)
			.defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER, ViewContainer.class, false)
			.buildAndRegister();

	public static final NotificationType NOTIFTYPE_VIEW_CLOSE = new NotificationType.Builder(
			"UI", "View:close", "A view was closed (notification).",
			Severity.INFO).defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_CURRENT_VIEW = new NotificationType.Builder(
			"UI", "View:getCurrentView", "Access the current a view.",
			Severity.UNKNOWN)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.addResult(Set.class).buildAndRegister();

	public static final NotificationType NOTIFTYPE_CREATE_VIEW = new NotificationType.Builder(
			"UI", "View:create", "Create a new view instance.",
			Severity.UNKNOWN)
			.defineParameter(VIEW_CONTEXT, ViewContext.class, true)
			.addResult(View.class).buildAndRegister();

	public ViewCommand(NotificationType notif) {
		super(notif);
	}

	public View<?> getView() {
		return getAttribute(VIEW, View.class);
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

	public String getViewContainerID() {
		return getAttribute(VIEW_CONTAINER_ID, String.class);
	}

	public void setViewContainerID(String viewContainerID) {
		setAttribute(VIEW_CONTAINER_ID, viewContainerID);
	}

	public void setViewContext(ViewContext context) {
		setAttribute(VIEW_CONTEXT, context);
	}

	public ViewContext getViewContext() {
		return getAttribute(VIEW_CONTEXT, ViewContext.class);
	}

	public ViewContainer<?> getViewContainer() {
		return getAttribute(VIEW_CONTAINER, ViewContainer.class);
	}

	public void setViewContainer(ViewContainer<?> viewContainer) {
		setAttribute(VIEW_CONTAINER, viewContainer);
	}

}
