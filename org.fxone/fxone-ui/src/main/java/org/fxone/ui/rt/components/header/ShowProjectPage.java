package org.fxone.ui.rt.components.header;

import org.fxone.ui.annot.ProvidedCommands;
import org.fxone.ui.annot.UICommand;
import org.fxone.ui.model.Model;
import org.fxone.ui.model.nav.AbstractUIAction;
import org.fxone.ui.rt.components.view.WebPage;

@ProvidedCommands(value = { @UICommand(value = "project", tree = "header") })
public class ShowProjectPage extends AbstractUIAction {

	@Override
	public void run() {
		WebPage view = new WebPage("http://github.com/atsticks/fxone");
		Model.Views.openView(view, "default");
	}

}