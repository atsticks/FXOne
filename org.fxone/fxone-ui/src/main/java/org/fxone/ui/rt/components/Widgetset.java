package org.fxone.ui.rt.components;

import javafx.stage.Stage;


public interface Widgetset {

	public Stage init(Stage stage);
	
	public void startApplication();

	public void updateProgress(String msg, Integer progress);

}
