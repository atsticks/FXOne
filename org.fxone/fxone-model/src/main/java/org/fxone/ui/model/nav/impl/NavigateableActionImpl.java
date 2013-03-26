package org.fxone.ui.model.nav.impl;

import org.fxone.core.types.AbstractIdentifiable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;

public class NavigateableActionImpl extends AbstractIdentifiable implements
		NavigateableAction {

	protected NavigateableArea parent;

	private NavigateableAction delegate;

	public NavigateableActionImpl(String id, NavigateableArea parent,
			NavigateableAction delegate) {
		super(id);
		this.parent = parent;
		this.delegate = delegate;
	}

	public String getPath() {
		if (parent == null) {
			return "<ROOT>";
		}
		return ((NavigateableActionImpl)parent).getPath() + '/' + getIdentifier();
	}

	public NavigateableArea getParent() {
		return this.parent;
	}

	public boolean isEnabled() {
		return delegate.isEnabled();
	}

	@Override
	public String toString() {
		return "Command: " + getIdentifier();
	}

	public void run() {
		this.delegate.run();
	}

}
