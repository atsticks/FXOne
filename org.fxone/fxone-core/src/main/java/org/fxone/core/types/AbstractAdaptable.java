package org.fxone.core.types;

import java.util.HashMap;
import java.util.Map;

import org.fxone.core.cdi.Container;

@SuppressWarnings("unchecked")
public class AbstractAdaptable implements Adaptable {

	private Map<Class<?>, Object> adapters = new HashMap<Class<?>, Object>();

	public Object[] getAdapters() {
		synchronized (adapters) {
			return adapters.values().toArray(new Object[adapters.size()]);
		}
	}

	@Override
	public boolean isAdaptable(Class<?> type) {
		return Container.getInstance(AdapterManager.class).isAdaptable(type,
				getClass());
	}

	@Override
	public <T> T getAdapter(Class<T> type) {
		synchronized (adapters) {
			T adaptedValue = (T) this.adapters.get(type);
			if (adaptedValue == null) {
				Adapter<T> adapter = Container
						.getInstance(AdapterManager.class).getAdapter(type,
								getClass());
				if (adapter != null) {
					adaptedValue = adapter.adapt(this);
					this.adapters.put(type, adaptedValue);
				}
			}
			return adaptedValue;
		}
	}

	@Override
	public <T> T removeAdapter(Class<T> type) {
		synchronized (adapters) {
			return (T) this.adapters.remove(type);
		}
	}

}
