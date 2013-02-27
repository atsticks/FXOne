package org.fxone.ui.model.view.cmd;

import java.util.Map;

import org.fxone.core.events.EventGroup;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationDefinition;
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

	public static final NotificationDefinition NOTIFTYPE_VIEW_OPENED = new NotificationDefinition(
			EventGroup.UI.toString(), "View:opened",
			"A view was opened (notification).", Severity.DEBUG)
			.defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER, ViewContainer.class, true)
			.setReadOnly();;
	public static final NotificationDefinition NOTIFTYPE_VIEW_CLOSED = new NotificationDefinition(
			EventGroup.UI.toString(), "View:closed",
			"A view was closed (notification).", Severity.DEBUG)
			.defineParameter(VIEW, View.class, true)
			.defineParameter(VIEW_CONTAINER, ViewContainer.class, true)
			.setReadOnly();;
	public static final NotificationDefinition NOTIFTYPE_OPEN_VIEW = new NotificationDefinition(
			EventGroup.UI.toString(), "View:open", "Open a view.",
			Severity.INFO).defineParameter(VIEW_ID, String.class, true)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.setReadOnly();
	public static NotificationDefinition NOTIFTYPE_CLOSE_VIEW = new NotificationDefinition(
			EventGroup.UI.toString(), "View:close",
			"Close a view as passed by viewID.", Severity.INFO)
			.defineParameter(VIEW_ID, String.class, true)
			.defineParameter(VIEW_CONTAINER_ID, String.class, false)
			.setReadOnly();;

	ViewCommand(NotificationDefinition notif) {
		super(notif);
	}

	public View getView() {
		return getData(VIEW, View.class);
	}

	public String getViewID() {
		return getData(VIEW_ID, String.class);
	}

	public ViewContainer getViewContainer() {
		return getData(VIEW_CONTAINER, ViewContainer.class);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getParameters() {
		return getData(PARAMS, Map.class);
	}

	public void setView(View view) {
		if (view == null) {
			throw new IllegalArgumentException("view is required.");
		}
		setData(VIEW, view);
	}

	public void setViewContainer(ViewContainer container) {
		setData(VIEW_CONTAINER, container);
	}

	public void setViewID(String viewID) {
		if (viewID == null) {
			throw new IllegalArgumentException("viewID is required.");
		}
		setData(VIEW_ID, viewID);
	}

	public void setContainerID(String viewContainerID) {
		setData(VIEW_CONTAINER_ID, viewContainerID);
	}
	
	public String getViewContainerID() {
		return getData(VIEW_CONTAINER_ID, String.class);
	}

	public void setViewContainerID(String viewContainerID) {
		setData(VIEW_CONTAINER_ID, viewContainerID);
	}

//	public void setReturnPath(String returnPath) {
//		setData(RETURN_PATH, returnPath);
//	}

}
