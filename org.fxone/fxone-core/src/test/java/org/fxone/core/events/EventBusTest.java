package org.fxone.core.events;

import org.fxone.core.events.NotificationService;
import org.fxone.core.events.Severity;
import org.fxone.core.events.notif.Messages;
import org.junit.Test;

public class EventBusTest {

	
	@Test
	public void testSendEvent(){
		int count = 0;
		for(int i=0;i<10;i++){
			Messages.send(this, Severity.INFO, "Test " + (count++));
		}
		System.err.println("*** No logs so far...");
		NotificationService.get().addListener(new LoggingEventListener());
		System.err.println("***Logs are to be expected...");
		for(int i=0;i<100;i++){
			Messages.send(this, Severity.INFO, "Test " + (count++));
		}
	}
	
}
