package org.fxone.ui.rt.components.perspectives;

import java.util.Enumeration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.nav.Navigateable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.Separator;

public final class ToolbarConfigurator {

	private ToolbarConfigurator() {
	}

	public static void configure(ToolBar toolbar) {
		configure(toolbar, "toolbar/default");
	}

	public static void configure(ToolBar toolbar, String path) {
		configure(toolbar, Container.getInstance(NavigationManager.class)
				.getRootNavigation().getChildArea(path));
	}

	public static void configure(ToolBar toolbar, String tree, String path) {
		configure(toolbar, Container.getInstance(NavigationManager.class)
				.getRootNavigation(tree).getChildArea(path));
	}

	public static void configure(ToolBar toolbar, NavigateableArea area) {
		Enumeration<Navigateable> en = area.getItems();
		while (en.hasMoreElements()) {
			Navigateable nav = (Navigateable) en.nextElement();
			if (nav instanceof Separator) {
				toolbar.getItems().add(new javafx.scene.control.Separator());
			} else if (nav instanceof NavigateableArea) {
				// TODO
			} else if (nav instanceof NavigateableAction) {
				toolbar.getItems().add(
						new ToolbarButton((NavigateableAction) nav));
			}
		}
	}

	public static final class ToolbarButton extends Button {

		public ToolbarButton(NavigateableAction tool) {
			if (tool == null) {
				throw new IllegalArgumentException("tool is required.");
			}
			getStyleClass().add("ToolbarButton");
			setUserData(tool);
			setDisable(!tool.isEnabled());
			setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					getTool().run();
				}
			});
		}

		public NavigateableAction getTool() {
			return (NavigateableAction) getUserData();
		}

	}

}
