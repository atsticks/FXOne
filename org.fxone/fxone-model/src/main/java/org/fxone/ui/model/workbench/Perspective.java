package org.fxone.ui.model.workbench;


public interface Perspective<T> {

	public void activated(Workbench owner);

	public void deactivated(Workbench owner);
	
	public T getUI();

}
