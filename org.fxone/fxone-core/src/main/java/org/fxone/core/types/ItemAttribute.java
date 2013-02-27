package org.fxone.core.types;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class ItemAttribute extends AbstractNamedItem implements
		Comparable<ItemAttribute>, Serializable {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6737939471995608555L;

	private static final Logger LOGGER = Logger.getLogger(ItemAttribute.class);

	private String typeName;

	private Class<?> type;

	private Object value;

	private boolean serialized = false;

	public ItemAttribute() {

	}

	public ItemAttribute(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Name is null.");
		}
		setName(name);
	}

	public ItemAttribute(String name, Object value) {
		if (name == null) {
			throw new IllegalArgumentException("Name is null.");
		}
		setName(name);
		setValue(value);
	}

	public ItemAttribute(String name, Class<?> type) {
		if (name == null) {
			throw new IllegalArgumentException("Name is null.");
		}
		setName(name);
		setType(type);
	}

	public <T> ItemAttribute(String name, Class<T> type,
			T value) {
		if (name == null) {
			throw new IllegalArgumentException("Name is null.");
		}
		setName(name);
		setType(type);
		setValue(value);
	}

	public Class<?> getType() {
		if (type == null) {
			try {
				this.type = Class.forName(this.typeName, true, Thread
						.currentThread().getContextClassLoader());
			} catch (ClassNotFoundException e) {
				LOGGER.error("Error loading type for device attribute: "
						+ typeName, e);
			}
		}
		return type;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
		if (!isTextMappableType(typeName)) {
			this.serialized = true;
		}
	}

	public void setType(Class<?> t) {
		if (t == null) {
			throw new IllegalArgumentException("Type may not be null.");
		}
		setTypeName(t.getName());
		this.type = t;
	}

	public final void setValue(Object val) {
		if (val != null && !getType().isAssignableFrom(val.getClass())) {
			throw new IllegalArgumentException("Value has invalid type "
					+ val.getClass() + ", required was " + getType() + ".");
		}
		this.value =  val;
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(Class<T> type) {
		return (T) this.value;
	}

	public String getStringValue() {
		return convertToString(this.value);
	}

	public void setStringValue(String value) {
		if (value != null && value.startsWith("<binary[")) {
			return;
		}
		this.value = convertFromString(value);
	}

	public boolean isSet() {
		return this.value != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (serialized ? 1231 : 1237);
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemAttribute other = (ItemAttribute) obj;
		if (serialized != other.serialized)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceAttribute [getName()=" + getName() + ", typeName="
				+ typeName + ", serialized=" + serialized + ", value=" + value
				+ "]";
	}

	public int compareTo(ItemAttribute o) {
		if (o == null) {
			return 1;
		}
		ItemAttribute other = (ItemAttribute) o;
		return getName().compareTo(other.getName());
	}

	private boolean isTextMappableType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("Type param required.");
		}
		if (String.class.getName().equals(type)) {
			return true;
		}
		if (Boolean.class.getName().equals(type)) {
			return true;
		}
		if (Byte.class.getName().equals(type)) {
			return true;
		}
		if (Character.class.getName().equals(type)) {
			return true;
		}
		if (Short.class.getName().equals(type)) {
			return true;
		}
		if (Integer.class.getName().equals(type)) {
			return true;
		}
		if (Long.class.getName().equals(type)) {
			return true;
		}
		if (Float.class.getName().equals(type)) {
			return true;
		}
		if (Double.class.getName().equals(type)) {
			return true;
		}
		return false;
	}

	private String convertToString(Object object) {
		if (object == null) {
			return null;
		}
		if (String.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Boolean.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Byte.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Character.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Short.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Integer.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Long.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Float.class.equals(object.getClass())) {
			return object.toString();
		}
		if (Double.class.equals(object.getClass())) {
			return object.toString();
		}
		return "<binary[" + object.toString() + "]>";
	}

	private Serializable convertFromString(String input) {
		if (input == null) {
			return null;
		}
		if (String.class.getName().equals(getTypeName())) {
			return input;
		}
		if (Boolean.class.getName().equals(getTypeName())) {
			return Boolean.valueOf(input);
		}
		if (Byte.class.getName().equals(getTypeName())) {
			return Byte.valueOf(input);
		}
		if (Character.class.getName().equals(getTypeName())) {
			if (input.length() != 1) {
				throw new IllegalArgumentException(
						"Character can be only be of size one, but was '"
								+ input + "'.");
			}
			return Character.valueOf(input.charAt(0));
		}
		if (Short.class.getName().equals(getTypeName())) {
			return Short.valueOf(input);
		}
		if (Integer.class.getName().equals(getTypeName())) {
			return Integer.valueOf(input);
		}
		if (Long.class.getName().equals(getTypeName())) {
			return Long.valueOf(input);
		}
		if (Float.class.getName().equals(getTypeName())) {
			return Float.valueOf(input);
		}
		if (Double.class.getName().equals(getTypeName())) {
			return Double.valueOf(input);
		}
		throw new IllegalArgumentException(
				"Type is not mappable from text, but not serializable: "
						+ getTypeName());
	}

}
