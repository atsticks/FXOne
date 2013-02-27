package org.fxone.core.events;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notification implements Serializable {

	private static final long serialVersionUID = -3616620214662973147L;

	private Severity severity = Severity.INFO;

	private boolean readOnly = false;

	private NotificationDefinition definition;

	private Date createdDT = new Date();

	protected Map<String, Object> data;

	private boolean completed;

	private List<Object> handlers;

	@SuppressWarnings("rawtypes")
	private Map<Class, Object> adapters;

	public Notification(NotificationDefinition def) {
		if (def == null) {
			throw new IllegalArgumentException("Definition required.");
		}
		this.definition = def;
		def.setReadOnly();
	}

	public Notification(Severity severity, NotificationDefinition def) {
		if (severity == null) {
			throw new IllegalArgumentException("Severity required.");
		}
		if (def == null) {
			throw new IllegalArgumentException("Definition required.");
		}
		this.definition = def;
		def.setReadOnly();
		this.severity = severity;
	}

	public String getGroup() {
		return getEventDefinition().getGroup();
	}

	public String getName() {
		return getEventDefinition().getName();
	}

	public int getEventTypeID() {
		return getEventDefinition().getID();
	}

	public Object getOwner() {
		return getData(NotificationDefinition.OWNER);
	}

	public void setOwner(Object owner) {
		setData(NotificationDefinition.OWNER, owner);
	}

	@SuppressWarnings("unchecked")
	public <T> T setAdapter(T adapter) {
		return (T) setAdapter((Class<T>) adapter.getClass(), adapter);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T setAdapter(Class<T> type, T adapter) {
		if (this.adapters == null) {
			this.adapters = Collections
					.synchronizedMap(new HashMap<Class, Object>());
		}
		synchronized (this.adapters) {
			return (T) this.adapters.put(type, adapter);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getAdapter(Class<T> type) {
		if (this.adapters != null) {
			synchronized (this.adapters) {
				return (T) this.adapters.get(type);
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getAdapters() {
		if (this.adapters == null) {
			return new Class[0];
		}
		return this.adapters.keySet().toArray(new Class[this.adapters.size()]);
	}

	public boolean isReadOnly() {
		return this.readOnly;
	}

	// public Item getItem() {
	// Object owner = getOwner();
	// if (owner instanceof Item) {
	// return (Item) owner;
	// }
	// return null;
	// }
	//
	// public void setOwner(Object owner) {
	// checkReadOnly();
	// if (owner == null) {
	// throw new IllegalArgumentException("Owner is required.");
	// }
	// this.owner = owner;
	//
	// }

	public void setReadOnly() {
		this.readOnly = true;
	}

	private void checkReadOnly() {
		if (this.readOnly) {
			throw new IllegalStateException("Event is readOnly.");
		}
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public void setSeverity(Severity severity) {
		if (this.severity == null) {
			throw new IllegalArgumentException("Severity null.");
		}
		this.severity = severity;
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
		result = prime * result
				+ ((adapters == null) ? 0 : adapters.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result
				+ ((createdDT == null) ? 0 : createdDT.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((definition == null) ? 0 : definition.hashCode());
		result = prime * result
				+ ((handlers == null) ? 0 : handlers.hashCode());
		result = prime * result + (readOnly ? 1231 : 1237);
		result = prime * result
				+ ((severity == null) ? 0 : severity.hashCode());
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
		Notification other = (Notification) obj;
		if (adapters == null) {
			if (other.adapters != null)
				return false;
		} else if (!adapters.equals(other.adapters))
			return false;
		if (completed != other.completed)
			return false;
		if (createdDT == null) {
			if (other.createdDT != null)
				return false;
		} else if (!createdDT.equals(other.createdDT))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (handlers == null) {
			if (other.handlers != null)
				return false;
		} else if (!handlers.equals(other.handlers))
			return false;
		if (readOnly != other.readOnly)
			return false;
		if (severity != other.severity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getCreatedDT() + " " + getSeverity() + " [" + getGroup() + "] "
				+ getName() + (readOnly ? ", ro=true" : "")
				+ String.valueOf(getData());
	}

	public Date getCreatedDT() {
		return this.createdDT;
	}

	@SuppressWarnings("unchecked")
	public <T> T setData(Class<T> type, T value) {
		return (T) setData(type.getName(), value);
	}

	public Object setData(String key, Object value) {
		checkReadOnly();
		this.definition.validateParameter(key, value);
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		return this.data.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getData(String key, Class<T> type) {
		if (this.data == null) {
			return null;
		}
		return (T) this.data.get(key);
	}

	public Object getData(String key) {
		return getData(key, null);
	}

	public Object getData(String key, Object defaultValue) {
		if (this.data == null) {
			return defaultValue;
		}
		Object value = this.data.get(key);
		if (value != null) {
			return value;
		}
		return defaultValue;
	}

	public <T> T getData(Class<T> type) {
		return getData(type.getName(), type);
	}

	public Map<String, Object> getData() {
		if (this.data != null) {
			return Collections.unmodifiableMap(this.data);
		}
		return Collections.emptyMap();
	}

	public NotificationDefinition getEventDefinition() {
		return this.definition;
	}

	public final Collection<Object> getHandlers() {
		if (handlers == null) {
			return Collections.emptySet();
		}
		return Collections.unmodifiableList(handlers);
	}

	public final boolean isHandled() {
		return handlers != null;
	}

	public final boolean isCompleted() {
		return completed;
	}

	public final void setCompleted() {
		this.completed = true;
	}

	public final synchronized void setHandledBy(Object h) {
		if (completed) {
			throw new IllegalStateException("Call is already completed.");
		}
		if (handlers == null) {
			handlers = new ArrayList<Object>();
		}
		handlers.add(h);
	}

}
