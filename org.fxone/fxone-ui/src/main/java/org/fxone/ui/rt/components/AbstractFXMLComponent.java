package org.fxone.ui.rt.components;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.inject.Named;

import org.apache.log4j.Logger;
import org.fxone.ui.annot.UIComponent;

public abstract class AbstractFXMLComponent<T extends Node> extends
		AbstractFXComponent<T> {

	protected final Logger logger = Logger.getLogger(getClass());

	private String fxmlResource;
	private String resourceBundle;
	private T ui;

	public AbstractFXMLComponent() {
		this(null);
	}

	public AbstractFXMLComponent(String fxmlResource) {
		super();
		initComponent(fxmlResource);
		initFields();
	}


	public String getFXMLResource() {
		return fxmlResource;
	}

	public String getResourceBundle() {
		return this.resourceBundle;
	}

	public T getUI() {
		return this.ui;
	}

	private void initComponent(String fxml) {
		// init id
		Named named = getClass().getAnnotation(Named.class);
		if (named != null) {
			this.id = named.value();
		} else {
			this.id = getClass().getName();
		}
		// init rest
		UIComponent comp = getClass().getAnnotation(UIComponent.class);
		if (comp != null) {
			this.fxmlResource = comp.fxmlLocation();
			this.resourceBundle = comp.resourceBundle();
		}
		if (fxmlResource == null || fxmlResource.isEmpty()) {
			this.fxmlResource = fxml;
		}
		if (fxmlResource == null || fxmlResource.isEmpty()) {
			this.fxmlResource = getIdentifier() + ".fxml";
		}
		// initUI
		Locale userLocale = Locale.ENGLISH; // TODO i18n
		try {
			ui = FXMLLoader.load(getClass().getResource(this.fxmlResource),
					ResourceBundle.getBundle(resourceBundle, userLocale));
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to load component: "
					+ getIdentifier(), e);
		}
	}
	
	private void initFields() {
		Field[] fields = getClass().getDeclaredFields();
		for (Field f : fields) {
			if (f.getAnnotation(FXML.class) != null) {
				Node value = ComponentUtil.lookup(ui, f.getName());
				if (value == null) {
					throw new IllegalArgumentException("Lookup failed of "
							+ f.getName() + " in " + this.fxmlResource);
				}
				if (!f.isAccessible()) {
					f.setAccessible(true);
				}
				try {
					f.set(this, value);
					if (logger.isDebugEnabled()) {
						logger.debug("Initialized field: " + f.getName()
								+ " with " + value);
					}
				} catch (Exception e) {
					logger.error("Failed to initialize field: " + f.getName(),
							e);
				}
			}
		}
	}

}
