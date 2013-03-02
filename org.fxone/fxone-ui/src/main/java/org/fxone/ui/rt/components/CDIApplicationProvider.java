package org.fxone.ui.rt.components;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.fxone.core.Context;
import org.fxone.ui.rt.ApplicationLoader;
import org.fxone.ui.rt.SplashScreen;

@Singleton
public class CDIApplicationProvider {

	@Produces
	public Stage getStage() {
		return Context.get().getSingleton(Stage.class);
	}

	@Produces
	public Application getApplication() {
		return Context.get().getSingleton(Application.class);
	}

	@Produces
	public SplashScreen getSplashScreen() {
		return Context.get().getSingleton(SplashScreen.class);
	}

	@Produces
	public ApplicationLoader getApplicationComponent() {
		return Context.get().getSingleton(ApplicationLoader.class);
	}
}
