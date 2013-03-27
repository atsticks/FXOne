package org.fxone.core.events;

import org.fxone.core.cmd.Core;
import org.junit.Test;

public class EventBusTest {

	
	@Test
	public void testSendEvent(){
		int count = 0;
		for(int i=0;i<10;i++){
			Core.Messages.send(this, Severity.INFO, "Test " + (count++));
		}
		System.err.println("*** No logs so far...");
		NotificationService.get().addListener(new LoggingEventListener());
		System.err.println("***Logs are to be expected...");
		for(int i=0;i<100;i++){
			Core.Messages.send(this, Severity.INFO, "Test " + (count++));
		}
	}
	
}
