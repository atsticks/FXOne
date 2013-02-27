package org.fxone.core.events;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class NotificationDefinition implements Comparable<NotificationDefinition> {

	private static final AtomicInteger COUNTER = new AtomicInteger();

	private int id = COUNTER.incrementAndGet();
	private String group;
	private String name;
	private String description;
	private Map<String, ParamDef> params = new HashMap<String, ParamDef>();
	private Severity severity = Severity.INFO;
	private boolean readOnly = false;
	
	public static final String OWNER = "owner";

	public NotificationDefinition(String group, String name, String description,
			Severity severity) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("description required.");
		}
		if (group == null) {
			throw new IllegalArgumentException("group required.");
		}
		if (name == null) {
			throw new IllegalArgumentException("name required.");
		}
		if (severity == null) {
			throw new IllegalArgumentException("severity required.");
		}
		this.group = group;
		this.name = name;
		this.description = description;
		defineParameter(OWNER, Object.class, false);
	}

	public int getID() {
		return id;
	}

	public String getDefinitionID() {
		return group + ": " + name + " [" + severity + ']';
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		checkReadOnly();
		this.severity = severity;
	}

	public String getName() {
		return this.name;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public NotificationDefinition setReadOnly() {
		this.readOnly = true;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		checkReadOnly();
		this.description = description;
	}

	public String getGroup() {
		return group;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Class> getParameterTypes() {
		Map<String, Class> result = new TreeMap<String, Class>();
		for (ParamDef def : this.params.values()) {
			result.put(def.getName(), def.getClass());
		}
		return result;
	}

	public int getParamCount() {
		return this.params.size();
	}

	@SuppressWarnings("rawtypes")
	public Class getParamType(String key) {
		ParamDef def = this.params.get(key);
		if (def != null) {
			return def.getType();
		}
		return null;
	}

	public boolean isParamRequired(String key) {
		ParamDef def = this.params.get(key);
		if (def != null) {
			return def.isRequired();
		}
		return false;
	}

	public String[] getParamKeys() {
		return this.params.keySet().toArray(new String[params.size()]);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationDefinition other = (NotificationDefinition) obj;
		if (id == other.id) {
			return true;
		}
		if (getDefinitionID().equals(other.getDefinitionID())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "EventDefinition [group=" + group + ", name=" + name
				+ ", severity=" + severity + ", readOnly=" + readOnly + "]";
	}

	public int compareTo(NotificationDefinition o) {
		int compare = group.compareTo(o.group);
		if (compare != 0) {
			return compare;
		}
		return name.compareTo(o.name);
	}

	public void validateParameter(String key, Object value) {
		ParamDef def = this.params.get(key);
		if (def == null) {
			throw new IllegalArgumentException("No such parameter defined: "
					+ key);
		}
		def.validate(value);
	}

	@SuppressWarnings("rawtypes")
	public NotificationDefinition defineParameter(String name, Class type) {
		return defineParameter(name, type, true);
	}

	@SuppressWarnings("rawtypes")
	public NotificationDefinition defineParameter(String name, Class type, boolean required) {
		checkReadOnly();
		if (this.params.containsKey(name)) {
			throw new IllegalArgumentException("Parameter is already defined.");
		}
		ParamDef def = new ParamDef(name, type, required);
		this.params.put(name, def);
		return this;
	}

	private void checkReadOnly() {
		if (this.readOnly) {
			throw new IllegalStateException("EventDefinition is read only.");
		}
	}

	@SuppressWarnings("rawtypes")
	public NotificationDefinition defineParameter(Class type) {
		return defineParameter(type.getName(), type, true);
	}

	@SuppressWarnings("rawtypes")
	public NotificationDefinition defineParameter(Class type, boolean required) {
		return defineParameter(type.getName(), type, required);
	}

	public boolean isMatching(Notification event) {
		return event.getEventTypeID()==getID();
	}

}
