package org.fxone.ui.model.workbench;

import java.util.Enumeration;


public interface Workbench {

	public void setWindowTitle(String title);

	public String getWindowTitle();

	public void setFullScreen(boolean set);

	public void centerOnScreen();

	public Enumeration<Perspective> getPerspectives();

	public Perspective<?> getPerspective(String key);

	public Perspective<?> getCurrentPerspective();

	public boolean setCurrentPerspective(String perspectiveID);

	void setDefaultPerspective();

}
