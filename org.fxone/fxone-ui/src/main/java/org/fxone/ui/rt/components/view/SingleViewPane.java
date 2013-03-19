package org.fxone.ui.rt.components.view;

import java.io.IOException;

import javafx.scene.control.Label;

import javax.inject.Inject;

import org.fxone.core.cdi.Container;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;
import org.fxone.ui.model.view.cmd.ViewCommand;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
//
//public class SingleViewPane extends AbstractFXMLComponent implements
//		NotificationListener {
//
//	private org.fxone.ui.model.view.ViewContainer viewContainer;
//	private AccordionMenu menu;
//	private Label viewTitle;
//	private boolean active;
//	private ViewMenu viewMetaMenu;
//
//	@Inject
//	public SingleViewPane() throws IOException {
//		super(SingleViewPane.class);
//		viewContainer = (org.fxone.ui.model.view.ViewContainer) lookup("#viewContainer");
//		viewTitle = (Label) lookup("#viewTitleLabel");
//		menu = (AccordionMenu) lookup("#viewMenu");
//		viewMetaMenu = (ViewMenu) lookup("#viewMetaMenu");
//		setUserData(this);
//		ContextManager.setInstance(FXViewContainer.class, viewContainer);
//		ContextManager.setInstance(ViewContainer.class, viewContainer);
//		NotificationService.get().addListener(this);
//		navigateTo(Container.getInstance(NavigationManager.class)
//				.getRootNavigation());
//	}
//
//	@Override
//	protected void finalize() throws Throwable {
//		NotificationService.get().removeListener(this);
//		super.finalize();
//	}
//
//	@Override
//	public void notified(Notification notif) {
//		if (!this.active) {
//			return;
//		}
//		if (ViewCommand.NOTIFTYPE_OPEN_VIEW.isMatching(notif)) {
//			ViewCommand evt = (ViewCommand)notif;
//			View view = evt.getView();
//			if (view == null) {
//				String viewID = evt.getViewID();
//				if (viewContainer.isViewOpened(viewID)) {
//					viewContainer.showView(viewID);
//					return;
//				}
//				view = Container.getInstance(ViewManager.class).getView(viewID);
//			}
//			if (view != null) {
//				viewContainer.openView(view);
//			}
//		}
//		else if (ViewCommand.NOTIFTYPE_CLOSE_VIEW.isMatching(notif)) {
//			ViewCommand evt = (ViewCommand)notif;
//			View view = evt.getView();
//			if (view == null) {
//				String viewID = evt.getViewID();
//				viewContainer.closeView(viewID);
//			}
//			else{
//				viewContainer.closeView(view);
//			}
//		}
//	}
//
//	@Override
//	public String getDescription() {
//		return "Default View Container (no18n).";
//	}
//
//	@Override
//	public void activated() {
//		this.active = true;
//	}
//
//	@Override
//	public void deactivated() {
//		this.active = false;
//	}
//
//	@Override
//	public void navigateTo(NavigationNode nav) {
//		if (active) {
//			nav.run();
//			viewTitle.setText(nav.getName());
//			this.menu.navigateTo(nav);
//		}
//
//	}
//
//	public void setMenuVisible(boolean value) {
//		this.menu.setVisible(value);
//	}
//
//	public boolean isMenuVisible() {
//		return this.menu.isVisible();
//	}
//
//	public void setTitle(String title) {
//		this.viewTitle.setText(title);
//	}
//
//	public String getTitle() {
//		return this.viewTitle.getText();
//	}
//
//	public ViewMenu getViewMenu() {
//		return this.viewMetaMenu;
//	}
//
//}
