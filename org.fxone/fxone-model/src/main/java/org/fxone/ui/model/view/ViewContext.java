package org.fxone.ui.model.view;

import java.util.Arrays;

import org.fxone.ui.annot.UIComponent;

public final class ViewContext {

	private String id;
	private String viewContainerID;
	private Object[] params;
	private UIComponent viewSpec;

	public ViewContext(String id, String viewContainerID, UIComponent viewSpec, Object... params) {
		if (id == null) {
			throw new IllegalArgumentException("Id may not be null.");
		}
		this.id = id;
		this.viewSpec = viewSpec;
		this.params = params;
		this.viewContainerID = viewContainerID;
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
	public final String getViewContainerID() {
		return viewContainerID;
	}

	public UIComponent getViewSpec() {
		return this.viewSpec;
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
		return "ViewContext [id=" + id + ", viewSpec=" + viewSpec + ", params="
				+ Arrays.toString(params) + "]";
	}

}
