package org.fxone.ui.rt.components.view;

import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;

public interface ViewTitle {

	public static final String LOGOID = "application-logo";

	public Image getLogo();
	
	public void setLogo(Image resource);

	public void setAreaTitle(String title);

	public String getAreaTitle();

	public void setSubTitle(String title);

	public String getSubTitle();
	
	public String getDescription();
	
	public void setDescription(String description);

	public Hyperlink getUserLink();
	
}
