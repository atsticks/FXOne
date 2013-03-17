package org.fxone.ui.rt.nav;

import org.fxone.ui.annot.UINavigationCommand;
import org.fxone.ui.model.nav.AbstractUIAction;

@UINavigationCommand(value = "/", perspective = "main")
public class GoRoot extends AbstractUIAction {

	@Override
	public void run() {
		// empty
	}

}
