package org.fxone.ui.rt.components.header;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.rt.nav.CommandLink;

@Dependent
@Named("header-linkbar")
@Default
public class HeaderLinkbar extends HBox {
	
	@Inject
	public HeaderLinkbar(NavigationManager man) {
		setId("header-linkbar");
		setAlignment(Pos.BASELINE_RIGHT);
		NavigateableArea area = man.getRootNavigation("header");
		for (NavigateableAction cmd : area.getCommands()) {
			getChildren().add(new CommandLink(cmd));
		}
	}
	
}
