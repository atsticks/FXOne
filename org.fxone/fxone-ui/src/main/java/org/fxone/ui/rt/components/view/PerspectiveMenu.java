package org.fxone.ui.rt.components.view;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.res.ResourceProvider;
import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;

@Dependent
@Named("perspective-menu")
@Default
public class PerspectiveMenu extends MenuButton {

	private ResourceProvider resourceProvider;

	@Inject
	public PerspectiveMenu(Instance<Perspective> perspectives,
			ResourceProvider resourceProvider) {
		setId("perspectiveMenu");
		super.setText("Perspectives");
		this.resourceProvider = resourceProvider;
		for (Perspective perspective : perspectives) {
			MenuItem menuItem = new MenuItem(getName(perspective));
			menuItem.setUserData(perspective);
			getItems().add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evt) {
					Perspective perspective = (Perspective) ((MenuItem) evt
							.getSource()).getUserData();
					Workbench workbench = Container
							.getInstance(Workbench.class);
					workbench.setCurrentPerspective(getId(perspective));
				}
			});
		}
	}

	private String getName(Perspective perspective) {
		Named named = perspective.getClass().getAnnotation(Named.class);
		if (named != null) {
			return resourceProvider.getName(named.value(), Locale.ENGLISH); // TODO
																			// i18n
		}
		return resourceProvider.getName(perspective.getClass(), Locale.ENGLISH); // TODO
																					// i18n
	}

	private String getId(Perspective perspective) {
		Named named = perspective.getClass().getAnnotation(Named.class);
		if (named != null) {
			return named.value();
		}
		return perspective.getClass().getSimpleName();
	}
}
