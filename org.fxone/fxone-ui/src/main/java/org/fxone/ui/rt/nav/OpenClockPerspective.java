package org.fxone.ui.rt.nav;

import org.fxone.core.cdi.Container;
import org.fxone.ui.annot.UINavigationCommand;
import org.fxone.ui.model.Model;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.workbench.Workbench;

@UINavigationCommand("perspectives/clock")
public class OpenClockPerspective extends AbstractUIAction {

	@Override
	public void run() {
		Workbench workbench = Container.getInstance(Workbench.class);
		if(workbench!=null){
			workbench.setCurrentPerspective("clock");
		}
	}

}
