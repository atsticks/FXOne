package org.fxone.ui.model.nav.impl;

import org.fxone.core.types.AbstractIdentifiable;
import org.fxone.ui.model.nav.NavigationArea;
import org.fxone.ui.model.nav.UIAction;
import org.fxone.ui.model.nav.UICommand;

public class UICommandImpl extends AbstractIdentifiable implements
		UICommand {

	protected NavigationArea parent;

	private String perspective;
	
	private UIAction delegate;

	public UICommandImpl(String id, NavigationArea parent, UIAction delegate) {
		super(id);
		this.parent = parent;
		this.delegate = delegate;
	}
	
	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}

	public String getPath() {
		if (parent == null) {
			return "<ROOT>";
		}
		return parent.getPath() + '/' + getIdentifier();
	}

	public NavigationArea getParent() {
		return this.parent;
	}

	/**
	 * Access the UI style of this node. The result is the style as set on this
	 * node, or the style returned by its parent. if the instance does not have
	 * a parent and its style is <code>null</code>, "default" is returned.
	 * 
	 * @return The style of this node.
	 */
	public String getPerspective() {
		return perspective;
	}

	public boolean isEnabled() {
		return delegate.isEnabled();
	}

	@Override
	public String toString() {
		return "NavigationCommand [" + getPath() + "]";
	}

	public void run() {
		this.delegate.run();
	}

}
