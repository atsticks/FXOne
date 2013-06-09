package org.fxone.ui.rt.components.header;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.rt.components.layout.VSpacer;

@Dependent
@Named("header")
@Default
public class Header extends VBox {
	
	@Inject
	public Header(@Named("header-links") Node headerLinks, @Named("header-menu") Node headerMenu) {
		setId("header-toolbar");
		setAlignment(Pos.BASELINE_RIGHT);
		VSpacer spacer = new VSpacer();
		spacer.setPrefHeight(4);
		getChildren().addAll(headerLinks, spacer, headerMenu);
	}
	
}
