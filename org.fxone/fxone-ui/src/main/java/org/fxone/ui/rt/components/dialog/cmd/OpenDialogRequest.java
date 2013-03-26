package org.fxone.ui.rt.components.dialog.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.rt.components.dialog.Dialog;

public final class OpenDialogRequest extends AbstractNotification<Boolean> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1497516726287509917L;

	private boolean forced;
	private Dialog dialog;

	public static final NotificationType NOTIFTYPE_OPEN = new NotificationType.Builder(
			"UI", "Dialog:openDialog", "Opens the given dialog.",
			Severity.UNKNOWN).define(OpenDialogRequest.class)
			.buildAndRegister();

	public OpenDialogRequest() {
		super(DefaultGroups.UI);
	}

	public boolean isForced() {
		return forced;
	}

	public void setForced(boolean forced) {
		checkReadOnly();
		this.forced = forced;
	}

	public void setDialog(Dialog dialog) {
		checkReadOnly();
		if(dialog==null){
			throw new IllegalArgumentException("Dialog is required.");
		}
		this.dialog = dialog;
	}

	public Dialog getDialog() {
		return this.dialog;
	}

}
