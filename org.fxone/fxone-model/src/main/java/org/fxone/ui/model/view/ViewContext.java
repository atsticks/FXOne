package org.fxone.ui.model.view;

import java.util.Arrays;

public final class ViewContext {

	private String id;
	private String viewContainerID;
	private Object[] params;

	public ViewContext(String id, String viewContainerID, Object... params) {
		if (id == null) {
			throw new IllegalArgumentException("Id may not be null.");
		}
		this.id = id;
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
		return "ViewContext [id=" + id + ", viewSpec=" // + viewSpec + ", params="
				+ Arrays.toString(params) + "]";
	}

}
