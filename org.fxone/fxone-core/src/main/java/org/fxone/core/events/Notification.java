package org.fxone.core.events;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Notification implements Serializable {

	private static final long serialVersionUID = -3616620214662973147L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Notification.class);

	protected static final String SIMPLE_VALUE = "value";
	
	protected static final String OWNER = "owner";
	
	protected static final String RESULT = "returnValue";

	private boolean readOnly = false;

	private NotificationType type;

	private Date createdDT = new Date();

	protected Map<Object, Object> attributes;

	private boolean completed;

	private Severity severity = Severity.UNKNOWN;
	
	
	public Notification(NotificationType type) {
		if (type == null) {
			throw new IllegalArgumentException("type required.");
		}
		this.type = type;
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Created: " + this);
		}
	}
	
	public Notification(String typeLookupName) {
		this(NotificationType.valueOf(typeLookupName));
	}

	public Notification(NotificationType type, Severity severity) {
		this(type);
		if (severity == null) {
			throw new IllegalArgumentException("Severity required.");
		}
		this.severity = severity;
	}
	
	public Notification(String typeLookupName, Severity severity) {
		this(typeLookupName);
		if (severity == null) {
			throw new IllegalArgumentException("Severity required.");
		}
		this.severity = severity;
	}
	
	public NotificationType getType() {
		return this.type;
	}

	public int getEventTypeID() {
		return getNotificationType().getEventId();
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public Object getOwner() {
		return getAttribute(OWNER);
	}

	@SuppressWarnings("unchecked")
	public <T> void setAdapter(T adapter) {
		setAdapter((Class<T>) adapter.getClass(), adapter);
	}

	public <T> void setAdapter(Class<T> type, T adapter) {
		setAttribute("Adapter:" + type.getName(), adapter);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAdapter(Class<T> type) {
		return (T) getAttribute("Adapter:" + type.getName());
	}

	public void setOwner(Object owner) {
		checkReadOnly();
		if (owner == null) {
			throw new IllegalArgumentException("Owner is required.");
		}
		setAttribute(OWNER, owner);

	}

	public void setReadOnly() {
		if(!this.readOnly){
			this.readOnly = true;
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Set to read-only: " + this);
			}
		}
	}

	private void checkReadOnly() {
		if (this.readOnly) {
			throw new IllegalStateException("Event is readOnly.");
		}
	}

	public void setSeverity(Severity severity) {
		if (this.severity == null) {
			throw new IllegalArgumentException("Severity null.");
		}
		this.severity = severity;
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Severity changed: " + this);
		}
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
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result
				+ ((createdDT == null) ? 0 : createdDT.hashCode());
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result
				+ ((type == null) ? 0 : type.hashCode());
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
		if (completed != other.completed)
			return false;
		if (createdDT == null) {
			if (other.createdDT != null)
				return false;
		} else if (!createdDT.equals(other.createdDT))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		}
		if (readOnly != other.readOnly)
			return false;
		if (severity != other.severity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getType().getDefinitionID() + "[" + getSeverity() + "] "
				+ String.valueOf(getAttributes());
	}

	public Date getCreatedDT() {
		return this.createdDT;
	}

	@SuppressWarnings("unchecked")
	public <T> T setAttribute(Class<T> type, T value) {
		return (T) setAttribute(type.getName(), value);
	}

	public Object setAttribute(String key, Object value) {
		checkReadOnly();
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("Attribute set " + key + '=' + value);
		}
		if (this.attributes == null) {
			this.attributes = new HashMap<Object, Object>();
		}
		return this.attributes.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key, Class<T> type) {
		if (this.attributes == null) {
			return null;
		}
		return (T) this.attributes.get(key);
	}

	public Object getAttribute(String key) {
		return getAttribute(key, null);
	}

	public Object getAttribute(String key, Object defaultValue) {
		if (this.attributes == null) {
			return defaultValue;
		}
		Object value = this.attributes.get(key);
		if (value != null) {
			return value;
		}
		return defaultValue;
	}

	public <T> T getAttribute(Class<T> type) {
		return getAttribute(type.getName(), type);
	}

	public Map<Object, Object> getAttributes() {
		if (this.attributes != null) {
			return Collections.unmodifiableMap(this.attributes);
		}
		return Collections.emptyMap();
	}

	public NotificationType getNotificationType() {
		return type;
	}

	public final boolean isCompleted() {
		return completed;
	}

	public final void setCompleted() {
		this.completed = true;
		
		try{
			notificationCompleted();
			if(LOGGER.isDebugEnabled()){
				LOGGER.debug("Completed notification: " + this);
			}
		}
		catch(Exception e){
			LOGGER.error("Completed notification with error: " + this, e);
		}
	}
	
	protected void notificationCompleted() {
		
	}

	public Object setResult(Object value) {
		if(!getType().isReturningResult()){
			throw new UnsupportedOperationException("Notification has no result: " + getType());
		}
		return setAttribute(RESULT, value);
	}

	public Object getResult() {
		if(!getType().isReturningResult()){
			throw new UnsupportedOperationException("Notification has no result: " + getType());
		}
		return getAttribute(RESULT);
	}
}
