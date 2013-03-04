package org.fxone.core.types;

public interface Adaptable {
	
	public Object[] getAdapters();

	public <T> T getAdapter(Class<T> type);

	public boolean isAdaptable(Class<?> type);

}
