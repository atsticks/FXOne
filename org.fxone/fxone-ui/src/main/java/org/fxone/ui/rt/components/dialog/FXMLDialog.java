package org.fxone.ui.rt.components.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import org.fxone.ui.model.dlog.cmd.Dialogs;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

public class FXMLDialog extends AbstractFXMLComponent<Node> implements
		org.fxone.ui.model.dlog.Dialog {
	@FXML
	protected Button yesButton;
	@FXML
	protected Label textLabel;
	@FXML
	protected AnchorPane contentPane;
	@FXML
	protected HBox buttonLayout;

	protected String title;

	public FXMLDialog(String id, String title) {
		super(id);
		this.title = title;
		getUI().setOpacity(1);
		// title
		initButtons();
	}

	public String getTitle() {
		return title;
	}

	protected void initButtons() {
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// hide dialog
				if (ok()) {
					Dialogs.closeDialog(FXMLDialog.this);
				}
			}
		});
	}

	/**
	 * @return the okBtn
	 */
	public final Button getOkBtn() {
		return yesButton;
	}

	public final String getMessage() {
		return textLabel.getText();
	}

	public final void setPrompt(String title) {
		this.textLabel.setText(title);
	}

	/**
	 * @return the content
	 */
	public final AnchorPane getContent() {
		return contentPane;
	}

	/**
	 * @return the buttonLayout
	 */
	public final HBox getButtonLayout() {
		return buttonLayout;
	}

	@Override
	public boolean canClose() {
		return true;
	}

	@Override
	public boolean closed(boolean forced) {
		return true;
	}

}
