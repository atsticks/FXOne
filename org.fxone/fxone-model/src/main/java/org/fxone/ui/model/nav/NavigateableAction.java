package org.fxone.ui.model.nav;


public interface NavigateableAction extends Runnable, Navigateable {

	public boolean isEnabled();
	
}
