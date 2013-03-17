package org.fxone.ui.model.nav;



public interface Navigateable {

	public void navigateTo(String path);
	
	public void navigateTo(String tree, String path);

	public NavigateableArea getCurrentNavigation();
	
	public NavigateableArea getCurrentNavigation(String tree);

	public void navigateTo(NavigateableArea nav);

	public void home();
	
	public void home(String tree);

	public void up();
	
	public void up(String tree);

}
