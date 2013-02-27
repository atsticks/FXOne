package org.fxone.core.events;


public interface NotificationConsumer {

	public Notification parseNotification(Object owner, String notification);

}
