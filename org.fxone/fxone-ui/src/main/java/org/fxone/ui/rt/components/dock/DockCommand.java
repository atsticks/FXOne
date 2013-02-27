package org.fxone.ui.rt.components.dock;

//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import org.homemotion.events.NotificationService;
//import org.homemotion.ui.model.nav.NavigationNode;
//
//public class DockCommand extends Button {
//
//	public DockCommand(final NavigationAreaImpl cmd) {
//		setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				cmd.run();
//			}
//		});
//		setContentDisplay(ContentDisplay.TOP);
//		// setGraphic(createResource(cmd.getIcon64()));
//		getStyleClass().add("tile");
//		setMinWidth(140);
//		setMinHeight(140);
//		setMaxWidth(140);
//		setMaxHeight(140);
//		setPrefWidth(140);
//		setPrefHeight(140);
//		setText(cmd.getName());
//		setUserData(cmd);
//	}
//
//	private Node createResource(String icon) {
//		if (icon == null) {
//			return null;
//		}
//		return new ImageView(new Image(getClass().getResourceAsStream(icon)));
//	}
//}
