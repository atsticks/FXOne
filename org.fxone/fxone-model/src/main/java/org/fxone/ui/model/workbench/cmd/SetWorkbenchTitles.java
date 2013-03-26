package org.fxone.ui.model.workbench.cmd;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.DefaultGroups;
import org.fxone.core.events.NotificationType;

public class SetWorkbenchTitles extends AbstractNotification<Void> {

	public static final NotificationType NOTIFTYPE_SET_TITLE = new NotificationType.Builder(
			"UI", "Workbench:setTitles", "Sets the current workbench's titles.")
			.define(SetWorkbenchTitles.class).buildAndRegister();

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6956469546415671688L;

	private String title;
	private String areaTitle;
	private String areaSubTitle;
	private String areaDescription;

	public SetWorkbenchTitles() {
		super(DefaultGroups.UI);
	}

	public String getTitle() {
		return title;
	}

	public String getAreaTitle() {
		return areaTitle;
	}

	public String getAreaSubTitle() {
		return areaSubTitle;
	}

	public String getAreaDescription() {
		return areaDescription;
	}

	public void setTitle(String title) {
		checkReadOnly();
		if (title == null) {
			this.title = "";
		} else {
			this.title = title;
		}
	}

	public void setAreaTitle(String areaTitle) {
		checkReadOnly();
		if (areaTitle == null) {
			this.areaTitle = "";
		} else {
			this.areaTitle = areaTitle;
		}
	}

	public void setAreaSubTitle(String areaSubTitle) {
		checkReadOnly();
		if (areaSubTitle == null) {
			this.areaSubTitle = "";
		} else {
			this.areaSubTitle = areaSubTitle;
		}
	}

	public void setAreaDescription(String areaDescription) {
		checkReadOnly();
		if (areaDescription == null) {
			this.areaDescription = "";
		} else {
			this.areaDescription = areaDescription;
		}
	}
}
