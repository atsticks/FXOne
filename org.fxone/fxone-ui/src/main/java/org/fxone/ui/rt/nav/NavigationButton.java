package org.fxone.ui.rt.nav;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.Model;
import org.fxone.ui.model.Model.Navigation;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.res.ResourceProvider;

public class NavigationButton extends MenuButton {

	public NavigationButton(final NavigateableArea navNode) {
		final ResourceProvider prov = Container
				.getInstance(ResourceProvider.class);
		setText(prov.getName(navNode.getIdentifier(), Locale.ENGLISH)); // TODO i18n
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Model.Workbench.setStatus(null, prov.getDescription(
						navNode.getIdentifier(), Locale.ENGLISH)); // TODO i18n
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Model.Workbench.setStatus(null, "");
			}
		});
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Navigation.navigateTo(navNode.getIdentifier());
			}
		});
		setGraphic(createResource(prov.getIcon32("ui", navNode.getIdentifier(),
				Locale.ENGLISH)));
		setUserData(navNode);
	}

	
}
