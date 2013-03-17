package org.fxone.ui.model.nav;

import org.fxone.core.types.Identifiable;

public interface NavigateableAction extends Runnable, Identifiable {

	public boolean isEnabled();
	
	public String getPath();
	
	public NavigateableArea getParent();

}
