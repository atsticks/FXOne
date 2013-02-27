package org.fxone.core.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationRegistry;
import org.junit.Test;

public class EventRegistryTest {

	
	@Test
	public void testgetEventDefinitions(){
		Collection<NotificationDefinition> events = NotificationRegistry.get().getEventDefinitions();
		assertNotNull(events);
		assertFalse(events.isEmpty());
	}
	
	@Test
	public void testgetEventDefinition(){
		Collection<NotificationDefinition> events = NotificationRegistry.get().getEventDefinitions();
		assertNotNull(events);
		assertFalse(events.isEmpty());
		NotificationDefinition def = events.iterator().next();
		NotificationDefinition def2 = NotificationRegistry.get().getEventDefinition(def.getGroup(), def.getName());
		assertEquals(def, def2);
		assertTrue(def==def2);
	}
	
}
