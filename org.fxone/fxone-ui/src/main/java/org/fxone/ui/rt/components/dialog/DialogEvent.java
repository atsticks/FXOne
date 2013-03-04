package org.fxone.ui.rt.components.dialog;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;

public final class DialogEvent extends Notification {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1497516726287509917L;

	private static final String FORCED = "forced";
	private static final String DIALOG = "dialog";

	public static final NotificationType NOTIFTYPE_OPEN = new NotificationType.Builder(
			"UI", "Dialog:openDialog", "Opens the given dialog.",
			Severity.DEBUG).defineParameter(DIALOG, String.class)
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_CLOSE = new NotificationType.Builder(
			"UI", "Dialog:closeDialog", "Closes the current dialog.",
			Severity.DEBUG).defineParameter(FORCED, Boolean.class, false)
			.defineParameter(DIALOG, String.class).addResult(Boolean.class)
			.buildAndRegister();
	public static final NotificationType NOTIFTYPE_ISOPEN = new NotificationType.Builder(
			"UI", "Dialog:isDialogOpenened",
			"Check if a dialog is currently open.", Severity.DEBUG)
			.defineParameter(DIALOG, String.class, false)
			.addResult(Boolean.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_CLOSEALL = new NotificationType.Builder(
			"UI", "Dialog:closeAllDialogs", "Closes all dialogs.",
			Severity.DEBUG).defineParameter(FORCED, Boolean.class, false)
			.addResult(Boolean.class).buildAndRegister();
	public static final NotificationType NOTIFTYPE_GETCURRENT = new NotificationType.Builder(
			"UI", "Dialog:getCurrentDialog", "Opens the given dialog.",
			Severity.DEBUG).addResult(Dialog.class).buildAndRegister();

	public DialogEvent(NotificationType type) {
		super(type);
	}

	public boolean isForced() {
		Boolean bool = getAttribute(FORCED, Boolean.class);
		if (bool != null) {
			return bool.booleanValue();
		}
		return false;
	}

	public void setForced(boolean force) {
		setAttribute(FORCED, Boolean.valueOf(force));
	}

	public void setDialog(Dialog dialog) {
		setAttribute(DIALOG, dialog);
	}

	public Dialog getDialog() {
		return getAttribute(DIALOG, Dialog.class);
	}

}
