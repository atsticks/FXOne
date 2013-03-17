package org.fxone.ui.rt.components.dialog;

import java.util.Arrays;

public final class DialogContext {

	private String id;
	private Object[] params;
	// private UIComponent dialogSpec;
	private DialogContainer dialogContainer;

	public DialogContext(String id, DialogContainer dialogContainer,
			Object... params) {
		if (id == null) {
			throw new IllegalArgumentException("Id may not be null.");
		}
		if (dialogContainer == null) {
			throw new IllegalArgumentException(
					"dialogContainer may not be null.");
		}
		this.id = id;
		// this.dialogSpec = dialogSpec;
		this.params = params;
		this.dialogContainer = dialogContainer;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	//
	// public UIComponent getDialogSpec() {
	// return this.dialogSpec;
	// }

	/**
	 * @return the params
	 */
	public final Object[] getParams() {
		return params;
	}

	public DialogContainer getDialogContainer() {
		return dialogContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DialogContext [id=" + id // + ", dialogSpec=" + dialogSpec
				+ ", params=" + Arrays.toString(params) + "]";
	}

}
