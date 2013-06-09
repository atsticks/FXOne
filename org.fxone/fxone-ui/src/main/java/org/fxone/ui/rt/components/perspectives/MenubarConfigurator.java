package org.fxone.ui.rt.components.perspectives;

import java.util.Enumeration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuItemBuilder;
import javafx.scene.control.SeparatorMenuItem;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.nav.Navigateable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.Separator;

public final class MenubarConfigurator {

	private MenubarConfigurator() {
	}
	
	public static void configure(MenuBar menubar) {
		configure(menubar, "menubar/default");
	}

	public static void configure(MenuBar menubar, String path) {
		configure(menubar, Container.getInstance(NavigationManager.class)
				.getRootNavigation().getChildArea(path));
	}

	public static void configure(MenuBar menubar, String tree, String path) {
		configure(menubar, Container.getInstance(NavigationManager.class)
				.getRootNavigation(tree).getChildArea(path));
	}

	public static void configure(MenuBar menubar, NavigateableArea area) {
		Enumeration<Navigateable> items = area.getItems();
		while (items.hasMoreElements()) {
			Navigateable nav = (Navigateable) items.nextElement();
			if(nav instanceof NavigateableArea){
				Menu menu = buildMenu((NavigateableArea)nav);
				menubar.getMenus().add(menu);
			}
		}
	}

	private static Menu buildMenu(NavigateableArea area) {
		Menu menu = new Menu(area.getIdentifier());
		Enumeration<Navigateable> items = area.getItems();
		while (items.hasMoreElements()) {
			Navigateable nav = (Navigateable) items.nextElement();
			if(nav instanceof NavigateableArea){
				Menu childMenu = buildMenu((NavigateableArea)nav);
				menu.getItems().add(childMenu);
			}
			else if(nav instanceof NavigateableAction){
				MenuItem childItem = createMenuItem((NavigateableAction)nav);
				menu.getItems().add(childItem);
			}
			else if(nav instanceof Separator){
				menu.getItems().add(new SeparatorMenuItem());
			}
		}
		return menu;
	}

	private static MenuItem createMenuItem(final NavigateableAction child) {
		MenuItemBuilder builder = MenuItemBuilder.create();
		builder.id(child.getIdentifier());
		builder.text(child.getIdentifier()); // TODO i18n
		builder.userData(child);
		if(child.isEnabled()){
			builder.onAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent arg0) {
					child.run();
				}});
		}
		return builder.build();
	}

	

}
