package org.fxone.core.events;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class NotificationGroup implements Comparable<NotificationGroup>{

	private static final Map<String, NotificationGroup> GROUPS = new ConcurrentHashMap<String, NotificationGroup>();

	private String name;

	private NotificationGroup(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationGroup other = (NotificationGroup) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() {
		return "EventGroup[" + name + ']';
	}

	public static NotificationGroup valueOf(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"AbstractNotification groupmust not be null or empty.");
		}
		NotificationGroup g = GROUPS.get(name);
		if (g == null) {
			synchronized (GROUPS) {
				g = GROUPS.get(name);
				if (g == null) {
					g = new NotificationGroup(name);
					GROUPS.put(name, g);
				}
			}
		}
		return g;
	}

	public static Enumeration<NotificationGroup> getGroups() {
		return Collections.enumeration(GROUPS.values());
	}

	public int compareTo(NotificationGroup group) {
		return this.name.compareTo(group.name);
	}
}
