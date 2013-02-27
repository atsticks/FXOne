package org.fxone.ui.rt.components.header;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.impl.HeaderCommand;
import org.fxone.ui.model.nav.impl.NavigationAreaImpl;
import org.fxone.ui.rt.components.view.ViewMenu;

@Dependent
@Named("header-menu")
@Default
public class HeaderMenu extends HBox {
	
	@Inject
	public HeaderMenu(NavigationManager man, ViewMenu viewMenu) {
		setId("header-menu");
		getChildren().add(viewMenu);
		setAlignment(Pos.BASELINE_RIGHT);
	}
	
}
