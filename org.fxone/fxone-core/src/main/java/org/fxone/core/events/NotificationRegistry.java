package org.fxone.core.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.types.AnnotationManager;


public final class NotificationRegistry {

	private final static Logger LOGGER = Logger
			.getLogger(NotificationRegistry.class);

	private List<NotificationDefinition> eventDefinitions = Collections
			.synchronizedList(new ArrayList<NotificationDefinition>(200));

	private static NotificationRegistry INSTANCE = new NotificationRegistry();

	private NotificationRegistry() {
		// Load notification providers
		Set<String> classNames = AnnotationManager
				.getAnnotatedClasses(NotificationExtension.class.getName());
		for (String className : classNames) {
			try {
				Class<?> clazz = Class.forName(className);
				if (NotificationProvider.class.isAssignableFrom(clazz)) {
					NotificationProvider np = (NotificationProvider) clazz
							.newInstance();
					for (NotificationDefinition def : np
							.getNotificationDefinitions()) {
						registerEventDefinition(def);
					}
				}
			} catch (Exception e) {
				Logger.getLogger(getClass()).error(
						"Failed to load/access NotificationExtension.", e);
			}
		}
	}

	public static NotificationRegistry get() {
		return INSTANCE;
	}

	public Collection<NotificationDefinition> getEventDefinitions() {
		synchronized (eventDefinitions) {
			return new ArrayList<NotificationDefinition>(eventDefinitions);
		}
	}

	public NotificationDefinition getEventDefinition(String group, String name) {
		synchronized (eventDefinitions) {
			for (NotificationDefinition evtDef : eventDefinitions) {
				if (group != null && !evtDef.getGroup().matches(group)) {
					continue;
				}
				if (name != null && !evtDef.getName().matches(name)) {
					continue;
				}
				return evtDef;
			}
		}
		return null;
	}

	public void registerEventDefinition(NotificationDefinition definition) {
		definition.setReadOnly();
		synchronized (eventDefinitions) {
			if (!eventDefinitions.contains(definition)) {
				LOGGER.debug("Registering event definition: " + definition);
				eventDefinitions.add(definition);
			}
		}
	}

	public void unregisterEventDefinition(NotificationDefinition definition) {
		synchronized (eventDefinitions) {
			eventDefinitions.remove(definition);
		}
	}

}