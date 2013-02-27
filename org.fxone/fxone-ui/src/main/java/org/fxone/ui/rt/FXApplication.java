package org.fxone.ui.rt;

import javafx.application.Application;
import javafx.stage.Stage;

import org.homemotion.di.Container;
import org.homemotion.ui.model.session.ContextManager;

import skins.dark.DarkSkin;

import com.sun.javafx.tk.Toolkit;

public class FXApplication extends Application {

	private Widgetset widgetset;
	

	public void start(final Stage primaryStage) {
		try {
			ContextManager.setInstance(Stage.class, primaryStage);
			this.widgetset = loadWidgetset();
			Stage splashStage = widgetset.init(primaryStage);
			splashStage.show();
			Thread startup = new Thread(){
				public void run(){
					widgetset.updateProgress("Loading Container...", 10);
					Container.start();
					widgetset.updateProgress("Registering shutdown hook...", 20);
					Toolkit.getToolkit().addShutdownHook(new Runnable() {
						@Override
						public void run() {
							Container.stop();
						}
					});
					widgetset.updateProgress("Starting UI...",22);
					widgetset.startApplication();
				}
			};
			startup.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		Application.launch(FXApplication.class, args);
	}

	private Widgetset loadWidgetset() {
		return new DarkSkin(); // TODO Make this coonfigurable
	}
	
}
