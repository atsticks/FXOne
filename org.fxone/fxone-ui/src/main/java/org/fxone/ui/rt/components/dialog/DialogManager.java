package org.fxone.ui.rt.components.dialog;

public interface DialogManager {

	/**
	 * Show information dialog box as parentStage child
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showInfo(String title, String message);

	/**
	 * Show warning dialog box
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showWarning(String title, String message);

	/**
	 * Show error dialog box
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 */
	public void showError(String title, String message);

	/**
	 * Shpw up the dialog.
	 * 
	 * @param dialog
	 */
	public void showDialog(Dialog dialog);

}
