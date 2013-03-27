package org.fxone.ui.rt.components.header;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.rt.components.perspectives.PerspectiveMenu;
import org.fxone.ui.rt.components.view.ViewMenu;

@Dependent
@Named("header-menu")
@Default
public class HeaderMenu extends HBox {
	
	@Inject
	public HeaderMenu(NavigationManager man, PerspectiveMenu perspectiveMenu) {
		setId("header-menu");
		getChildren().add(perspectiveMenu);
		setAlignment(Pos.BASELINE_RIGHT);
	}
	
}
