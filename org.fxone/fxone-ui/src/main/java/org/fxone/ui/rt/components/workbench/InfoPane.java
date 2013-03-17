package org.fxone.ui.rt.components.workbench;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.workbench.WorkbenchEvent;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
import org.fxone.ui.rt.components.api.WorkbenchInfo;

import com.sun.javafx.tk.Toolkit;

@Dependent
@Named("info-pane")
@Default
public class InfoPane extends AbstractFXMLComponent implements
		org.fxone.core.events.NotificationListener, WorkbenchInfo {
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
	public void notified(Notification notif) {
		// TODO
//		if (SelectionEvent.NOTIFTYPE_SET_SELECTION.isMatching(notif)) {
//			SelectionEvent selEvt = notif.getAdapter(SelectionEvent.class);
//			this.selectedItem = selEvt.getSelection();
//			showInfo();
//			notif.setHandledBy(this);
//		}
//		else 
			if (WorkbenchEvent.NOTIFTYPE_SETINFO.isMatching(notif)) {
			WorkbenchEvent evt = (WorkbenchEvent)notif;
			setInfo(evt.getValue());
			notif.setCompleted();
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

	@Override
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

	@Override
	public String getInfo() {
		return noInfoLabel.getText();
	}

}
