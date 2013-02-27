package org.fxone.ui.rt.components.dialog;
//
//import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//public abstract class AbstractDialog extends Stage {
//	
//	public AbstractDialog(String title, Stage owner, String iconFile) {
//		setTitle(title);
//		initStyle(StageStyle.UTILITY);
//		initModality(Modality.APPLICATION_MODAL);
//		initOwner(owner);
//		setResizable(false);
//		FXOptionPane.icon.setImage(new Image(getClass().getResourceAsStream(iconFile)));
//		setScene(createScene());
//	}
//
//	protected abstract Scene createScene();
//
//	public void showDialog() {
//		sizeToScene();
//		centerOnScreen();
//		showAndWait();
//	}
//}