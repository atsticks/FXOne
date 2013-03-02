package org.fxone.ui.rt.components.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

import org.fxone.ui.rt.components.AbstractFXMLComponent;

public abstract class AbstractFXMLDialog extends AbstractFXMLComponent
		implements Dialog {

	protected String title;

	private DialogContext dialogContext;
	private DialogContainer dialogContainer;

	public AbstractFXMLDialog(String id, String title) {
		super(id);
		this.title = title;
		setOpacity(1);
		// title
		initButtons();
	}

	public String getTitle() {
		return title;
	}

	protected void initButtons() {
		Button button = new Button("Close"); // TODO i18n
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				// hide dialog
				if (canClose()) {
					closeImmedeately();
				}
			}
		});
	}

	protected void closeImmedeately() {
		getDialogContainer().hideDialog(this);
	}

	protected DialogContainer getDialogContainer() {
		return dialogContainer;
	}

	public boolean canClose() {
		return true;
	}

	public boolean closed(boolean forced) {
		return true;
	}

	@Override
	public void init(DialogContext dialogContext) {
		this.dialogContext = dialogContext;
	}

	@Override
	public void beforeOpen(DialogContainer dialogContainer) {
		this.dialogContainer = dialogContainer;
	}

	protected DialogContainer getOwner() {
		return this.dialogContainer;
	}
}
