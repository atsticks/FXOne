package org.fxone.core.types;

public interface AdapterManager {

	public <T> Class<? extends Adapter<T>>[] getAdapters(Class<?> adaptableClass);

	public <T> Adapter<T> getAdapter(Class<T> adapterTarget,
			Class<?> adaptableClass);

	public void registerAdapter(Class<? extends Adapter<?>> adapterType,
			Class<?> adaptableClass);

	public boolean isAdaptable(Class<?> adapterTarget, Class<?> adaptableClass);

}
