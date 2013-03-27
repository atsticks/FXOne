package org.fxone.ui.model.nav.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.fxone.ui.model.nav.Navigateable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;

public class NavigationAreaImpl extends AbstractNavigateable implements
		NavigateableArea {

	protected NavigateableArea parent;

	protected String cachedPath;

	private NavigateableAction delegate;

	private List<Navigateable> children = new ArrayList<Navigateable>();
	private Map<String, NavigateableArea> childAreas = new HashMap<String, NavigateableArea>();
	private Map<String, NavigateableAction> commands = new HashMap<String, NavigateableAction>();

	public NavigationAreaImpl(String id, NavigationAreaImpl parent) {
		this(id, parent, null);
	}

	public NavigationAreaImpl(String id) {
		this(id, null, null);
	}

	public NavigationAreaImpl(String id, NavigateableAction action) {
		this(id, null, action);
	}

	public NavigationAreaImpl(String id, NavigateableArea parent,
			NavigateableAction action) {
		this(id, parent, action, null, null);
	}

	public NavigationAreaImpl(String id, NavigateableArea parent,
			NavigateableAction action, String before, String after) {
		super(id);
		this.parent = parent;
		this.delegate = action;
		setPlacedBefore(before);
		setPlacedAfter(after);
	}

	public String getPath() {
		if (cachedPath == null) {
			if (parent != null) {
				cachedPath = ((NavigateableActionImpl) parent).getPath() + '/'
						+ getIdentifier();
			} else {
				cachedPath = getIdentifier();
			}
		}
		return cachedPath;
	}

	public boolean isLeaf() {
		return this.childAreas == null || this.childAreas.isEmpty();
	}

	public NavigateableArea getChildArea(String path) {
		if (childAreas == null) {
			return null;
		}
		NavigateableArea current = this;
		String[] paths = path.split("/");
		if (paths.length == 1) {
			return this.childAreas.get(paths[0]);
		}
		for (int i = 0; i < paths.length; i++) {
			current = current.getChildArea(paths[i]);
			if (current == null) {
				break;
			}
		}
		if (current == null) {
			throw new IllegalArgumentException("Path not found: " + path);
		}
		return current;
	}

	public NavigateableAction getCommand(String path) {
		if (childAreas == null) {
			return null;
		}
		NavigateableArea current = this;
		NavigateableAction action = null;
		String[] paths = path.split("/");
		for (int i = 0; i < paths.length; i++) {
			if (i == (paths.length - 1)) {
				action = current.getCommand(paths[i]);
			}
			current = current.getChildArea(paths[i]);
			if (current == null) {
				break;
			}
		}
		if (action == null) {
			throw new IllegalArgumentException("Command not found: " + path);
		}
		return action;
	}

	public Navigateable removeChild(String name) {
		if (childAreas == null) {
			return null;
		}
		NavigationAreaImpl child = (NavigationAreaImpl) getChildArea(name);
		if (child != null) {
			this.childAreas.remove(child);
			child.parent = null;
		}
		NavigateableAction command = (NavigateableAction) getCommand(name);
		if (command != null) {
			this.commands.remove(command);
			child.parent = null;
		}
		for (Navigateable childNav : children) {
			if (childNav.getIdentifier().equals(name)) {
				children.remove(childNav);
				return childNav;
			}
		}
		return null;
	}

	public void addChild(Navigateable nav) {
		this.children.add(nav);
		Collections.sort(this.children);
		if (nav instanceof NavigateableArea) {
			this.childAreas.put(nav.getIdentifier(), (NavigateableArea) nav);
		} else if (nav instanceof NavigateableAction) {
			this.commands.put(nav.getIdentifier(), (NavigateableAction) nav);
		}
	}

	public Enumeration<NavigateableArea> getChildAreas() {
		List<NavigateableArea> areas = new ArrayList<NavigateableArea>();
		for (Navigateable nav : children) {
			if (nav instanceof NavigateableArea) {
				areas.add((NavigateableArea) nav);
			}
		}
		return Collections.enumeration(areas);
	}

	@Override
	public String toString() {
		return "Area: " + getIdentifier();
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public boolean isRunnable() {
		return this.delegate != null;
	}

	public Enumeration<NavigateableAction> getCommands() {
		if (commands == null) {
			return Collections.emptyEnumeration();
		}
		return Collections.enumeration(commands.values());
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

	public NavigateableArea getParent() {
		return this.parent;
	}

	public void addCommand(NavigateableActionImpl cmd) {
		if (commands == null) {
			commands = new ConcurrentHashMap<String, NavigateableAction>();
		}
		commands.put(cmd.getIdentifier(), cmd);
		this.children.add(cmd);
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
			NavigationAreaImpl child = (NavigationAreaImpl) curNode
					.getChildArea(path);
			if (child == null) {
				child = new NavigationAreaImpl(path, curNode);
				curNode.addChild(child);
			}
			curNode = child;
		}
		return curNode;
	}

	@Override
	public Enumeration<Navigateable> getItems() {
		return Collections.enumeration(children);
	}

}
