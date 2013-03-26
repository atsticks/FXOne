package org.fxone.ui.model.nav.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;
import org.fxone.core.events.Severity;
import org.fxone.ui.model.nav.NavigateableArea;

public final class PrintNavigationHistory extends AbstractNotification<Void> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 793323727444811623L;


	public static final NotificationType NOTIFTYPE = new NotificationType.Builder(
			"UI", "Navigate:clearHistory", "Clear the navigation history.",
			Severity.DEBUG).define(PrintNavigationHistory.class).buildAndRegister();

	public PrintNavigationHistory() {
		super(DefaultGroups.NAVIGATION);
	}
}
