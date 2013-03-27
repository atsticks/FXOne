package org.fxone.ui.rt.components.perspectives;

import javafx.scene.control.Label;

import javax.inject.Named;

import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.components.clocks.SimpleLabelClockAdapter;

@Named("clock")
public class ClockPerspective extends Label implements Perspective {

	private Workbench wb;

	public ClockPerspective() {
		setId("ClockPerspective");
		new SimpleLabelClockAdapter(this).start();
	}

	@Override
	public void activated(Workbench workbench) {
		this.wb = workbench;
	}

	@Override
	public void deactivated(Workbench owner) {
		this.wb = null;
	}

	public Workbench getWorkbench() {
		return this.wb;
	}

}
