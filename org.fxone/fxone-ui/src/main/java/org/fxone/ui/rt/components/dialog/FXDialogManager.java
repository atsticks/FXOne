package org.fxone.ui.rt.components.dialog;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;

import org.fxone.ui.model.dlog.DialogManager;
import org.fxone.ui.rt.components.dialog.Dialog.DialogBuilder;

public interface FXDialogManager extends DialogManager {
	/**
	 * Show information dialog box as parentWindow child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param owner
	 *            parent window
	 */
	public void showInfo(String title, String message, Window owner);

	/**
	 * Show information dialog box as parentStage child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showYesNo(String title, String message,
			javafx.event.EventHandler<ActionEvent> h);

	/**
	 * Show information dialog box as parentWindow child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param owner
	 *            parent window
	 */
	public void showYesNo(String title, String message, Window owner,
			javafx.event.EventHandler<ActionEvent> h);

	/**
	 * Show warning dialog box as parentStage child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param owner
	 *            parent window
	 */
	public void showWarning(String title, String message, Window owner);

	/**
	 * Show error dialog box
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param owner
	 *            parent window
	 */
	public DialogBuilder buildDialog(String title, Node content, Window owner);

	/**
	 * Show error dialog box
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public DialogBuilder buildDialog(String title, Node content);

	/**
	 * Show error dialog box
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param owner
	 *            parent window
	 */
	public void showError(String title, String message, Window owner);

	/**
	 * Show error dialog box with stacktrace
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param t
	 *            throwable
	 * @param owner
	 *            parent window
	 */
	public void showThrowable(String title, String message, Throwable t,
			Window owner);

	/**
	 * Build confirmation dialog builder
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param owner
	 *            parent window
	 * @return
	 */
	public DialogBuilder buildConfirmation(String title, String message,
			Window owner);

	/**
	 * Build confirmation dialog builder
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @return
	 */
	public DialogBuilder buildConfirmation(String title, String message);
}
