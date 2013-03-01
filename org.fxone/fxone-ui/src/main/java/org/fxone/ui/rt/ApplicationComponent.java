package org.fxone.ui.rt;

import javafx.stage.Stage;

public interface ApplicationComponent {

	public void init(Stage stage);

	public SplashScreen getSplashScreen();

	public void startApplication();

}
