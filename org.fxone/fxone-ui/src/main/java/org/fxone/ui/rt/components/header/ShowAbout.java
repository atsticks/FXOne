package org.fxone.ui.rt.components.header;

import org.fxone.ui.annot.UINavigation;
import org.fxone.ui.model.nav.impl.HeaderCommand;
import org.fxone.ui.model.view.cmd.Views;

@UINavigation(target="help/about")
public class ShowAbout implements HeaderCommand {

	@Override
	public void run() {
		Views.openView("help/about", null);
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
