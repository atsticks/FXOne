package org.fxone.ui.model.nav.impl;

import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigateableSeparator;

public class NavigationSeparator extends AbstractNavigateable implements
		NavigateableSeparator {

	protected NavigateableArea parent;

	public NavigationSeparator(String id, NavigateableArea parent) {
		this(id, parent, null, null);
	}

	public NavigationSeparator(String id, NavigateableArea parent,
			String before, String after) {
		super(id);
		this.parent = parent;
		setPlacedAfter(after);
		setPlacedBefore(before);
	}

	public String getPath() {
		if (parent == null) {
			return "<ROOT>";
		}
		return ((NavigationSeparator) parent).getPath() + '/' + getIdentifier();
	}

	public NavigateableArea getParent() {
		return this.parent;
	}

	public boolean isEnabled() {
		return false;
	}

	@Override
	public String toString() {
		return "Separator: " + getIdentifier();
	}

	public void run() {
	}

}
