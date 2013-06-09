package org.fxone.ui.model.nav.impl;

import org.fxone.core.types.AbstractIdentifiable;
import org.fxone.ui.model.nav.Navigateable;

public abstract class AbstractNavigateable extends AbstractIdentifiable
		implements Navigateable, Comparable<Navigateable> {

	private String placedBefore;
	private String placedAfter;

	public AbstractNavigateable(String id) {
		super(id);
	}

	@Override
	public String getPlacedAfter() {
		return this.placedAfter;
	}

	@Override
	public String getPlacedBefore() {
		return this.placedBefore;
	}

	public void setPlacedAfter(String after) {
		this.placedAfter = after;
	}

	public void setPlacedBefore(String before) {
		this.placedBefore = before;
	}

	@Override
	public int compareTo(Navigateable o) {
		if (o == null) {
			return -1;
		}
		if (o.getIdentifier().equals(this.getPlacedBefore())) {
			return -1;
		}
		if (o.getIdentifier().equals(this.getPlacedAfter())) {
			return 1;
		}
		if (this.getIdentifier().equals(o.getPlacedBefore())) {
			return 1;
		}
		if (this.getIdentifier().equals(o.getPlacedAfter())) {
			return -1;
		}
		return 0;
	}

}
