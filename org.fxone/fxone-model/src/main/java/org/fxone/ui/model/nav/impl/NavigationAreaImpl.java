package org.fxone.ui.model.nav.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.fxone.core.types.AbstractIdentifiable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;

public class NavigationAreaImpl extends AbstractIdentifiable implements
		NavigateableArea {

	protected NavigateableArea parent;

	protected String cachedPath;

	private NavigateableAction delegate;

	private String perspective;

	private Map<String, NavigateableArea> childAreas;
	private Map<String, NavigateableAction> commands;

	public NavigationAreaImpl(String id, NavigationAreaImpl parent) {
		this(id, parent, null);
	}

	public NavigationAreaImpl(String id) {
		this(id, null, null);
	}

	public NavigationAreaImpl(String id, NavigateableAction action) {
		this(id, null, action);
	}

	public NavigationAreaImpl(String id, NavigateableArea parent, NavigateableAction action) {
		super(id);
		this.parent = parent;
		this.delegate = action;
	}

	public String getPath() {
		if (cachedPath == null) {
			if (parent != null) {
				cachedPath = parent.getPath() + '/' + getIdentifier();
			} else {
				cachedPath = getIdentifier();
			}
		}
		return cachedPath;
	}

	public boolean isLeaf() {
		return this.childAreas == null || this.childAreas.isEmpty();
	}

	public NavigateableArea getChildArea(String id) {
		if (childAreas == null) {
			return null;
		}
		return childAreas.get(id);
	}

	public NavigationAreaImpl removeChildArea(String name) {
		if (childAreas == null) {
			return null;
		}
		NavigationAreaImpl child = (NavigationAreaImpl) getChildArea(name);
		if (child != null) {
			this.childAreas.remove(child);
			child.parent = null;
		}
		return child;
	}

	public void addChildArea(NavigationAreaImpl cmd) {
		if (childAreas == null) {
			childAreas = new ConcurrentHashMap<String,NavigateableArea>();
		}
		childAreas.put(cmd.getIdentifier(), cmd);
	}

	public Collection<NavigateableArea> getChildAreas() {
		if (childAreas == null) {
			return Collections.emptySet();
		}
		return childAreas.values();
	}

	@Override
	public String toString() {
		return "Area: " +getIdentifier();
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public boolean isRunnable() {
		return this.delegate != null;
	}

	public Collection<NavigateableAction> getCommands() {
		if (commands == null) {
			return Collections.emptySet();
		}
		return commands.values();
	}

	public boolean isEnabled() {
		if (this.delegate == null) {
			return true;
		}
		return this.delegate.isEnabled();
	}

	public void run() {
		if (this.delegate != null) {
			this.delegate.run();
		}
	}

	@Override
	public NavigateableArea getParent() {
		return this.parent;
	}


	public void setPerspective(String perspective) {
		this.perspective = perspective;
	}

	public void addCommand(NavigateableAction cmd) {
		if (commands == null) {
			commands = new ConcurrentHashMap<String, NavigateableAction>();
		}
		commands.put(cmd.getIdentifier(), cmd);
	}
	
	NavigationAreaImpl resolveOrCreateArea(String name) {
		if (name.isEmpty()) {
			return this;
		}
		String[] paths = name.split("/");
		NavigationAreaImpl curNode = this;
		for (String path : paths) {
			if (path.isEmpty()) {
				continue;
			}
			NavigationAreaImpl child = (NavigationAreaImpl) curNode.getChildArea(path);
			if (child == null) {
				child = new NavigationAreaImpl(path, curNode);
				curNode.addChildArea(child);
			}
			curNode = child;
		}
		return curNode;
	}

}
