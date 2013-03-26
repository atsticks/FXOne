package org.fxone.ui.model.workbench;


public interface Perspective {

	public void activated(Workbench owner);

	public void deactivated(Workbench owner);
	
}
