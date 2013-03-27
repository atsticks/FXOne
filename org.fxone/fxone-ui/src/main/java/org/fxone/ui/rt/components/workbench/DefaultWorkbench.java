/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [DefaultWorkbench.java]
 * AHCP Project (http://jacp.googlecode.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 *
 ************************************************************************/
package org.fxone.ui.rt.components.workbench;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
import org.fxone.ui.rt.components.clocks.SimpleLabelClockAdapter;

import com.sun.javafx.tk.Toolkit;

/**
 * A simple JacpFX workbench. Define basic UI settings like size, menus and
 * toolbars here.
 * 
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 * 
 */
@Singleton
@Named("workbench")
@Default
public class DefaultWorkbench extends AbstractFXMLComponent implements
		Workbench {
	private static final String DEFAULT_PERSPECTIVE_ID = "default";

	private static final Logger LOGGER = Logger
			.getLogger(DefaultWorkbench.class);

	private Stage stage;

	private Group workbenchGroup = new Group();

	private Scene workbenchScene;

	private Perspective currentPerspective;

	@SuppressWarnings("rawtypes")
	private Map<String, Perspective> registeredPerspectives = new ConcurrentHashMap<String, Perspective>();

	private Label globalDescription = new Label();

	@FXML
	private Label workbenchRightTitle;

	@FXML
	private Label workbenchMiddleTitle; // alterantively: HBox
										// workbenchMiddleHeader

	@FXML
	private AnchorPane workbenchPerspectivePane;

	@FXML
	private VBox workbenchRightHeader; // for global menus, user logout links
										// etc.

	@FXML
	private VBox workbenchHeaderPane;

	@FXML
	private BorderPane workbenchFooterPane;

	@FXML
	private Label workbenchStatus;

	@FXML
	private Label workbenchInfo;

	@Inject
	public DefaultWorkbench(Instance<Perspective> perspectives, Stage stage,
			@Named("header") Node header) {
		super("/org/fxone/ui/rt/components/workbench/DefaultWorkbench.fxml");
		setId("workbench");
		this.stage = stage;
		LOGGER.info("Loading Workbench...");
		workbenchScene = new Scene(workbenchGroup);
		workbenchScene.setRoot(this);
		for (Perspective perspective : perspectives) {
			registeredPerspectives.put(getIdentifier(perspective), perspective);
		}
		LOGGER.info("Loaded perspectives: " + registeredPerspectives.keySet());
		stage.initStyle(StageStyle.DECORATED);
		// window.setFullScreen(true);
		workbenchScene.getStylesheets().addAll(
				getClass().getResource(getStyleSheet()).toExternalForm());
		boolean is3dSupported = Platform
				.isSupported(ConditionalFeature.SCENE3D);
		if (is3dSupported) {
			// RT-13234
			workbenchScene.setCamera(new PerspectiveCamera());
		}
		new SimpleLabelClockAdapter(workbenchRightTitle);
		setDefaultPerspective();
		this.workbenchHeaderPane.getChildren().add(globalDescription);
		setInfo("This is a small demonstration workbench...");
		workbenchRightHeader.getChildren().add(header);
		this.layout();
	}

	public void setGlobalDescription(String text) {
		if (text == null) {
			globalDescription.setVisible(false);
		} else {
			globalDescription.setVisible(false);
			globalDescription.setText(text);
		}
	}

	protected String getStyleSheet() {
		return "/styles/default.css";
	}

	private String getIdentifier(Perspective perspective) {
		Named named = perspective.getClass().getAnnotation(Named.class);
		if (named != null) {
			return named.value();
		}
		return perspective.getClass().getSimpleName();
	}

	@Override
	public void setWindowTitle(String title) {
		this.stage.setTitle(title);
	}

	@Override
	public String getWindowTitle() {
		return this.stage.getTitle();
	}

	@Override
	public void setFullScreen(boolean set) {
		this.stage.setFullScreen(set);
	}

	@Override
	public void centerOnScreen() {
		this.stage.centerOnScreen();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration<Perspective> getPerspectives() {
		return Collections.enumeration(this.registeredPerspectives.values());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Perspective getPerspective(String key) {
		return this.registeredPerspectives.get(key);
	}

	@Override
	public Perspective getCurrentPerspective() {
		return currentPerspective;
	}

	@Override
	public boolean setCurrentPerspective(final String perspectiveID) {
		final Perspective newPerspective = getPerspective(perspectiveID);
		if (newPerspective == null) {
			return false;
		}
		if (this.currentPerspective != null) {
			this.currentPerspective.deactivated(this);
		}
		if (!Toolkit.getToolkit().isFxUserThread()) {
			Toolkit.getToolkit().defer(new Runnable() {
				@Override
				public void run() {
					newPerspective.activated(DefaultWorkbench.this);
					Node node = (Node) newPerspective;
					DefaultWorkbench.this.workbenchPerspectivePane
							.getChildren().clear();
					DefaultWorkbench.this.workbenchPerspectivePane
							.getChildren().add(node);
					AnchorPane.setTopAnchor(node, 0.0d);
					AnchorPane.setBottomAnchor(node, 0.0d);
					AnchorPane.setLeftAnchor(node, 0.0d);
					AnchorPane.setRightAnchor(node, 0.0d);
					LOGGER.info("Switched Perspective to: " + perspectiveID);
				}
			});
		} else {
			newPerspective.activated(DefaultWorkbench.this);
			Node node = (Node) newPerspective;
			this.workbenchPerspectivePane.getChildren().clear();
			this.workbenchPerspectivePane.getChildren().add(node);
			AnchorPane.setTopAnchor(node, 0.0d);
			AnchorPane.setBottomAnchor(node, 0.0d);
			AnchorPane.setLeftAnchor(node, 0.0d);
			AnchorPane.setRightAnchor(node, 0.0d);
			LOGGER.info("Switched Perspective to: " + perspectiveID);
		}
		return true;
	}

	@Override
	public void setDefaultPerspective() {
		setCurrentPerspective(DEFAULT_PERSPECTIVE_ID);
		setStatus("OK.");
	}

	public void setStatus(String status) {
		this.workbenchStatus.setText(status);
	}

	public String getStatus() {
		return this.workbenchStatus.getText();
	}

	public void setInfo(String info) {
		this.workbenchInfo.setText(info);
	}

	public String getInfo() {
		return this.workbenchInfo.getText();
	}

}
