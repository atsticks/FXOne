package org.fxone.ui.rt.components.footer;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Dependent
@Default
@Named("status-line")
public class StatusLine extends HBox {

	private Label statusLabel = new Label("Status: ");
	private Label statusValue = new Label("Initializing...");

	public StatusLine() {
		statusLabel.setTooltip(new Tooltip("Shows the current status."));
		getChildren().addAll(statusLabel, statusValue);
	}

	public void setStatus(String status) {
		this.statusValue.setText(status);
	}

	public String getStatus() {
		return this.statusValue.getText();
	}
}
