package org.fxone.ui.model.nav;

import org.fxone.ui.model.nav.cmd.NavigateTo;



public interface NavigationHistory {

	public void forward();
	public boolean isForwardEnabled();
	public boolean isBackEnabled();
	public void back();
	public void addNotification(NavigateTo cmd, String title);
	public NavigateTo removeNavigation(int pos);
	public void clearHistory();
	public int getSize();
	public int getIndex();
	public String getNotificationTitle(int pos);
	public NavigateTo getNotification(int pos);
	
}
