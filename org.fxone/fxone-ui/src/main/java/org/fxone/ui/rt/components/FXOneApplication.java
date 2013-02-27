package org.fxone.ui.rt.components;

import javafx.application.Application;
import javafx.stage.Stage;

import org.fxone.core.cdi.Container;

import com.sun.javafx.tk.Toolkit;

public class FXOneApplication extends Application {

	private Widgetset widgetset;
	

	public void start(final Stage primaryStage) {
		try {
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
		Application.launch(FXOneApplication.class, args);
	}

	private Widgetset loadWidgetset() {
		return new DarkSkin(); // TODO Make this coonfigurable
	}
	
}
