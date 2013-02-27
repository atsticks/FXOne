package org.fxone.ui.model.nav;

import org.fxone.ui.model.nav.impl.NavigationAreaImpl;


public interface Navigateable {

	public void navigateTo(String path);

	public NavigationAreaImpl getCurrentNavigation();

	public void navigateTo(NavigationAreaImpl nav);

	public void home();

	public void up();

}
