package org.fxone.ui.model.dlog.cmd;

import org.fxone.core.events.EventGroup;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.Severity;
import org.fxone.core.events.notif.AsynchMethodCall;
import org.fxone.ui.model.dlog.Dialog;

public final class DialogRequest extends AsynchMethodCall {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1497516726287509917L;

	private static final String FORCED = "forced";
	private static final String RESULT = "result";
	private static final String DIALOG = "dialog";

	public static final NotificationDefinition NOTIFTYPE_OPEN = new NotificationDefinition(
			EventGroup.UI.toString(), "Dialog:openDialog",
			"Opens the given dialog.", Severity.DEBUG)
			.defineParameter(DIALOG, String.class).setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_CLOSE = new NotificationDefinition(
			EventGroup.UI.toString(), "Dialog:closeDialog",
			"Closes the current dialog.", Severity.DEBUG)
			.defineParameter(FORCED, Boolean.class, false)
			.defineParameter(DIALOG, String.class)
			.defineParameter(RESULT, Boolean.class, false).setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_ISOPEN = new NotificationDefinition(
			EventGroup.UI.toString(), "Dialog:isDialogOpenened",
			"Check if a dialog is currently open.", Severity.DEBUG)
			.defineParameter(DIALOG, String.class, false)
			.defineParameter(RESULT, Boolean.class).setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_CLOSEALL = new NotificationDefinition(
			EventGroup.UI.toString(), "Dialog:closeAllDialogs",
			"Closes all dialogs.", Severity.DEBUG)
			.defineParameter(FORCED, Boolean.class, false)
			.defineParameter(RESULT, Boolean.class, false).setReadOnly();
	public static final NotificationDefinition NOTIFTYPE_GETCURRENT = new NotificationDefinition(
			EventGroup.UI.toString(), "Dialog:getCurrentDialog",
			"Opens the given dialog.", Severity.DEBUG).defineParameter(RESULT,
			Dialog.class, false).setReadOnly();

	public DialogRequest(NotificationDefinition type) {
		super(type);
	}

	public Dialog getDialog() {
		return getData(DIALOG, Dialog.class);
	}

	public void setDialog(Dialog dlog) {
		if (dlog == null) {
			throw new IllegalArgumentException("Dialog must not be null.");
		}
		setData(DIALOG, dlog);
	}

	public boolean isForced() {
		Boolean bool = getData(FORCED, Boolean.class);
		if (bool != null) {
			return bool.booleanValue();
		}
		return false;
	}

	public void setForced(boolean force) {
		setData(FORCED, Boolean.valueOf(force));
	}
}
