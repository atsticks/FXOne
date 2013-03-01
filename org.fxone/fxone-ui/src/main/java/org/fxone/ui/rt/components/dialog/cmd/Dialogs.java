package org.fxone.ui.rt.components.dialog.cmd;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.rt.components.dialog.Dialog;

public final class Dialogs {

	private Dialogs(){
		// singleton
	}

	public static Future<DialogRequest> openDialog(Dialog dialog) {
		return NotificationService.get().publishEvent(
				createOpenDialogNotif(dialog), DialogRequest.class);
	}

	public static Future<DialogRequest> closeAllDialogs() {
		return NotificationService.get().publishEvent(
				createCloseAllDialogsNotif(), DialogRequest.class);
	}

	public static DialogRequest createOpenDialogNotif(Dialog dialog) {
		DialogRequest notif = new DialogRequest(DialogRequest.NOTIFTYPE_OPEN);
		notif.setDialog(dialog);
		return notif;
	}

	public static DialogRequest createCloseAllDialogsNotif() {
		return createCloseAllDialogsNotif(false);
	}
	
	public static DialogRequest createCloseAllDialogsNotif(boolean forced) {
		DialogRequest notif = new DialogRequest(DialogRequest.NOTIFTYPE_CLOSEALL);
		notif.setForced(forced);
		return notif;
	}

}
