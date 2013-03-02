package org.fxone.ui.rt.components.view;

import java.util.Locale;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.core.cdi.Container;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.msg.ResourceProvider;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.cmd.ViewCommand;

@Dependent
@Named("view-menu")
@Default
public class ViewMenu extends MenuButton implements NotificationListener {

	private Menu viewActionsMenu = new Menu("Actions");
	private Menu viewSelectionMenu = new Menu("Change View");
	private MenuItem closeItem = new MenuItem("Close");
	private MenuItem closeAllItem = new MenuItem("Close All");
	private MenuItem firstItem = new MenuItem("First");
	private MenuItem previousItem = new MenuItem("Previous");
	private MenuItem nextItem = new MenuItem("Next");
	private MenuItem lastItem = new MenuItem("Last");

	@Inject
	private ResourceProvider resourceProvider;
	
	public ViewMenu() {
		super.setText("View");
		getItems().addAll(closeItem, closeAllItem, new SeparatorMenuItem(),
				firstItem, previousItem, nextItem, lastItem, viewActionsMenu,
				viewSelectionMenu);
		NotificationService.get().addListener(this);
	}

	protected void finalize() throws Throwable {
		NotificationService.get().removeListener(this);
	};

	private void initViewSelectionMenu(View currentView,
			View[] currentViews) {
		this.viewSelectionMenu.getItems().clear();
		if (currentViews.length==0) {
			this.viewSelectionMenu.setDisable(true);
		} else {
			this.viewSelectionMenu.setDisable(false);
			// Add views to be selected...
			Locale userLocale = Locale.ENGLISH; // TODO i18n
			for (View v : currentViews) {
				RadioMenuItem mi = new RadioMenuItem(resourceProvider.getName(Container.getName(v), userLocale));
				mi.setUserData(v);
				if (v == currentView) {
					mi.setSelected(true);
				}
				this.viewSelectionMenu.getItems().add(mi);
			}
		}
	}

	private void initViewActionsMenu(View currentView) {
		this.viewActionsMenu.getItems().clear();
		if (currentView != null) {
			this.viewActionsMenu.setDisable(true);
			// TODO add actions for this view...
		} else {
			this.viewActionsMenu.setDisable(false);
		}
	}

	private void viewOpenend() {
		this.closeAllItem.setDisable(false);
		this.closeItem.setDisable(false);
		this.firstItem.setDisable(false);
		this.previousItem.setDisable(true);
		this.nextItem.setDisable(true);
		this.lastItem.setDisable(true);
	}

	private void viewClosed() {
		this.closeAllItem.setDisable(true);
		this.closeItem.setDisable(true);
		this.firstItem.setDisable(true);
		this.previousItem.setDisable(true);
		this.nextItem.setDisable(true);
		this.lastItem.setDisable(true);
		this.viewActionsMenu.setDisable(true);
	}

	public void notified(Notification event) {
		ViewCommand ve = event.getAdapter(ViewCommand.class);
		if (ve.getName().equals(ViewCommand.NOTIFTYPE_VIEW_CLOSED.getName())) {
			View[] currentViews = ve.getViewContainer().getViewsVisible();
			if (currentViews.length==0) {
				viewClosed();
				this.viewSelectionMenu.setDisable(true);
				this.viewActionsMenu.setDisable(true);
			} else {
				viewOpenend();
			}
		} else if (ve.getName().equals(ViewCommand.NOTIFTYPE_VIEW_OPENED.getName())) {
			View viewOpened = ve.getView();
			View[] currentViews = ve.getViewContainer().getViewsVisible();
			if (currentViews.length==0) {
				viewClosed();
			} else {
				viewOpenend();
				initViewActionsMenu(viewOpened);
				initViewSelectionMenu(viewOpened, currentViews);
			}
		}
	}
}
