package org.fxone.ui.rt;

import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.fxone.ui.rt.components.splash.SplashScreen;

import com.sun.javafx.tk.Toolkit; 

public class DarkSkin implements Widgetset {

	private Stage primaryStage;

	private Group primaryGroup = new Group();

	private SplashScreen splashScreen;
	
	private Stage splashStage;

	@Override
	public Stage init(Stage stage) {
		// progressBar.setProgress(4 / STEPS);
		this.primaryStage = stage;
		splashStage = new Stage();
		splashScreen = new SplashScreen();
//		startScene.setRoot(splashScreen);
//		splashStage.setScene(startScene);
		splashStage.centerOnScreen();
		splashStage.initStyle(StageStyle.UNDECORATED);
		return splashStage;
	}

	

	public void updateProgress(final String msg, Integer progress) {
		this.splashScreen.updateProgress(msg, progress);
	}

	@Override
	public void startApplication() {
		Toolkit.getToolkit().defer(new Runnable() {
			@Override
			public void run() {
				try {
					updateProgress("Loading application container...", 40);
//					ApplicationContainer applicationContainer = new ApplicationContainerImpl();
//					ApplicationContainer result = ContextManager.getInstance(
//							ApplicationContainer.class, false);
					updateProgress("Initializing Maina UI...", 50);
//					MainScene applicationScene = new MainScene(primaryGroup,
//							applicationContainer); // (DialogRootPane) dlogMan, 
//					updateProgress("Initializing scene title area...", 60);
//					ViewTitle viewTitle = new ViewInfoAreaAdapter(
//							applicationScene.getNode());
					updateProgress("Application started.", 100);
					// Showing up UI
					Thread.sleep(500L);
					splashScreen.setFinished();
					splashStage.hide();
					splashStage.close();
//					primaryStage.setScene(applicationScene);
					primaryStage.hide();
					primaryStage.sizeToScene();
					primaryStage.initStyle(StageStyle.DECORATED);
					primaryStage.show();
				} catch (Exception e) {
					System.err.println("Failed to switch UI:");
					e.printStackTrace();
					System.exit(-1);
				}
			}
		});
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public Group getPrimaryGroup() {
		return primaryGroup;
	}

}
