package org.fxone.ui.rt.components.footer;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.workbench.WorkbenchEvent;

@Dependent
@Named("footer")
@Default
public class Footer extends HBox implements NotificationListener {

	private StatusLine statusLine;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	@Inject
	public Footer(@Named("status-line") Node statusLine) {
		setId("footer");
		getChildren().add(statusLine);
		setFillHeight(false);
		setSpacing(3);
		NotificationService.get().addListener(this);
	}

	protected void finalize() throws Throwable {
		NotificationService.get().removeListener(this);
	};

	public void setStatus(String status) {
		this.statusLine.setStatus(status);
	}

	public String getStatus() {
		return this.statusLine.getStatus();
	}

	@Override
	public void notified(Notification notif) {
		if (WorkbenchEvent.NOTIFTYPE_SETSTATUS.isMatching(notif)) {
			WorkbenchEvent cmd = (WorkbenchEvent) notif;
			setStatus(cmd.getValue());
		} else if (WorkbenchEvent.NOTIFTYPE_GETSTATUS
				.isMatching(notif)) {
			((WorkbenchEvent) notif).setResult(getStatus());
		}
		notif.setCompleted();
	}

}
