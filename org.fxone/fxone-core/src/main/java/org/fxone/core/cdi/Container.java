package org.fxone.core.cdi;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

public final class Container {

	private static boolean hookRegistered = false;
	private static CDI cdiContainer;

	static {
		// TODO init alternate container here...
		cdiContainer = new WeldContainerImpl();
	}

	public static <T> T getInstance(Class<T> instanceType,
			Annotation... qualifiers) {
		return cdiContainer.getInstance(instanceType, qualifiers);
	}

	public static <T> Iterator<T> getInstances(Class<T> instanceType,
			Annotation... qualifiers) {
		return cdiContainer.getInstances(instanceType, qualifiers);
	}

	public static Set<?> getInstances(String name) {
		return cdiContainer.getInstances(name);
	}

	public static void fireEvent(Object evt, Annotation... qualifiers) {
		cdiContainer.fireEvent(evt, qualifiers);
	}

	public static synchronized void start() {
		cdiContainer.start();
		if (!hookRegistered) {
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					cdiContainer.stop();
				}
			});
			hookRegistered = true;
		}
	}

	public static synchronized void stop() {
		cdiContainer.stop();
	}

	public static void main(String[] args) {
		start();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getNamedInstance(Class<T> type, String id) {
		return cdiContainer.getNamedInstance(type, id);
	}
}