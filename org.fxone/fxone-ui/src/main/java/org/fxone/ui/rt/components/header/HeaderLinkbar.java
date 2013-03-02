package org.fxone.ui.rt.components.header;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.impl.HeaderCommand;
import org.fxone.ui.rt.components.view.ViewMenu;
import org.fxone.ui.rt.nav.CommandLink;

@Dependent
@Named("header-linkbar")
@Default
public class HeaderLinkbar extends HBox {
	
	@Inject
	public HeaderLinkbar(NavigationManager man, ViewMenu viewMenu, Instance<HeaderCommand> headerCommands) {
		setId("header-linkbar");
		setAlignment(Pos.BASELINE_RIGHT);
		for (HeaderCommand cmd : headerCommands) {
			getChildren().add(new CommandLink(cmd));
		}
	}
	
}
