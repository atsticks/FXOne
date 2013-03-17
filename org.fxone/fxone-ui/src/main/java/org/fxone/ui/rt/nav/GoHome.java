package org.fxone.ui.rt.nav;

import org.fxone.ui.annot.UINavigationCommand;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.model.view.cmd.Views;

@UINavigationCommand(value = "home", perspective="view")
public class GoHome extends AbstractUIAction {

	@Override
	public void run() {
		Views.openView("dashboard");
	}

}