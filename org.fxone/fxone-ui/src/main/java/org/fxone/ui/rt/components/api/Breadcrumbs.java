package org.fxone.ui.rt.components.api;


import org.fxone.ui.model.nav.NavigationArea;
import org.fxone.ui.model.nav.UIAction;
import org.fxone.ui.model.nav.UICommand;

public interface Breadcrumbs {

	public UIAction[] getPath();
	
	public void setPath(UIAction[] actions);
	
	public void setPath(NavigationArea area);
	
	public void setPath(UICommand command);
}
