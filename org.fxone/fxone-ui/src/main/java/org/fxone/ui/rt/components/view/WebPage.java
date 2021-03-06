package org.fxone.ui.rt.components.view;

/**
  * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
  * All rights reserved. Use is subject to license terms.
  */
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContext;

public class WebPage extends AnchorPane implements View {

	private String location;
	private WebView view = new WebView();

	public WebPage(String pageId) {
		this(pageId, pageId);
	}

	public WebPage(String pageId, String location) {
		if (location == null) {
			throw new IllegalArgumentException("location is null.");
		}
		getChildren().add(view);
		AnchorPane.setBottomAnchor(view, 0d);
		AnchorPane.setTopAnchor(view, 0d);
		AnchorPane.setRightAnchor(view, 0d);
		AnchorPane.setLeftAnchor(view, 0d);
		setLocation(location);
		setMinSize(100, 100);
		setPrefSize(1024, 1280);
		setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	public String getLocation() {
		if (location == null) {
			return null;
		}
		if (location.startsWith("http://") || location.startsWith("https://")) {
			return location;
		} else {
			return "http://" + location;
		}
	}

	public void setLocation(String location) {
		this.location = location;
		if (location == null) {
			view.getEngine().load("<html/>");
		} else {
			view.getEngine().load(getLocation());
		}
	}

	@Override
	public void init(ViewContext viewContext) {
	}

	@Override
	public void opened() {
		view.getEngine().load(getLocation());
	}

	@Override
	public boolean canClose() {
		return true;
	}

	@Override
	public void closed() {
	}

	@Override
	public String getName() {
		return this.location;
	}
}
