package org.fxone.ui.model.view.impl;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.cdi.Container;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.cmd.ViewCommand;

@Singleton
public class ViewFactoryImpl implements NotificationListener {

	private static final Logger LOGGER = Logger
			.getLogger(ViewFactoryImpl.class);

	@Override
	public void notified(Notification event) {
		if (ViewCommand.NOTIFTYPE_CREATE_VIEW.isMatching(event)) {
			if (event.isCompleted()) {
				LOGGER.debug("Ignoring completed event: " + event);
			}
			ViewCommand cmd = (ViewCommand) event;
			String viewID = cmd.getViewContext().getId();
			try {
				View view = Container.getNamedInstance(View.class, viewID);
				cmd.setResult(view);
			} catch (Exception e) {
				LOGGER.error("Failed to resolve view with id: " + viewID, e);
			}
		}
	}
}
