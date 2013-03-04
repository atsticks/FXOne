package org.fxone.core.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Enumeration;

import org.junit.Test;

public class EventRegistryTest {

	
	@Test
	public void testgetEventDefinitions(){
		Enumeration<NotificationType> events = NotificationType.getTypes();
		assertNotNull(events);
		assertFalse(events.hasMoreElements());
	}
	
	@Test
	public void testgetEventDefinition(){
		Enumeration<NotificationType> events = NotificationType.getTypes();
		assertNotNull(events);
		assertFalse(events.hasMoreElements());
		NotificationType def = events.nextElement();
		NotificationType def2 = NotificationType.valueOf(def.getGroup(), def.getName());
		assertEquals(def, def2);
		assertTrue(def==def2);
	}
	
}
