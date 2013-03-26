package org.fxone.ui.model.workbench.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;

public class SetWorkbenchStatus extends AbstractNotification<Void> {

	public static final NotificationType NOTIFTYPE_SETSTATUS = new NotificationType.Builder(
			"UI", "Workbench:setStatus", "Sets the current workbench status info.")
			.define(SetWorkbenchStatus.class).buildAndRegister();

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6956469546415671688L;

	private String status;
	private String info;

	public SetWorkbenchStatus() {
		super(DefaultGroups.UI);
	}

	public String getStatus() {
		return status;
	}
	
	public String getInfo() {
		return info;
	}

	public void setStatus(String status) {
		checkReadOnly();
		if (status == null) {
			throw new IllegalArgumentException("status is required.");
		}
		this.status = status;
	}
	
	public void setInfo(String info) {
		checkReadOnly();
		if (info == null) {
			throw new IllegalArgumentException("info is required.");
		}
		this.info = info;
	}
}
