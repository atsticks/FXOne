package org.fxone.ui.model.nav;

public abstract class AbstractUIAction implements NavigateableAction {

	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getIdentifier() {
		return getClass().getSimpleName();
	}

}
