package org.fxone.ui.rt.components;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.ApplicationLoader;
import org.fxone.ui.rt.SplashScreen;
import org.fxone.ui.rt.components.splash.SimpleSplashScreen;

import com.sun.javafx.tk.Toolkit;

public class DefaultApplicationLoader implements ApplicationLoader {

	private Stage primaryStage;

	private Group primaryGroup = new Group();

	private SplashScreen splashScreen;
	
	@Override
	public void init(Stage stage) {
		this.primaryStage = stage;
		splashScreen = createSplashScreen();
	}
	
	private SplashScreen createSplashScreen() {
		return new SimpleSplashScreen();
	}

	public SplashScreen getSplashScreen(){
		return this.splashScreen;
	}
	

	@Override
	public void startApplication() {
		Toolkit.getToolkit().defer(new Runnable() {
			@Override
			public void run() {
				try {
					if(splashScreen!=null){
						splashScreen.updateProgress("Loading application container...", 40);
						Thread.sleep(500L);
					}
//					ApplicationContainer applicationContainer = new ApplicationContainerImpl();
//					ApplicationContainer result = Context.getInstance(
//							ApplicationContainer.class, false);
					if(splashScreen!=null){
						splashScreen.updateProgress("Initializing Maina UI...", 70);
						Thread.sleep(500L);
					}
//					MainScene applicationScene = new MainScene(primaryGroup,
//							applicationContainer); // (DialogRootPane) dlogMan, 
//					updateProgress("Initializing scene title area...", 60);
//					ViewTitle viewTitle = new ViewInfoAreaAdapter(
//							applicationScene.getNode());
					if(splashScreen!=null){
						splashScreen.updateProgress("Application started.", 95);
						Thread.sleep(500L);
					}
					// Showing up UI
					Thread.sleep(500L);
					if(splashScreen!=null){
						splashScreen.setFinished();
						Thread.sleep(500L);
					}
					Workbench workbench = Container.getInstance(Workbench.class);
					primaryStage.setScene(((Node)workbench).getScene());
					primaryStage.hide();
					primaryStage.sizeToScene();
					primaryStage.initStyle(StageStyle.DECORATED);
					primaryStage.setHeight(600d);
					primaryStage.setWidth(800d);
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
