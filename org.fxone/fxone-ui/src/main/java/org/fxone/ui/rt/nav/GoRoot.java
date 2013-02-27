package org.fxone.ui.rt.nav;

import org.fxone.ui.annot.UINavigation;
import org.fxone.ui.model.nav.AbstractUIAction;

@UINavigation(target = "/", perspective = "main")
public class GoRoot extends AbstractUIAction {

	@Override
	public void run() {
		// empty
	}

}
