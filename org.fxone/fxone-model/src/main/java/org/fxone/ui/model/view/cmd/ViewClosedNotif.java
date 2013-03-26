package org.fxone.ui.model.view.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;

public final class ViewClosedNotif extends AbstractNotification<Void> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 6541926919693382107L;

	private View view;
	private ViewContainer viewContainer;

	public static final NotificationType NOTIFTYPE = new NotificationType.Builder(
			"UI", "View:closed", "A view was closed.", Severity.DEBUG)
			.define(ViewClosedNotif.class).addResult(Void.class)
			.buildAndRegister();

	public ViewClosedNotif(View view, ViewContainer viewContainer) {
		super(DefaultGroups.UI);
		setView(view);
		setViewContainer(viewContainer);
	}
	
	public View getView() {
		return this.view;
	}

	public void setView(View view) {
		checkReadOnly();
		if (view == null) {
			throw new IllegalArgumentException("view is required.");
		}
		this.view = view;
	}

	public ViewContainer getViewContainer() {
		return this.viewContainer;
	}

	public void setViewContainer(ViewContainer viewContainer) {
		checkReadOnly();
		if (viewContainer == null) {
			throw new IllegalArgumentException("viewContainer is required.");
		}
		this.viewContainer = viewContainer;
	}

}
