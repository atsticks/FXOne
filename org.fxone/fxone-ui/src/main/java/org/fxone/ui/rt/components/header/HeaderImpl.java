package org.fxone.ui.rt.components.header;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.rt.components.view.ViewTitle;

//
//@Dependent
//@Named("header-toolbar")
//@Default
//public class HeaderImpl extends ToolBar  {
//	
//	private ImageView logo;
//	
//	@Inject
//	public HeaderImpl(@Named("header-toolbar") Node headerLinks, @Named("header-logo")Node logo) {
//		setId("header");
//		logo.setId(ViewTitle.LOGOID);
//		getItems().add(logo);
//		HBox.setMargin(logo, new Insets(0, 0, 0, 5));
//		Region spacer = new Region();
//		getItems().add(spacer);
//		HBox.setHgrow(spacer, Priority.ALWAYS);
//		getItems().add(headerLinks);
//		setPrefHeight(66);
//		setMinHeight(66);
//		setMaxHeight(66);
//		GridPane.setConstraints(this, 0, 0);
//	}
//	
//	public void setLogo(String resource){
//		logo.setImage(new Image(getClass()
//				.getResourceAsStream(resource)));
//	}
//
//}
