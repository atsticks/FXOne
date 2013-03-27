package org.fxone.ui.model.nav;

import java.util.Enumeration;

public interface NavigateableArea extends NavigateableAction {

	public Enumeration<NavigateableArea> getChildAreas();

	public Enumeration<NavigateableAction> getCommands();

	public boolean isRoot();

	public NavigateableArea getChildArea(String path);
	
	public NavigateableAction getCommand(String path);

	public Enumeration<Navigateable> getItems();

}
