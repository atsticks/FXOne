package org.fxone.core.events.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.core.events.Severity;

/**
 * @author Anatole
 */
@Singleton
@Named("EventCollector")
public final class EventCollector implements NotificationListener {

	private static final Logger LOGGER = Logger.getLogger(EventCollector.class);

	private int maxSize = 2000;

	private Severity minLevel = Severity.INFO;

	private List<AbstractNotification> events = new LinkedList<AbstractNotification>();

	public EventCollector() {
		NotificationService.get().addListener(this);
	}

	public void notified(AbstractNotification event) {
		if (event.getSeverity().compareTo(this.minLevel) < 0) {
			return;
		}
		LOGGER.debug("Event: " + event.toString());
		events.add(event);
		if (events.size() > maxSize) {
			events.remove(0);
		}
	}

	public int getMaxEventCount() {
		return maxSize;
	}

	public List<AbstractNotification> getCurrentEvents() {
		return Collections.unmodifiableList(events);
	}

	public void clear() {
		this.events.clear();
	}

	public void setMaxEventCount(int maxSize) {
		if (maxSize < 0) {
			this.maxSize = maxSize;
		} else {
			throw new IllegalArgumentException("maxSize must be > 0.");
		}
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Severity getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(Severity minLevel) {
		if (minLevel == null) {
			throw new IllegalArgumentException("minLevel is null.");
		}
		this.minLevel = minLevel;
	}

	public List<AbstractNotification> getEvents() {
		return events;
	}

}
