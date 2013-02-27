package org.fxone.ui.model.nav;

import org.fxone.core.types.Identifiable;

public interface UICommand extends UIAction, Identifiable {

	public String getPath();
	
	public NavigationArea getParent();
	
	public String getPerspective();
	
}
