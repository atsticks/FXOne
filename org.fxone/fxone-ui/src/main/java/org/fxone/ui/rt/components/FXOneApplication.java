package org.fxone.ui.rt.components;

import java.lang.Thread.UncaughtExceptionHandler;

import javafx.application.Application;
import javafx.stage.Stage;

import org.apache.log4j.Logger;
import org.fxone.core.cdi.Container;
import org.fxone.ui.rt.ApplicationComponent;
import org.fxone.ui.rt.SplashScreen;

import com.sun.javafx.tk.Toolkit;

public class FXOneApplication extends Application {

	private ApplicationComponent application;
	private static final Logger LOGGER = Logger
			.getLogger(FXOneApplication.class);

	public void start(final Stage primaryStage) {
		try {
			this.application = loadApplication();
			this.application.init(primaryStage);
			final SplashScreen splash = application.getSplashScreen();
			if (splash != null) {
				splash.show();
			}
			Thread startup = new Thread() {
				public void run() {
					if (splash != null) {
						splash.updateProgress("Loading Container...", 10);
					}
					Container.start();
					if (splash != null) {
						splash.updateProgress("Registering shutdown hook...",
								20);
					}
					Toolkit.getToolkit().addShutdownHook(new Runnable() {
						@Override
						public void run() {
							Container.stop();
						}
					});
					if (splash != null) {
						splash.updateProgress("Starting UI...", 22);
					}
					application.startApplication();
					try {
						sleep(400L);
					} catch (InterruptedException e) {
						LOGGER.warn("Startup interrupted.", e);
					}
					if (splash != null) {
						splash.hide();
					}
				}
			};
			startup.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread t, Throwable e) {
					LOGGER.fatal("Failed to start application.", e);
					System.exit(-1);
				}
			});
			startup.start();
			
		} catch (Exception e) {
			LOGGER.fatal("Failed to start application.", e);
			System.exit(-1);
		}
	}

	public static void main(String[] args) {
		Application.launch(FXOneApplication.class, args);
	}

	private ApplicationComponent loadApplication() {
		return new DefaultApplicationComponent(); // TODO Make this configurable
	}

}
