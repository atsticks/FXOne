package org.fxone.ui.rt.nav;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.msg.ResourceProvider;
import org.fxone.ui.model.nav.UICommand;
import org.fxone.ui.model.workbench.WorkbenchCommands;

public class CommandButton extends MenuButton {

	public CommandButton(final UICommand navNode) {
		final ResourceProvider prov = Container.getInstance(ResourceProvider.class);
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				WorkbenchCommands.setSceneInfo(prov.getDescription(navNode.getPath(), Locale.ENGLISH)); // TODO i18n
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				WorkbenchCommands.setSceneInfo(null);
			}
		});
		setText(prov.getName(navNode.getPath(), Locale.ENGLISH)); // TODO i18n
		setGraphic(createResource(prov.getIcon32("ui",navNode.getPath(), Locale.ENGLISH))); // TODO i18n
		setUserData(navNode);
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				navNode.run();
			}
		});
	}

}
