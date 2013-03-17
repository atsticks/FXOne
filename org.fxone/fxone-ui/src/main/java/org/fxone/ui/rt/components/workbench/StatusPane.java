package org.fxone.ui.rt.components.workbench;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.workbench.WorkbenchEvent;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

import com.sun.javafx.tk.Toolkit;

@Dependent
@Named("workbench-status")
@Default
public class StatusPane extends AbstractFXMLComponent implements
		NotificationListener {

	@FXML
	private AnchorPane statusPane;
	@FXML
	private Label statusLabel;

	public StatusPane() {
		super("/org/fxone/ui/rt/components/workbench/StatusPane.fxml");
		NotificationService.get().addListener(this);
	}

	@Override
	public void notified(Notification notif) {
		if (WorkbenchEvent.NOTIFTYPE_SETSTATUS.isMatching(notif)) {
			WorkbenchEvent evt = (WorkbenchEvent) notif;
			setStatus(evt.getValue());
		}
	}

	public void setStatus(final String status) {
		if (!Toolkit.getToolkit().isFxUserThread()) {
			Toolkit.getToolkit().defer(new Runnable() {
				@Override
				public void run() {
					statusLabel.setText("Status: " + status);
				}

			});
		} else {
			statusLabel.setText("Status: " + status);
		}
	}

	public String getStatus() {
		return statusLabel.getText();
	}

}
