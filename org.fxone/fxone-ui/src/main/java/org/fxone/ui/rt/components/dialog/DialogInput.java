package org.fxone.ui.rt.components.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class DialogInput extends AbstractFXMLDialog {
	@FXML
	private TextField valueField;

	@FXML
	private Label questionField;

	@FXML
	private Button okButton;

	@FXML
	private HBox buttonLayout;

	private Runnable okAction;

	public DialogInput(String title, String question) {
		super("DialogInput", title);
		setPrompt(question);
	}

	private void setPrompt(String question) {
		questionField.setText(question);
	}

	public DialogInput(String question) {
		super("DialogInput", "INPUT");
		setPrompt(question);
	}

	public void setOkAction(Runnable r) {
		this.okAction = r;
	}

	public boolean ok() {
		if (okAction != null) {
			okAction.run();
		}
		return true;
	}

	public String getValue() {
		return this.valueField.getText();
	}

	public void setValue(String val) {
		this.valueField.setText(val);
	}

	@Override
	protected void initButtons() {
		super.initButtons();
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				closeImmedeately();
			}
		});
		buttonLayout.getChildren().add(cancelButton);
		okButton.setText("Save");
	}

}
