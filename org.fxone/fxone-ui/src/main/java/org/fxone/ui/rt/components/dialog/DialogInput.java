package org.fxone.ui.rt.components.dialog;



import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.New;
import javax.inject.Named;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import org.fxone.ui.model.dlog.DialogManager;
import org.fxone.ui.model.dlog.cmd.Dialogs;

@New
@Default
@Named("input-dialog")
public class DialogInput extends FXMLDialog {

	private TextField valueField;
	private Runnable okAction;
	
	public DialogInput(String title, String question) {
		super("DialogInput", title);
		setPrompt(question);
	}
	
	public DialogInput(String question) {
		super("DialogInput", "INPUT");
		setPrompt(question);
	}
	
	public void setOkAction(Runnable r) {
		this.okAction = r;
	}
	
	@Override
	public boolean ok() {
		if (okAction != null) {
			okAction.run();
		}
		return true;
	}

	public String getValue(){
		return this.valueField.getText();
	}
	
	public void setValue(String val){
		this.valueField.setText(val);
	}
	
	@Override
	protected void initButtons() {
		super.initButtons();
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Dialogs.closeDialog(DialogInput.this);
			}
		});
		getButtonLayout().getChildren().add(cancelButton);
		getOkBtn().setText("Save");
	}

	public static String open(String message) {
		DialogInput dlog = new DialogInput(message);
		dlog.setPrompt(message);
		dMan.openDialog(dlog);
		return dlog.getValue();
	}
	
	public static String open(String title, String message) {
		DialogInput dlog = new DialogInput(title, message);
		dlog.setPrompt(message);
		dMan.openDialog(dlog);
		return dlog.getValue();
	}

}
