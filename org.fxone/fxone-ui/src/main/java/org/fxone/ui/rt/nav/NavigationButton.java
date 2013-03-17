package org.fxone.ui.rt.nav;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import org.apache.log4j.Logger;
import org.fxone.core.cdi.Container;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationEvent;
import org.fxone.ui.model.nav.NavigationHistory;
import org.fxone.ui.model.nav.cmd.Navigation;
import org.fxone.ui.model.res.ResourceProvider;
import org.fxone.ui.model.workbench.cmd.WorkbenchCommands;

public class NavigationButton extends MenuButton {

	public NavigationButton(final NavigateableArea navNode) {
		final ResourceProvider prov = Container
				.getInstance(ResourceProvider.class);
		setText(prov.getName(navNode.getPath(), Locale.ENGLISH)); // TODO i18n
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				WorkbenchCommands.setSceneInfo(prov.getDescription(
						navNode.getPath(), Locale.ENGLISH)); // TODO i18n
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				WorkbenchCommands.setSceneInfo(null);
			}
		});
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Navigation.navigateTo(navNode.getPath());
				registerBackNavigation(navNode);
			}
		});
		setGraphic(createResource(prov.getIcon32("ui", navNode.getPath(),
				Locale.ENGLISH)));
		setUserData(navNode);
	}

	protected void registerBackNavigation(final NavigateableArea navNode) {
		final ResourceProvider prov = Container
				.getInstance(ResourceProvider.class);
		NavigationHistory hist = Container.getInstance(NavigationHistory.class);
		if (navNode.getParent() != null) {
			try {
				if (navNode.getParent() == null) {
					hist.addNotification(Navigation.createGoHomeNotif()
							.getAdapter(NavigationEvent.class), prov.getName(
							"home", Locale.ENGLISH));
				} else {
					hist.addNotification(
							Navigation.createNavigateToNotif(navNode.getPath())
									.getAdapter(NavigationEvent.class), prov
									.getName(navNode.getPath(), Locale.ENGLISH));
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(
						"Failed to register navigation.", e);
			}
		}
	}
}
