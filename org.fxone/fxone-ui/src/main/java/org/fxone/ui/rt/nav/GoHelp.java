package org.fxone.ui.rt.nav;

import org.fxone.core.cdi.Container;
import org.fxone.ui.annot.ProvidedCommands;
import org.fxone.ui.annot.UICommand;
import org.fxone.ui.model.Model;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.model.view.View;

@ProvidedCommands(value = { @UICommand("Options/Help"), @UICommand("toolbar/?") })
public class GoHelp extends AbstractUIAction {

	@Override
	public void run() {
		View view = Container.getNamedInstance(View.class,  "help");
		if(view!=null){
			Model.Views.openView(view, "default");
		}
	}

}
