package org.fxone.ui.rt.nav;

import org.fxone.core.cdi.Container;
import org.fxone.ui.annot.ProvidedCommands;
import org.fxone.ui.annot.UICommand;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.model.workbench.Workbench;

@ProvidedCommands(value = { @UICommand("perspectives/default") })
public class OpenDefaultPerspective extends AbstractUIAction {

	@Override
	public void run() {
		Workbench workbench = Container.getInstance(Workbench.class);
		if(workbench!=null){
			workbench.setDefaultPerspective();
		}
	}

}
