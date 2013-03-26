package org.fxone.ui.model.nav.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.ui.annot.UINavigationArea;
import org.fxone.ui.annot.UINavigationCommand;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.NavigateableAction;

@Singleton
public class NavigationManagerImpl implements NavigationManager {

	private static final Logger LOGGER = Logger
			.getLogger(NavigationManagerImpl.class);

	private Map<String, NavigateableArea> trees = new ConcurrentHashMap<String, NavigateableArea>();

	@Inject
	public NavigationManagerImpl(Instance<NavigateableAction> commandInstances) {
		this.trees.put("default", new NavigationAreaImpl("default"));
		for (NavigateableAction actionCommand : commandInstances) {
			try {
				UINavigationCommand commandSpec = (UINavigationCommand) actionCommand
						.getClass().getAnnotation(UINavigationCommand.class);
				UINavigationArea areaSpec = (UINavigationArea) actionCommand
						.getClass().getAnnotation(UINavigationArea.class);
				if (commandSpec == null && areaSpec==null) {
					LOGGER.warn("Missing annotation '@UINavigationCommand' or '@UINavigationArea' for action, ignoring: "
							+ actionCommand.getClass());
					continue;
				}
				NavigationAreaImpl parent = null;
				if (commandSpec != null) {
					String[] parts = splitTarget(commandSpec.value());
					parent = initParent(parts[0], commandSpec.tree());
					NavigateableActionImpl cmd = new NavigateableActionImpl(parts[1], parent,
							actionCommand);
					parent.addCommand(cmd);
				}
				else if (areaSpec != null) {
					String[] parts = splitTarget(areaSpec.value());
					parent = initParent(parts[0], areaSpec.tree());
					NavigationAreaImpl areaImpl = new NavigationAreaImpl(parts[1],
							parent, actionCommand);
					parent.addChildArea(areaImpl);
				}
			} catch (Exception e) {
				LOGGER.error("Error registering navigation action: "
						+ actionCommand, e);
			}
		}
	}

	private String[] splitTarget(String target) {
		int index = target.lastIndexOf('/');
		String id = null;
		String parentPath = null;
		NavigationAreaImpl parent = null;
		if (index >= 0) {
			parentPath = target.substring(0, index);
			id = target.substring(index + 1);
		} else {
			parentPath = "";
			id = target;
		}
		return new String[]{parentPath, id};
	}

	private NavigationAreaImpl initParent(String parentPath, String tree) {
		NavigationAreaImpl parent = null;
		if (tree == null || tree.isEmpty()) {
			parent = (NavigationAreaImpl) getRootNavigation();
		} else {
			parent = (NavigationAreaImpl) getRootNavigation(tree);
			if (parent == null) {
				parent = new NavigationAreaImpl(tree);
				this.trees.put(tree, parent);
			}
		}

		if (parentPath != null && !parentPath.isEmpty()) {
			parent = parent.resolveOrCreateArea(parentPath);
		}
		return parent;
	}

	@Override
	public NavigateableArea getRootNavigation() {
		return this.trees.get("default");
	}

	@Override
	public NavigateableArea getRootNavigation(String treeName) {
		return this.trees.get(treeName);
	}

}
