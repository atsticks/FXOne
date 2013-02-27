package org.fxone.ui.model.nav;

import java.util.Collection;

public interface NavigationArea extends UICommand {

	public Collection<NavigationArea> getChildAreas();

	public Collection<UICommand> getCommands();

	public boolean isRoot();
	
	public boolean isRunnable();

}
