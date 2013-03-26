package org.fxone.core.events;


public interface NotificationConsumer {

	public AbstractNotification parseNotification(Object owner, String notification);

}
