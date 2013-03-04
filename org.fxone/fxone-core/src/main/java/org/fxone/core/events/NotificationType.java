package org.fxone.core.events;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.fxone.core.annot.NotificationExtension;
import org.fxone.core.events.AbstractNotificationConsumer.ParseResult;
import org.fxone.core.types.AnnotationManager;

public final class NotificationType implements Comparable<NotificationType> {

	private static final AtomicInteger COUNTER = new AtomicInteger();

	private int id = COUNTER.incrementAndGet();
	private NotificationGroup group;
	private String name;
	private String description;
	private Map<String, ParamDef> params = new HashMap<String, ParamDef>();
	private Severity severity = Severity.UNKNOWN;
	private boolean readOnly = false;
	private Class resultType;

	private static final Map<String, NotificationType> REGISTERED_TYPES = new ConcurrentHashMap<String, NotificationType>();

	static {
		Set<String> classNames = AnnotationManager
				.getAnnotatedClasses(NotificationExtension.class.getName());
		for (String className : classNames) {
			try {
				Class<?> clazz = Class.forName(className);
				if (NotificationProvider.class.isAssignableFrom(clazz)) {
					NotificationProvider np = (NotificationProvider) clazz
							.newInstance();
					np.registerEventDefinitions();
				}
			} catch (Exception e) {
				Logger.getLogger(NotificationType.class).error(
						"Failed to load/access NotificationExtension.", e);
			}
		}
	}

	public boolean isReturningResult() {
		return resultType != null;
	}

	public static NotificationType registerNotificationType(
			NotificationType type) {
		NotificationType existing = REGISTERED_TYPES.get(type.getDefinitionID());
		if (existing != null) {
			return existing;
		}
		synchronized(REGISTERED_TYPES){
			existing = REGISTERED_TYPES.get(type.getDefinitionID());
			if (existing != null) {
				return existing;
			}
			type.setReadOnly();
			REGISTERED_TYPES.put(type.getDefinitionID(), type);
		}
		return type;
	}

	public static NotificationType valueOf(String group, String name) {
		return valueOf(NotificationGroup.valueOf(group), name);
	}

	public static NotificationType valueOf(NotificationGroup group, String name) {
		return valueOf(group.getName() + ':' + name);
	}

	public static NotificationType valueOf(String compoundName) {
		NotificationType found = REGISTERED_TYPES.get(compoundName);
		if (found == null) {
			throw new IllegalArgumentException("No such type with ID: "
					+ compoundName);
		}
		return found;
	}

	private NotificationType(NotificationGroup family, String name,
			String description, Severity severity, Map<String, ParamDef> params) {
		this(family, name, description, severity, params, null);
	}

	private NotificationType(NotificationGroup family, String name,
			String description, Severity severity,
			Map<String, ParamDef> params, Class<?> resultType) {
		this.group = family;
		this.name = name;
		this.description = description;
		this.params = params;
		this.resultType = resultType;
	}

	public int getEventId() {
		return id;
	}

	public String getDefinitionID() {
		return group.getName() + ": " + name;
	}

	public String getName() {
		return this.name;
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public NotificationType setReadOnly() {
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

	public NotificationGroup getGroup() {
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
		NotificationType other = (NotificationType) obj;
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
				+ ", readOnly=" + readOnly + "]";
	}

	public int compareTo(NotificationType o) {
		int compare = group.compareTo(o.getGroup());
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
	public NotificationType defineParameter(String name, Class type) {
		return defineParameter(name, type, true);
	}

	@SuppressWarnings("rawtypes")
	public NotificationType defineParameter(String name, Class type,
			boolean required) {
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
	public NotificationType defineParameter(Class type) {
		return defineParameter(type.getName(), type, true);
	}

	@SuppressWarnings("rawtypes")
	public NotificationType defineParameter(Class type, boolean required) {
		return defineParameter(type.getName(), type, required);
	}

	public boolean isMatching(Notification event) {
		return event.getEventTypeID() == getEventId();
	}

	public boolean isMatching(ParseResult result) {
		return getGroup().getName().equals(result.group)
				&& getName().equals(result.name);
	}

	public static final class Builder {

		private String group;
		private String name;
		private String description;
		private Map<String, ParamDef> params = new HashMap<String, ParamDef>();
		private Severity severity = Severity.UNKNOWN;
		private Class<?> resultType;

		public Builder() {

		}

		public Builder(String group, String name) {
			this(group, name, null, null);
		}

		public Builder(String group, String name, String description) {
			this(group, name, description, null);
		}

		public Builder(String group, String name, String description,
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
			if (severity != null) {
				this.severity = severity;
			}
			this.group = group;
			this.name = name;
			this.description = description;
		}

		public boolean isBuildable() {
			if (group == null) {
				return false;
			}
			if (name == null) {
				return false;
			}
			return true;
		}

		public String getDefinitionID() {
			return '[' + group + ']' + name;
		}

		public String getName() {
			return this.name;
		}

		public Severity getSeverity() {
			return this.severity;
		}

		public String getDescription() {
			return description;
		}

		public Builder setName(String name) {
			if (name == null) {
				throw new IllegalArgumentException("name must not be null.");
			}
			this.name = name;
			return this;
		}

		public Builder setGroup(String group) {
			if (group == null) {
				throw new IllegalArgumentException("group must not be null.");
			}
			this.group = group;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setSeverity(Severity severity) {
			if (severity == null) {
				throw new IllegalArgumentException("severity must not be null.");
			}
			this.severity = severity;
			return this;
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
		public String toString() {
			return "EventDefinition.Builder [group=" + group + ", name=" + name
					+ "]";
		}

		@SuppressWarnings("rawtypes")
		public Builder defineParameter(String name, Class type) {
			return defineParameter(name, type, true);
		}

		@SuppressWarnings("rawtypes")
		public Builder defineParameter(String name, Class type, boolean required) {
			if (this.params.containsKey(name)) {
				throw new IllegalArgumentException(
						"Parameter is already defined.");
			}
			ParamDef def = new ParamDef(name, type, required);
			this.params.put(name, def);
			return this;
		}

		@SuppressWarnings("rawtypes")
		public Builder defineParameter(Class type) {
			return defineParameter(type.getName(), type, true);
		}

		@SuppressWarnings("rawtypes")
		public Builder defineParameter(Class type, boolean required) {
			return defineParameter(type.getName(), type, required);
		}

		public NotificationType build() {
			if (!isBuildable()) {
				throw new IllegalStateException(
						"NotificationType is not fully defined.");
			}
			return new NotificationType(NotificationGroup.valueOf(group), name,
					description, severity, this.params, resultType);
		}

		public NotificationType buildAndRegister() {
			NotificationType type = build();
			return registerNotificationType(type);
		}

		public Builder addResult(Class<?> resultType) {
			this.resultType = resultType;
			return this;
		}
	}

	public static Enumeration<NotificationType> getTypes() {
		return Collections.enumeration(REGISTERED_TYPES.values());
	}

}
