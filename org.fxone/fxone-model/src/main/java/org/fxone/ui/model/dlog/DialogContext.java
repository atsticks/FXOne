package org.fxone.ui.model.dlog;

import java.util.Arrays;

import org.fxone.ui.annot.UIComponent;

public final class DialogContext {

	private String id;
	private DialogManager dialogManager;
	private Object[] params;
	private UIComponent dialogSpec;

	public DialogContext(String id, DialogManager dialogManager,
			UIComponent dialogSpec, Object... params) {
		if (id == null) {
			throw new IllegalArgumentException("Id may not be null.");
		}
		this.id = id;
		this.dialogSpec = dialogSpec;
		this.params = params;
		this.dialogManager = dialogManager;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @return the id
	 */
	public final DialogManager getDialogManager() {
		return dialogManager;
	}

	public UIComponent getDialogSpec() {
		return this.dialogSpec;
	}

	/**
	 * @return the params
	 */
	public final Object[] getParams() {
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DialogContext [id=" + id + ", dialogSpec=" + dialogSpec + ", params="
				+ Arrays.toString(params) + "]";
	}

}
