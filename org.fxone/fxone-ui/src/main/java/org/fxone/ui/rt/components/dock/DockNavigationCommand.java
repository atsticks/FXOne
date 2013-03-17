package org.fxone.ui.rt.components.dock;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import org.homemotion.ui.model.nav.NavigationNode;
//import org.homemotion.ui.model.nav.cmd.Navigation;
//import org.homemotion.ui.model.nav.cmd.NavigationEvent;
//
//public class DockNavigationCommand extends Button {
//
//	public DockNavigationCommand(final NavigationAreaImpl cmd) {
//		setText(cmd.getName());
//		setMinSize(140, 140);
//		setPrefSize(140, 140);
//		setMaxSize(140, 140);
//		setContentDisplay(ContentDisplay.TOP);
//		// getStyleClass().clear();
//		getStyleClass().add("tile");
//		setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				NavigateableArea.navigateTo(cmd.getUIId());
//			}
//		});
//		// setGraphic(createResource(cmd.getIcon64()));
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
