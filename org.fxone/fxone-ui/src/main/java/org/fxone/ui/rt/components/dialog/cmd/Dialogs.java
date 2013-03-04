package org.fxone.ui.rt.components.dialog.cmd;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.rt.components.dialog.Dialog;
import org.fxone.ui.rt.components.dialog.DialogEvent;

public final class Dialogs {

	private Dialogs(){
		// singleton
	}

	public static Future<DialogEvent> openDialog(Dialog dialog) {
		return NotificationService.get().publishEvent(
				createOpenDialogNotif(dialog), DialogEvent.class);
	}

	public static Future<DialogEvent> closeAllDialogs() {
		return NotificationService.get().publishEvent(
				createCloseAllDialogsNotif(), DialogEvent.class);
	}

	public static DialogEvent createOpenDialogNotif(Dialog dialog) {
		DialogEvent notif = new DialogEvent(DialogEvent.NOTIFTYPE_OPEN);
		notif.setDialog(dialog);
		return notif;
	}

	public static DialogEvent createCloseAllDialogsNotif() {
		return createCloseAllDialogsNotif(false);
	}
	
	public static DialogEvent createCloseAllDialogsNotif(boolean forced) {
		DialogEvent notif = new DialogEvent(DialogEvent.NOTIFTYPE_CLOSEALL);
		notif.setForced(forced);
		return notif;
	}

}
