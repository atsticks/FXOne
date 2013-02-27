package org.fxone.ui.model;

import java.util.Locale;

public interface Presentable{

	public String getName(Locale locale);
	
	public String getDescription(Locale locale);
	
	public String getImage(Locale locale);
	
	public String getIcon16(Locale locale);
	
	public String getIcon32(Locale locale);
	
	public String getIcon48(Locale locale);
	
	public String getIcon64(Locale locale);
	
	public String getIcon128(Locale locale);
	
	public String getTooltip(Locale locale);

}
