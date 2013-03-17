package org.fxone.ui.rt.components;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import javax.inject.Named;

import org.apache.log4j.Logger;

import com.sun.corba.se.spi.ior.Identifiable;

public class AbstractFXMLComponent extends
		AnchorPane{

	private static final String DEFAULT_BUNDLE = "i18n/translation";

	protected final Logger logger = Logger.getLogger(getClass());

	private String fxmlResource;
	private String resourceBundle;
	private Node ui;

	public AbstractFXMLComponent() {
		this(null, null);
	}
	
	public AbstractFXMLComponent(String fxmlResource) {
		this(fxmlResource, null);
	}

	public AbstractFXMLComponent(String fxmlResource, String resourceBundle) {
		super();
		setId(getClass().getSimpleName());
		initComponent(fxmlResource, resourceBundle);
		initFields();
	}

	public String getFXMLResource() {
		return fxmlResource;
	}

	public String getResourceBundle() {
		return this.resourceBundle;
	}

	private void initComponent(String fxmlResource, String resourceBundle) {
		// init rest
		if (fxmlResource == null || fxmlResource.isEmpty()) {
			this.fxmlResource = getClass().getName() + ".fxml";
		}
		else{
			this.fxmlResource = fxmlResource;
		}
		this.resourceBundle = resourceBundle;
		if(this.resourceBundle == null){
			this.resourceBundle = DEFAULT_BUNDLE;
		}
		// initUI
		Locale userLocale = Locale.ENGLISH; // TODO i18n
		try {
			ui = FXMLLoader.load(getClass().getResource(this.fxmlResource),
					ResourceBundle.getBundle(this.resourceBundle, userLocale));
			this.getChildren().add(ui);
			AnchorPane.setBottomAnchor(ui, 0d);
			AnchorPane.setTopAnchor(ui, 0d);
			AnchorPane.setLeftAnchor(ui, 0d);
			AnchorPane.setRightAnchor(ui, 0d);
		} catch (IOException e) {
			throw new IllegalArgumentException("Failed to load component: "
					+ this, e);
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
