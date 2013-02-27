package org.fxone.ui.rt.components;

import javafx.scene.Node;

import javax.inject.Named;

import org.fxone.ui.model.AbstractUIComponent;

public abstract class AbstractFXComponent<T extends Node> extends
		AbstractUIComponent<T> {

	public AbstractFXComponent() {
		super("tempId");
		initComponent();
	}

	
	public abstract T getUI();
	

	private void initComponent() {
		// init id
		Named named = getClass().getAnnotation(Named.class);
		if (named != null) {
			this.id = named.value();
		} else {
			this.id = getClass().getName();
		}
	}
	
}
