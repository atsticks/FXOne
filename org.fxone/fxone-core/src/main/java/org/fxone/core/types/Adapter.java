package org.fxone.core.types;


public interface Adapter<T> {

	public Class<T> getTargetType();
	
	public T adapt(Object tsource);
	
}


