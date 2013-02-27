package org.fxone.ui.model.view.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.types.AnnotationManager;
import org.fxone.ui.annot.UIComponent;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContext;
import org.fxone.ui.model.view.ViewFactory;
import org.fxone.ui.model.view.ViewManager;

@Singleton
public class ViewManagerImpl implements ViewManager {

	private static final Logger LOGGER = Logger
			.getLogger(ViewManagerImpl.class);

	private Map<String, ViewFactory> viewFactories = new HashMap<String, ViewFactory>();

	@PostConstruct
	public void init() {
		Set<String> viewClasses = AnnotationManager
				.getAnnotatedClasses(UIComponent.class.getName());
		if (viewClasses != null) {
			for (String className : viewClasses) {
				try {
					Class c = Class.forName(className);
					UIComponent viewSpec = (UIComponent) c
							.getAnnotation(UIComponent.class);
					Named nameSpec = (Named) c.getAnnotation(Named.class);
					ViewFactory f = new DefaultViewFactory();
					String name = c.getSimpleName();
					if (nameSpec != null) {
						name = nameSpec.value();
					}
					f.configure(name, viewSpec, c);
					viewFactories.put(name, f);
				} catch (Exception e) {
					LOGGER.error("Error registering adapter: " + className, e);
				}
			}
		}
	}

	@Override
	public boolean isViewDefined(String id) {
		return viewFactories.containsKey(id);
	}

	@Override
	public Set<String> getViewIDs() {
		return viewFactories.keySet();
	}

	@Override
	public View getView(String viewID, String viewContainerID, Object... params) {
		View view = null;
		ViewFactory viewFactory = this.viewFactories.get(viewID);
		if (viewFactory != null) {
			view = viewFactory.createView(viewContainerID, params);
		}
		return view;
	}

	public static class DefaultViewFactory implements ViewFactory {
		private String id;
		private Class<View> viewClass;
		private UIComponent annotation;

		public DefaultViewFactory() {

		}

		@Override
		public View createView(String viewContainerID, Object... params) {
			ViewContext viewContext = new ViewContext(id, viewContainerID,
					annotation, params);
			View v = null;
			try {
				v = viewClass.newInstance();
				v.init(viewContext);
				return v;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return v;
		}

		@Override
		public void configure(String id, UIComponent annotation,
				Class<View> viewClass) {
			this.id = id;
			this.viewClass = viewClass;
			this.annotation = annotation;
		}

	}

}
