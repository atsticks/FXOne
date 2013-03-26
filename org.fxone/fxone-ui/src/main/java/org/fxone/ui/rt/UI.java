package org.fxone.ui.rt;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.rt.components.dialog.Dialog;
import org.fxone.ui.rt.components.dialog.cmd.OpenDialogRequest;

public final class UI {

	private UI() {
	}
	
	public static final class Dialogs {

		private Dialogs(){
			// singleton
		}

		public static Future<OpenDialogRequest> openDialog(Dialog dialog) {
			return NotificationService.get().publishEvent(
					createOpenDialogNotif(dialog), OpenDialogRequest.class);
		}

		private static OpenDialogRequest createOpenDialogNotif(Dialog dialog) {
			OpenDialogRequest notif = new OpenDialogRequest();
			notif.setDialog(dialog);
			return notif;
		}

	}
	
}
