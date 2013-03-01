package org.fxone.ui.rt;

import javafx.stage.Stage;

public interface SplashScreen {

	public void init(Stage stage);
	
	public void updateProgress(String msg, Integer progress);

	public void setFinished();
	
	public int getMaxProgressValue();
	
	public void setMaxProgress(int maxProgressValue);
	
	public void show();
	
	public void hide();

}