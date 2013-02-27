package org.fxone.core.events;

import java.util.Random;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;

public final class LoggingEventListener implements NotificationListener{
	
	public void notified(Notification event) {
		try{
			Thread.sleep(new Random().nextInt(200));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		java.lang.System.err.println("LoggingEventListener -> Event("+ Thread.currentThread().getId() +"): " + event);
	}
	
}

