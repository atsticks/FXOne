package org.fxone.core.events;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractNotification<T> implements Serializable {

	private static final long serialVersionUID = -3616620214662973147L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractNotification.class);

	private boolean readOnly = false;

	private Date createdDT = new Date();

	private boolean completed;

	private Severity severity = Severity.UNKNOWN;

	private String group;

	private Object owner;

	private T result;

	private boolean returnsResult;

	public AbstractNotification(Object group) {
		if (group == null) {
			throw new IllegalArgumentException("group required.");
		}
		this.group = group.toString();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created: " + this);
		}
	}

	public AbstractNotification(Object group, Severity severity) {
		if (group == null) {
			throw new IllegalArgumentException("group required.");
		}
		if (severity == null) {
			throw new IllegalArgumentException("Severity required.");
		}
		this.group = group.toString();
		this.severity = severity;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created: " + this);
		}
	}

	public boolean isMatching(Class<? extends AbstractNotification<?>> type) {
		if (type == null) {
			return false;
		}
		return type.isAssignableFrom(getClass());
	}

	public String getGroup() {
		return this.group;
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public Object getOwner() {
		return owner;
	}

	public void setOwner(Object owner) {
		checkReadOnly();
		if (owner == null) {
			throw new IllegalArgumentException("Owner is required.");
		}
		this.owner = owner;
	}

	public void setReadOnly() {
		if (!this.readOnly) {
			this.readOnly = true;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Set to read-only: " + this);
			}
		}
	}

	protected void checkReadOnly() {
		if (this.readOnly) {
			throw new IllegalStateException("Event is readOnly.");
		}
	}

	public void setSeverity(Severity severity) {
		if (this.severity == null) {
			throw new IllegalArgumentException("Severity null.");
		}
		this.severity = severity;
		if (LOGGER.isDebugEnabled()) {
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
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		AbstractNotification other = (AbstractNotification) obj;
		if (completed != other.completed)
			return false;
		if (createdDT == null) {
			if (other.createdDT != null)
				return false;
		} else if (!createdDT.equals(other.createdDT))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (readOnly != other.readOnly)
			return false;
		if (severity != other.severity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + getSeverity() + "] ";
	}

	public Date getCreatedDT() {
		return this.createdDT;
	}

	public final boolean isCompleted() {
		return completed;
	}

	public final void setCompleted() {
		this.completed = true;
		try {
			notificationCompleted();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Completed notification: " + this);
			}
		} catch (Exception e) {
			LOGGER.error("Completed notification with error: " + this, e);
		}
	}

	public T setResult(T value) {
		if (!isReturningResult()) {
			throw new UnsupportedOperationException(
					"Notification has no result.");
		}
		T oldResult = this.result;
		this.result = value;
		return oldResult;
	}

	public boolean isReturningResult() {
		return returnsResult;
	}

	public void setReturningResult(boolean value) {
		checkReadOnly();
		this.returnsResult = value;
	}

	public T getResult() {
		return this.result;
	}

	protected void notificationCompleted() {
	}
}
