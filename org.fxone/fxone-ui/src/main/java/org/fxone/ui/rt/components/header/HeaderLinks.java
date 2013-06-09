package org.fxone.ui.rt.components.header;

import java.util.Enumeration;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.model.nav.Navigateable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.Separator;
import org.fxone.ui.rt.nav.CommandLink;

@Dependent
@Named("header-links")
@Default
public class HeaderLinks extends HBox {

	@Inject
	public HeaderLinks(NavigationManager man) {
		setId("header-linkbar");
		setAlignment(Pos.BASELINE_RIGHT);
		NavigateableArea area = man.getRootNavigation("header");
		Enumeration<Navigateable> items = area.getItems();
		while (items.hasMoreElements()) {
			Navigateable item = (Navigateable) items.nextElement();
			if (item instanceof NavigateableArea) {
				continue;
			} else if (item instanceof NavigateableAction) {
				getChildren().add(new CommandLink((NavigateableAction) item));
			} else if (item instanceof Separator) {
				getChildren().add(new javafx.scene.control.Separator());
			}
		}
	}

}
