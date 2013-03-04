package org.fxone.core.events;

@SuppressWarnings("rawtypes")
class ParamDef {

	private String name;
	private Class type;
	private boolean required;
	private boolean nullable;

	public ParamDef(String name, Class type) {
		this(name, type, true, true);
	}
	
	public ParamDef(String name, Class type, boolean required) {
		this(name, type, required, true);
	}

	public ParamDef(String name, Class type, boolean required, boolean nullable) {
		this.required = required;
		this.nullable = nullable;
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name is required.");
		}
		if (type == null) {
			throw new IllegalArgumentException("Type is required.");
		}
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public final Class getType() {
		return type;
	}

	/**
	 * @return the required
	 */
	public final boolean isRequired() {
		return required;
	}
	
	/**
	 * @return the required
	 */
	public final boolean isNullable() {
		return nullable;
	}

	@SuppressWarnings("unchecked")
	public void validate(Object value) {
		if (value == null) {
			if (required) {
				throw new IllegalStateException("Value is required for " + name);
			}
			return;
		}
		if (!type.isAssignableFrom(value.getClass())) {
			throw new IllegalStateException("Value has invalid type, must be "
					+ type.getName() + ", but was "
					+ value.getClass().getName());
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
		ParamDef other = (ParamDef) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ParamDef [name=" + name + ", type=" + type + ", required="
				+ required + "]";
	}

}
