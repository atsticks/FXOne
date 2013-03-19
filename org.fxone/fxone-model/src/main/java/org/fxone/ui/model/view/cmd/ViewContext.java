package org.fxone.ui.model.view.cmd;

import java.util.Arrays;

import org.fxone.ui.model.workbench.Workbench;

public final class ViewContext {

	private String id;
	private Workbench workbench;
	private Object[] params;

	public ViewContext(String id, String viewContainerID, Object... params) {
		if (id == null) {
			throw new IllegalArgumentException("Id may not be null.");
		}
		this.id = id;
		this.params = params;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
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
		return "ViewContext [id=" + id + ", viewSpec=" // + viewSpec +
														// ", params="
				+ Arrays.toString(params) + "]";
	}

	public Workbench getWorkbench() {
		return workbench;
	}

	public void setWorkbench(Workbench workbench) {
		if (workbench == null) {
			throw new IllegalArgumentException("Workbench is required.");
		}
		this.workbench = workbench;
	}

}
