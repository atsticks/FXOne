package org.fxone.ui.rt.components.api;


import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.UICommand;

public interface Breadcrumbs {

	public NavigateableAction[] getPath();
	
	public void setPath(NavigateableAction[] actions);
	
	public void setPath(NavigateableArea area);
	
	public void setPath(UICommand command);
}
