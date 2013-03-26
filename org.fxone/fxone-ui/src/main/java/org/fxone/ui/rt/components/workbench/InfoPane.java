package org.fxone.ui.rt.components.workbench;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.workbench.cmd.SetWorkbenchStatus;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

import com.sun.javafx.tk.Toolkit;

@Dependent
@Named("info-pane")
@Default
public class InfoPane extends AbstractFXMLComponent implements
		org.fxone.core.events.NotificationListener {
	@FXML
	private AnchorPane infoPane;

	@FXML
	private Label noInfoLabel;

	private Object selectedItem;

	public InfoPane() {
		super("/org/fxone/ui/rt/components/workbench/InfoPane.fxml");
		NotificationService.get().addListener(this);
	}

	@Override
	public void notified(AbstractNotification event) {
		if (event.isMatching(SetWorkbenchStatus.class)) {
			SetWorkbenchStatus evt = (SetWorkbenchStatus) event;
			setInfo(evt.getInfo());
		}
	}

	private void showInfo() {
		// TODO enhance this...
		if (selectedItem != null) {
			setInfo("Selected: " + selectedItem.toString());
		} else {
			setInfo("No information available.");
		}
	}

	public void setInfo(final String info) {
		if (!Toolkit.getToolkit().isFxUserThread()) {
			Toolkit.getToolkit().defer(new Runnable() {
				@Override
				public void run() {
					noInfoLabel.setText(info);
				}

			});
		} else {
			noInfoLabel.setText(info);
		}
	}

	public String getInfo() {
		return noInfoLabel.getText();
	}

}
