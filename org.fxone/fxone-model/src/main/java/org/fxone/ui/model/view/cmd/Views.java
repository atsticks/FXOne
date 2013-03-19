package org.fxone.ui.model.view.cmd;

import java.util.HashSet;
import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Views {

	private static final Logger LOGGER = LoggerFactory.getLogger(Views.class);

	private Views() {
		// singleton
	}

	public static Future<ViewCommand> openView(View view, String viewContainerID) {
		ViewCommand notif = createOpenViewNotif(view, viewContainerID);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> openView(View view) {
		ViewCommand notif = createOpenViewNotif(view, null);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> closeView(View view,
			String viewContainerID) {
		ViewCommand notif = createCloseViewNotif(view, viewContainerID);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> closeView(View view) {
		ViewCommand notif = createCloseViewNotif(view, null);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> getCurrentViews() {
		ViewCommand notif = createGetCurrentViewsNotif(null);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> getCurrentViews(String viewContainerID) {
		ViewCommand notif = createGetCurrentViewsNotif(viewContainerID);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static ViewCommand createCreateViewNotif(ViewContext context) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_CREATE_VIEW);
		notif.setViewContext(context);
		return notif;
	}

	public static ViewCommand createGetCurrentViewsNotif(String viewContainerID) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_CURRENT_VIEW);
		if (viewContainerID != null) {
			notif.setViewContainerID(viewContainerID);
		}
		notif.setResult(new HashSet<View>());
		return notif;
	}

	public static ViewCommand createOpenViewNotif(View view,
			String viewContainerID) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_VIEW_OPEN);
		notif.setView(view);
		if (viewContainerID != null) {
			notif.setViewContainerID(viewContainerID);
		}
		return notif;
	}

	public static ViewCommand createCloseViewNotif(View view,
			String viewContainerID) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_VIEW_CLOSE);
		notif.setView(view);
		if (viewContainerID != null) {
			notif.setViewContainerID(viewContainerID);
		}
		return notif;
	}

	public static View createView(ViewContext ctx) {
		ViewCommand cmd = createCreateViewNotif(ctx);
		Future<ViewCommand> fut = NotificationService.get().publishEvent(cmd,
				ViewCommand.class);
		try {
			return fut.get().getView();
		} catch (Exception e) {
			LOGGER.warn("Error creating view: " + ctx, e);
			return null;
		}
	}

	public static Future<ViewCommand> viewOpenened(
			ViewContainer viewContainer, View closedView) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_VIEW_OPENED);
		notif.setView(closedView);
		notif.setViewContainer(viewContainer);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}
	
	public static Future<ViewCommand> viewClosed(
			ViewContainer viewContainer, View closedView) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_VIEW_CLOSED);
		notif.setView(closedView);
		notif.setViewContainer(viewContainer);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

}
