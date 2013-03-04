package org.fxone.ui.model.nav;



public interface NavigationHistory {

	public void forward();
	public boolean isForwardEnabled();
	public boolean isBackEnabled();
	public void back();
	public void addNotification(NavigationEvent cmd, String title);
	public NavigationEvent removeNavigation(int pos);
	public void clearHistory();
	public int getSize();
	public int getIndex();
	public String getNotificationTitle(int pos);
	public NavigationEvent getNotification(int pos);
	
}
