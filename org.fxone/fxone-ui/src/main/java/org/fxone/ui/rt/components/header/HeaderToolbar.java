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
@Named("header-toolbar")
@Default
public class HeaderToolbar extends VBox {
	
	@Inject
	public HeaderToolbar(@Named("header-linkbar") Node headerLinks, @Named("header-menu") Node headerMenu) {
		setId("header-toolbar");
		setAlignment(Pos.BASELINE_RIGHT);
		VSpacer spacer = new VSpacer();
		spacer.setPrefHeight(2);
		getChildren().addAll(headerLinks, spacer, headerMenu);
	}
	
}
