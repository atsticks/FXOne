package org.fxone.ui.rt.components.header;

import org.fxone.ui.annot.UINavigation;
import org.fxone.ui.model.nav.impl.HeaderCommand;
import org.fxone.ui.model.view.cmd.Views;

@UINavigation(target="settings/user")
public class ShowUserSettings implements HeaderCommand {

	@Override
	public void run() {
		Views.openView("settings/user");
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
