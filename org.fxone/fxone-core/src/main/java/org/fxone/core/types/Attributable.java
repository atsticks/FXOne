package org.fxone.core.types;

import java.util.List;
import java.util.Set;


public interface Attributable {

	public <T> boolean defineAttribute(String name, Class<T> type);

	public <T> boolean defineAttribute(String name, Class<T> type,
			T initialValue);

	public ItemAttribute removeAttribute(String name);

	public Class<?> getAttributeType(String name);

	public <T> T getAttribute(String name, Class<T> type);

	public ItemAttribute getAttribute(String name);

	public boolean isAttributeDefined(String name);

	public Set<String> getAttributeNames();

	public <T> void setAttribute(String key, T value);

	public <T> void setAttribute(String key, T value,
			boolean triggerEvents);

	public <T> void setAttribute(String key, T value, Class<T> type,
			boolean triggerEvents);

	public void clearAttributes();

	public List<ItemAttribute> getAttributes();

	public void defineBooleanAttribute(String name);

	public void defineBooleanAttribute(String name, boolean initialValue);

	public void setBooleanAttribute(String name, boolean value);

	public boolean getBooleanAttribute(String name);

	public void defineIntegerAttribute(String name);

	public void defineIntegerAttribute(String name, int initialValue);

	public void setIntegerAttribute(String name, int value);

	public int getIntegerAttribute(String name);

	public void defineLongAttribute(String name);

	public void defineLongAttribute(String name, long initialValue);

	public void setLongAttribute(String name, long value);

	public long getLongAttribute(String name);

	public void defineFloatAttribute(String name);

	public void defineFloatAttribute(String name, float initialValue);

	public void setFloatAttribute(String name, float value);

	public float getFloatAttribute(String name);

	public void defineDoubleAttribute(String name);

	public void defineDoubleAttribute(String name, double initialValue);

	public void setDoubleAttribute(String name, double value);

	public double getDoubleAttribute(String name);

	public void defineShortAttribute(String name);

	public void defineShortAttribute(String name, short initialValue);

	public void setShortAttribute(String name, short value);

	public short getShortAttribute(String name);

	public void defineByteAttribute(String name);

	public void defineByteAttribute(String name, byte initialValue);

	public void setByteAttribute(String name, byte value);

	public byte getByteAttribute(String name);

}
