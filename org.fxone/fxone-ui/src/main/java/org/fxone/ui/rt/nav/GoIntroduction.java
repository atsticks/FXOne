package org.fxone.ui.rt.nav;

import org.fxone.ui.annot.UINavigationCommand;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.model.view.cmd.Views;

@UINavigationCommand(value = "home/introduction", priority=10)
public class GoIntroduction extends AbstractUIAction {

	@Override
	public void run() {
		Views.openView("introduction");
	}

}
