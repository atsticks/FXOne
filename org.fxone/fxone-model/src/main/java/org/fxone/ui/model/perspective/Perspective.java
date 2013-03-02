package org.fxone.ui.model.perspective;

import org.fxone.core.types.Adaptable;
import org.fxone.ui.model.workbench.Workbench;

public interface Perspective<T> extends Adaptable {

	public void activated(Workbench owner);

	public void deactivated(Workbench owner);
	
	public T getUI();

}
