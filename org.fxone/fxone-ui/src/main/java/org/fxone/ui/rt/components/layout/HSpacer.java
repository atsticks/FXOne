package org.fxone.ui.rt.components.layout;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HSpacer extends Region{

	public HSpacer() {
		HBox.setHgrow(this, Priority.ALWAYS);
	}
}
