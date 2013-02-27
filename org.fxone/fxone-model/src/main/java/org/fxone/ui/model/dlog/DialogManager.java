package org.fxone.ui.model.dlog;

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
	 * Show error dialog box with stacktrace
	 * 
	 * @param title
	 *            dialog title
	 * @param message
	 *            dialog message
	 * @param t
	 *            throwable
	 */
	public void showThrowable(String title, String message, Throwable t);

	/**
	 * Shpw up the dialog.
	 * 
	 * @param dialog
	 */
	public void openDialog(String dialog, Object... params);

}
