package org.fxone.ui.model.view.cmd;

import java.util.Collection;
import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewCommand;
import org.fxone.ui.model.view.ViewContainer;

public class Views {

	private Views() {
		// singleton
	}
	
	public static Future<ViewCommand> viewOpened(
			ViewContainer container, View view) {
		return viewOpened(container, view, null);
	}

	public static Future<ViewCommand> viewOpened(
			ViewContainer container, View view, Collection<View> allViews) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_VIEW_OPENED);
		notif.setView(view);
		notif.setViewContainer(container);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> viewClosed(
			ViewContainer container, View view) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_VIEW_CLOSED);
		notif.setView(view);
		notif.setViewContainer(container);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> openView(String viewID, String viewContainerID){
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_OPEN_VIEW);
		notif.setViewID(viewID);
		notif.setContainerID(viewContainerID);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}
	
	public static Future<ViewCommand> openView(String viewID){
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_OPEN_VIEW);
		notif.setViewID(viewID);
		return NotificationService.get().publishEvent(notif, ViewCommand.class);
	}

	public static Future<ViewCommand> closeView(String viewID,
			String viewContainerID) {
		return NotificationService.get().publishEvent(
				createCloseViewNotif(viewID, viewContainerID), ViewCommand.class);
	}

	public static ViewCommand createOpenView(String viewID,
			String viewContainerID) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_OPEN_VIEW);
		notif.setViewID(viewID);
		if (viewContainerID != null) {
			notif.setViewContainerID(viewContainerID);
		}
		return notif;
	}

	public static ViewCommand createCloseViewNotif(
			String viewID, String viewContainerID){ // , String returnPath) {
		ViewCommand notif = new ViewCommand(ViewCommand.NOTIFTYPE_CLOSE_VIEW);
		notif.setViewID(viewID);
		if(viewContainerID!=null){
			notif.setViewContainerID(viewContainerID);
		}
		return notif;
	}

}
