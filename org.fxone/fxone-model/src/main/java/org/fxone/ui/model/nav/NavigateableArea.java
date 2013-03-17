package org.fxone.ui.model.nav;

import java.util.Collection;

public interface NavigateableArea extends NavigateableAction {

	public Collection<NavigateableArea> getChildAreas();

	public Collection<NavigateableAction> getCommands();

	public boolean isRoot();
	

}
