package org.fxone.core.cdi;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

import javax.inject.Singleton;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

@Singleton
public class WeldContainerImpl implements CDI{

	private Weld weld;
	private WeldContainer weldContainer;

	
	public <T> T getInstance(Class<T> instanceType, Annotation... qualifiers) {
		 return weldContainer.instance().select(instanceType, qualifiers).get();
	}
	
	public <T> Iterator<T> getInstances(Class<T> instanceType, Annotation... qualifiers) {
		 return weldContainer.instance().select(instanceType, qualifiers).iterator();
	}
	
	public Set<?> getInstances(String name) {
		 return weldContainer.getBeanManager().getBeans(name);
	}

	public void fireEvent(Object evt, Annotation... qualifiers){
		 weldContainer.getBeanManager().fireEvent(evt, qualifiers);
	}
	
	public synchronized void start(){
		if(weld==null){
			System.out.println("*** Starting Container ...");
			weld = new Weld();
			weldContainer = weld.initialize();
			weldContainer.instance().select(WeldContainer.class).get();
			System.out.println("*** Container started.");
		}
	}
	
	public synchronized void stop(){
		if(weld!=null){
			System.out.println("*** Stopping Container ...");
			weld.shutdown();
			weld = null;
			System.out.println("*** Container stopped.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T>  T getNamedInstance(Class<T> type, String id) {
		Set<?> found = weldContainer.getBeanManager().getBeans(id);
		if(found.isEmpty()){
			return null;
		}
		for (Object object : found) {
			if(type.isAssignableFrom(object.getClass())){
				return (T)object;
			}
		}
		return null;
	}

}