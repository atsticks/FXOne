package org.fxone.ui.rt.components.dialog.impl;

import javafx.event.ActionEvent;
import javafx.scene.Node;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.fxone.ui.rt.components.dialog.Dialog;
import org.fxone.ui.rt.components.dialog.DialogManager;

/**
 * 
 * @author Anton Smirnov (dev@antonsmirnov.name)
 */
@Singleton
public final class DialogManagerImpl implements DialogManager {

	private ModalDialogPane modalPane;

	@Inject
	public DialogManagerImpl(@Named("workbench") Node workbench) {
		this.modalPane = new ModalDialogPane(workbench);
	}

	/**
	 * Show information dialog box as parentWindow child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showInfo(String title, String message) {
//		this.modalPane.showDialog(new InfoDialog(title, message)); // TODO
	}

	/**
	 * Show information dialog box as parentStage child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showYesNo(String title, String message,
			javafx.event.EventHandler<ActionEvent> h) {
//		this.modalPane.showDialog(new YesNoDialog(title, message, h)); // TODO
	}

	/**
	 * Show warning dialog box as parentStage child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showWarning(String title, String message) {
//		this.modalPane.showDialog(new WarningDialog(title, message)); // TODO
	}

	/**
	 * Show error dialog box
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showError(String title, String message) {
//		this.modalPane.showDialog(new ErrorDialog(title, message)); // TODO
	}

	/**
	 * Show error dialog box with stacktrace
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param t
	 *            throwable
	 */
	public void showThrowable(String title, String message, Throwable t) {
//		this.modalPane.showDialog(new ThrowableDialog(title, message, t)); // TODO
	}

	/**
	 * Opens the given dialog.
	 * 
	 * @param dialog
	 *            the dialog.
	 */
	@Override
	public void showDialog(Dialog dialog) {
		this.modalPane.showDialog(dialog);
	}

}
