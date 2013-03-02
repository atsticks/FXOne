/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [ModalDialogPane.java]
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
package org.fxone.ui.rt.components.dialog.impl;

import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import org.fxone.ui.rt.components.dialog.Dialog;
import org.fxone.ui.rt.components.dialog.DialogContainer;

/**
 * The Class ModalDialogPane.
 * 
 * @author Patrick Symmangk
 * 
 */
public class ModalDialogPane extends StackPane implements DialogContainer {

	/** The maximum blur radius. */
	private final double MAX_BLUR = 4.0;

	/** The root. */
	private Node root;
	//
	// /** The instance. */
	// private static ModalDialogPane instance;

	private Timeline hideTimeline;
	private Timeline showTimeline;

	public ModalDialogPane(Node rootNode) {
		root = rootNode;
		setId("error-dimmer");
	}

	/**
	 * Show modal message.
	 * 
	 * @param message
	 *            the message
	 */
	public void showDialog(final Dialog dialog) {
		System.out.println("SHOW");
		if (getHideTimeline().getStatus() == Status.RUNNING) {
			getHideTimeline().stop();
		}
		this.getChildren().clear();
		this.getChildren().add((Node) dialog);
		this.setOpacity(0);
		dialog.beforeOpen(this);
		this.setVisible(true);
		this.setCache(true);
		((GaussianBlur) root.getEffect()).setRadius(this.MAX_BLUR);

		getShowTimeline().play();
	}

	private Timeline getHideTimeline() {
		hideTimeline = (hideTimeline == null) ? TimelineBuilder
				.create()
				.keyFrames(
						new KeyFrame(Duration.millis(250),
								new EventHandler<ActionEvent>() {
									@Override
									public void handle(final ActionEvent t) {
										ModalDialogPane.this.setCache(false);
										ModalDialogPane.this.setVisible(false);
									}
								}, new KeyValue(this.opacityProperty(), 0,
										Interpolator.EASE_BOTH))).build()
				: hideTimeline;
		return hideTimeline;
	}

	private Timeline getShowTimeline() {
		showTimeline = (showTimeline == null) ? TimelineBuilder
				.create()
				.keyFrames(
						new KeyFrame(Duration.millis(250),
								new EventHandler<ActionEvent>() {
									@Override
									public void handle(final ActionEvent t) {
										ModalDialogPane.this.setCache(false);
									}
								}, new KeyValue(this.opacityProperty(), 1,
										Interpolator.EASE_BOTH))).build()
				: showTimeline;
		return showTimeline;
	}

	@Override
	public void hideDialog(Dialog dialog) {
		System.out.println("HIDE");
		this.setCache(true);
		getHideTimeline().play();
		((GaussianBlur) root.getEffect()).setRadius(0.0);
	}

}
