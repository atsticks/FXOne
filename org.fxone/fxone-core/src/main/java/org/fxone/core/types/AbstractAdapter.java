package org.fxone.core.types;


public abstract class AbstractAdapter<S,T> implements Adapter<T> {

	private Class<T> targetType;
	
	protected AbstractAdapter(Class<T> targetType) {
		if(targetType==null){
			throw new IllegalArgumentException("target type required.");
		}
		this.targetType = targetType;
	}

	
	public abstract T adapt(Object source);
	
	public Class<T> getTargetType(){
		return targetType;
	}
	
}
