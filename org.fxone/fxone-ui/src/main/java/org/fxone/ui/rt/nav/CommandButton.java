package org.fxone.ui.rt.nav;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.Model;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.res.ResourceProvider;

public class CommandButton extends MenuButton {

	public CommandButton(final NavigateableAction action) {
		final ResourceProvider prov = Container.getInstance(ResourceProvider.class);
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Model.Workbench.setStatus(null, prov.getDescription(action.getIdentifier(), Locale.ENGLISH)); // TODO i18n
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Model.Workbench.setStatus(null, "");
			}
		});
		setText(prov.getName(action.getIdentifier(), Locale.ENGLISH)); // TODO i18n
		setGraphic(createResource(prov.getIcon32("ui",action.getIdentifier(), Locale.ENGLISH))); // TODO i18n
		setUserData(action);
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				action.run();
			}
		});
	}

}
