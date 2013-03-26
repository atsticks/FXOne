package org.fxone.ui.rt.components.workbench;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.workbench.cmd.SetWorkbenchStatus;
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
	public void notified(AbstractNotification notif) {
		if (notif.isMatching(SetWorkbenchStatus.class)) {
			SetWorkbenchStatus evt = (SetWorkbenchStatus) notif;
			setStatus(evt.getStatus());
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
