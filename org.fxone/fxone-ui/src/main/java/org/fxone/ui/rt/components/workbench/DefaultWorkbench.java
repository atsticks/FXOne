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
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
import org.fxone.ui.rt.components.clocks.SimpleLabelClockAdapter;

/**
 * A simple JacpFX workbench. Define basic UI settings like size, menus and
 * toolbars here.
 * 
 * @author <a href="mailto:amo.ahcp@gmail.com"> Andy Moncsek</a>
 * 
 */
@Dependent
@Named("workbench")
@Default
public class DefaultWorkbench extends AbstractFXMLComponent implements
		Workbench {
	private static final String DEFAULT_PERSPECTIVE_ID = "default";

	private Stage stage;

	private Group workbenchGroup = new Group();

	private Scene workbenchScene;

	private Perspective<Parent> currentPerspective;

	private Map<String, Perspective> registeredPerspectives = new ConcurrentHashMap<String, Perspective>();

	@FXML
	private Label clockLabel;
	
	@FXML
	private AnchorPane leftSplitPane;
	
	@FXML
	private AnchorPane rightSplitPane;
	
	@Inject
	public DefaultWorkbench(Instance<Perspective> perspectives, Stage stage) {
		super("/org/fxone/ui/rt/components/workbench/Workbench.fxml");
		setId("workbench");
		this.stage = stage;
		workbenchScene = new Scene(workbenchGroup);
		workbenchScene.setRoot(this);
		for (Perspective perspective : perspectives) {
			registeredPerspectives.put(getIdentifier(perspective), perspective);
		}
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
		new SimpleLabelClockAdapter(clockLabel);
		leftSplitPane.getChildren().add(new NavigationTree());
	}

	protected String getStyleSheet() {
		return "/styles/default.css";
	}

	private String getIdentifier(Perspective perspective) {
		// TODO Auto-generated method stub
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

	@Override
	public Enumeration<Perspective> getPerspectives() {
		return Collections.enumeration(this.registeredPerspectives.values());
	}

	@Override
	public Perspective<Parent> getPerspective(String key) {
		return this.registeredPerspectives.get(key);
	}

	@Override
	public Perspective<Parent> getCurrentPerspective() {
		return currentPerspective;
	}

	@Override
	public boolean setCurrentPerspective(String perspectiveID) {
		Perspective<Parent> newPerspective = getPerspective(perspectiveID);
		if (newPerspective == null) {
			return false;
		}
		if (this.currentPerspective != null) {
			this.currentPerspective.deactivated(this);
		}
		this.workbenchScene.setRoot(newPerspective.getUI());
		newPerspective.activated(this);
		return false;
	}

	@Override
	public void setDefaultPerspective() {
		setCurrentPerspective(DEFAULT_PERSPECTIVE_ID);
	}

}
