package org.fxone.ui.rt.components.view;

/**
  * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
  * All rights reserved. Use is subject to license terms.
  */
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebBrowser extends StackPane {

	private TextField locationField = new TextField(
			"http://www.oracle.com/us/index.html");
	private WebView view = new WebView();
	final WebEngine eng = view.getEngine();

	public WebBrowser() {
		view.setMinSize(500, 400);
		view.setPrefSize(500, 400);
		eng.load("http://www.oracle.com/us/index.html");
		locationField.setMaxHeight(Double.MAX_VALUE);
		Button goButton = new Button("Go");
		goButton.setDefaultButton(true);
		EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				eng.load(getLocation());
			}
		};
		goButton.setOnAction(goAction);
		locationField.setOnAction(goAction);
		eng.locationProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				locationField.setText(newValue);
			}
		});
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		GridPane.setConstraints(locationField, 0, 0, 1, 1, HPos.CENTER,
				VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
		GridPane.setConstraints(goButton, 1, 0);
		GridPane.setConstraints(view, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER,
				Priority.ALWAYS, Priority.ALWAYS);
		grid.getColumnConstraints().addAll(
				new ColumnConstraints(100, 100, Double.MAX_VALUE,
						Priority.ALWAYS, HPos.CENTER, true),
				new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER,
						true));
		grid.getChildren().addAll(locationField, goButton, view);
		getChildren().add(grid);
	}

	public String getLocation() {
		if (locationField.getText().startsWith("http://")
				|| locationField.getText().startsWith("https://")) {
			return locationField.getText();
		} else {
			return "http://" + locationField.getText();
		}
	}

	public void setLocation(String location) {
		locationField.setText(location);
		eng.load(getLocation());
	}

}
