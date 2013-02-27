package org.fxone.ui.rt.components.layout;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class VSpacer extends Region{

	public VSpacer() {
		VBox.setVgrow(this, Priority.ALWAYS);
	}
}
