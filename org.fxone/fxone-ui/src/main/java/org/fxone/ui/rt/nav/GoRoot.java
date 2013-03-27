package org.fxone.ui.rt.nav;

import org.fxone.ui.annot.ProvidedCommands;
import org.fxone.ui.annot.UICommand;
import org.fxone.ui.model.nav.AbstractUIAction;

@ProvidedCommands(value = { @UICommand("/") })
public class GoRoot extends AbstractUIAction {

	@Override
	public void run() {
		// empty
	}

}
