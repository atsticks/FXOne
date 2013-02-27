package org.fxone.core.cdi;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

public interface CDI {

	public <T> T getInstance(Class<T> instanceType, Annotation... qualifiers);

	public <T> Iterator<T> getInstances(Class<T> instanceType,
			Annotation... qualifiers);

	public Set<?> getInstances(String name);

	public void fireEvent(Object evt, Annotation... qualifiers);

	public void start();

	public void stop();

	public <T> T getNamedInstance(Class<T> type, String id);
	

}