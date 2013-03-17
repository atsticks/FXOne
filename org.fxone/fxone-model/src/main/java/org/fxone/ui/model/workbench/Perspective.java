package org.fxone.ui.model.workbench;

import org.fxone.core.types.Adaptable;

public interface Perspective<T> extends Adaptable {

	public void activated(Workbench owner);

	public void deactivated(Workbench owner);
	
	public T getUI();

}
