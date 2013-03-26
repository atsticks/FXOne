package org.fxone.core.events;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractAdaptableNotification extends AbstractNotification {

	private static final long serialVersionUID = -3616620214662973147L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractAdaptableNotification.class);

	private Map<Object, Object> attributes;

	public AbstractAdaptableNotification(String group) {
		super(group);
	}

	public AbstractAdaptableNotification(String group, Severity severity) {
		super(group, severity);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (attributes==null ? 0 : attributes.hashCode());
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
		AbstractAdaptableNotification other = (AbstractAdaptableNotification) obj;
		if (!super.equals(obj)){
			return false;
		}
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + getSeverity() + "] "
				+ String.valueOf(getAttributes());
	}

	@SuppressWarnings("unchecked")
	public <T> T setAttribute(Class<T> type, T value) {
		return (T) setAttribute(type.getName(), value);
	}

	public Object setAttribute(String key, Object value) {
		checkReadOnly();
		if (LOGGER.isDebugEnabled()) {
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
}
