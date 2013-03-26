package org.fxone.ui.rt.misc;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;

@Singleton
public final class CommandLogger implements NotificationListener{

	private static final Logger LOGGER = Logger.getLogger(CommandLogger.class);
	
	public CommandLogger() {
		NotificationService.get().addListener(this);
	}
	
	protected void finalize() throws Throwable {
		NotificationService.get().removeListener(this);
		super.finalize();
	};
	
	public void notified(AbstractNotification event) {
		LOGGER.info("Notif - " + event);
	}
	
	@Override
	public String toString(){
		return "CommandLogger";
	}

}
