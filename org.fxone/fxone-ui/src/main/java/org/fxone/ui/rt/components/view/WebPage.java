package org.fxone.ui.rt.components.view;

/**
  * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
  * All rights reserved. Use is subject to license terms.
  */
import javafx.scene.web.WebView;

import org.fxone.ui.model.view.AbstractView;

public class WebPage extends AbstractView {

	private String location;
	private WebView view = new WebView();

	public WebPage(String pageId) {
		this(pageId, null);
	}
	
	public WebPage(String pageId, String location) {
		super(location);
		if(location==null){
			throw new IllegalArgumentException("location is null.");
		}
		setLocation(location);
	}

	public String getLocation() {
		if(location==null){
			return null;
		}
		if (location.startsWith("http://")
				|| location.startsWith("https://")) {
			return location;
		} else {
			return "http://" + location;
		}
	}

	public void setLocation(String location) {
		this.location = location;
		if(location==null){
			view.getEngine().load("<html/>");
		}
		else{
			view.getEngine().load(getLocation());
		}
	}

	@Override
	public Object getUI() {
		return this.view;
	}

	
}
