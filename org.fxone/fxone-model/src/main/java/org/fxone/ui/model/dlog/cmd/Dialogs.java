package org.fxone.ui.model.dlog.cmd;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.dlog.Dialog;

public final class Dialogs {

	private Dialogs(){
		// singleton
	}

	public static Future<DialogRequest> closeDialog(Dialog dialog) {
		return closeDialog(dialog, false);
	}

	public static Future<DialogRequest> closeDialog(
			Dialog dialog, boolean force) {
		return NotificationService.get().publishEvent(
				createCloseDialogNotif(dialog, force),
				DialogRequest.class);
	}

	public static Future<DialogRequest> openDialog(Dialog dialog) {
		return NotificationService.get().publishEvent(
				createOpenDialogNotif(dialog), DialogRequest.class);
	}

	public static Future<DialogRequest> closeAllDialogs() {
		return NotificationService.get().publishEvent(
				createCloseAllDialogsNotif(), DialogRequest.class);
	}

	public static Future<DialogRequest> isOpen(Dialog dialog) {
		DialogRequest notif = new DialogRequest(DialogRequest.NOTIFTYPE_ISOPEN);
		if (dialog != null) {
			notif.setDialog(dialog);
		}
		return NotificationService.get().publishEvent(notif,
				DialogRequest.class);
	}

	public static Future<DialogRequest> getCurrent() {
		DialogRequest notif = new DialogRequest(DialogRequest.NOTIFTYPE_GETCURRENT);
		return NotificationService.get().publishEvent(notif,
				DialogRequest.class);
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

	public static DialogRequest createCloseDialogNotif() {
		return createCloseDialogNotif(null, false);
	}
	
	public static DialogRequest createCloseDialogNotif(
			boolean force) {
		return createCloseDialogNotif(null, force);
	}
	
	public static DialogRequest createCloseDialogNotif(Dialog dialog,
			boolean force) {
		DialogRequest notif = new DialogRequest(DialogRequest.NOTIFTYPE_CLOSE);
		if(dialog!=null){
			notif.setDialog(dialog);
		}
		notif.setForced(force);
		return notif;
	}

	public static DialogRequest createCloseDialogNotif(Dialog dialog) {
		return createCloseDialogNotif(dialog, false);
	}
}
