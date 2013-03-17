package org.fxone.ui.rt.nav;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.nav.cmd.Navigation;
import org.fxone.ui.model.res.ResourceProvider;

@Dependent
@Default
@Named("view-navigation")
public class ViewNavigationToolbar extends HBox {

	// private Separator separator = new Separator(Orientation.VERTICAL);
	private Button homeButton = new Button();
	private Button backButton = new Button();
	private Button forwardButton = new Button();
	private Button upButton = new Button();

	public ViewNavigationToolbar() {
		setId("breadcrumbs");
		setOpacity(70);
		setMinHeight(26);
		setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);
		ResourceProvider rp = Container.getInstance(ResourceProvider.class);
		String imageResource = rp.getIcon32("nav-view", "home", Locale.ENGLISH);
		if (imageResource != null) {
			homeButton.setGraphic(new ImageView(new Image(getClass()
					.getResourceAsStream(imageResource))));
		}
		homeButton.getStyleClass().setAll("button", "first-button");
		homeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				home();
			}
		});
		homeButton.setMaxHeight(Double.MAX_VALUE);
		imageResource = rp.getIcon32("nav-view", "back", Locale.ENGLISH);
		if (imageResource != null) {
			backButton.setGraphic(new ImageView(new Image(getClass()
					.getResourceAsStream(imageResource))));
		}
		backButton.getStyleClass().setAll("button", "middle-button");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				back();
			}
		});
		backButton.setMaxHeight(Double.MAX_VALUE);
		imageResource = rp.getIcon32("nav-view", "next", Locale.ENGLISH);
		if (imageResource != null) {
			forwardButton.setGraphic(new ImageView(new Image(getClass()
					.getResourceAsStream(imageResource))));
		}
		forwardButton.getStyleClass().setAll("button", "middle-button");
		forwardButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				forward();
			}
		});
		forwardButton.setMaxHeight(Double.MAX_VALUE);
		imageResource = rp.getIcon32("nav-view", "up", Locale.ENGLISH);
		if (imageResource != null) {
			upButton.setGraphic(new ImageView(new Image(getClass()
					.getResourceAsStream(imageResource))));
		}
		upButton.getStyleClass().setAll("button", "middle-button");
		upButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				up();
			}
		});
		upButton.setMaxHeight(Double.MAX_VALUE);
		getChildren().addAll(homeButton, backButton, forwardButton, upButton);
	}

	public void addButtons(Button... buttons) {
		getChildren().clear();
		getChildren().addAll(homeButton, backButton, forwardButton, upButton);
		if (buttons != null) {
			for (int i = 0; i < buttons.length; i++) {
				if (i == 0) {
					buttons[i].getStyleClass().setAll("button", "first-button");
				} else if (i != (buttons.length - 1)) {
					buttons[i].getStyleClass()
							.setAll("button", "middle-button");
				} else {
					buttons[i].getStyleClass().setAll("button", "last-button");
				}
			}
			getChildren().addAll(buttons);
		}
	}

	public void removeButtons(Button... buttons) {
		if (buttons != null) {
			getChildren().removeAll(buttons);
		}
	}

	public void home() {
		Navigation.goHome();
	}

	public void back() {
		Navigation.goBack();
	}

	public void forward() {
		Navigation.goNext();
	}

	public void up() {
		Navigation.goUp();
	}

	public Button[] getButtons() {
		return this.getChildren().toArray(new Button[getChildren().size()]);
	}

}
