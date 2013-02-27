package org.fxone.ui.rt.nav;

import org.fxone.ui.annot.UINavigation;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.model.view.cmd.Views;

@UINavigation(target = "help/help")
public class GoHelp extends AbstractUIAction {

	@Override
	public void run() {
		Views.openView("help");
	}

}
