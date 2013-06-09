package org.fxone.ui.model.nav;

import org.fxone.ui.model.nav.impl.AbstractNavigateable;

public final class Separator extends AbstractNavigateable{

	public Separator(String id) {
		super(id);
	}
	
	public Separator() {
		super("<separator>");
	}

}
