package org.fxone.ui.rt;

import javafx.stage.Stage;

public interface ApplicationLoader {

	public void init(Stage stage);

	public SplashScreen getSplashScreen();

	public void startApplication();

}
