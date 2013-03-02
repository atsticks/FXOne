package org.fxone.core;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Context {

	private static final Context INSTANCE = new Context();
	
	private Date created = new Date();
	private String id = UUID.randomUUID().toString();
	
	private Map<Object, Object> contextData = new ConcurrentHashMap<Object, Object>();

	private Context(){}
	
	public static Context get(){
		return INSTANCE;
	}
	
	public String getID() {
		return this.id;
	}

	public Date getCreated(){
		return (Date) this.created.clone();
	}

	public <T> T getAttribute(Object key, Class<T> type) {
		return (T) this.contextData.get(key);
	}

	public <T> T getSingleton(Class<T> type) {
		return getInstance(type, false);
	}

	public <T> T getInstance(Class<T> type, boolean createIfMissing) {
		T t = getAttribute(type, type);
		if (t != null || !createIfMissing) {
			return t;
		}
		try {
			t = (T) type.newInstance();
			setSingleton(type, t);
			return t;
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not instantiate type: "
					+ type + ").", e);
		}
	}

	public <T> T setAttribute(Object key, Class<T> type, T value) {
		if(key==null){
			throw new IllegalArgumentException("Can not set attribute: key==null.");
		}
		if(value==null){
			return (T) this.contextData.remove(key);
		}
		return (T) this.contextData.put(key, value);
	}

	public <T> T setSingleton(Class<T> type, T value) {
		return (T) setAttribute(type, type, value);
	}

}