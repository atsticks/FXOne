package org.fxone.ui.model.nav;

import org.fxone.core.types.Identifiable;

public interface Navigateable extends Identifiable, Comparable<Navigateable> {

	public String getPlacedAfter();

	public String getPlacedBefore();

}
