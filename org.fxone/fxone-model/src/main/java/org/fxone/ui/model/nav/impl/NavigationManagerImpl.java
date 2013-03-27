package org.fxone.ui.model.nav.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.ui.annot.ProvidedAreas;
import org.fxone.ui.annot.ProvidedCommands;
import org.fxone.ui.annot.ProvidedSeparators;
import org.fxone.ui.annot.UIArea;
import org.fxone.ui.annot.UICommand;
import org.fxone.ui.annot.UISeparator;
import org.fxone.ui.model.nav.Navigateable;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.Separator;

@Singleton
public class NavigationManagerImpl implements NavigationManager {

	private static final String DEFAULT_TREE = "default";

	private static final Logger LOGGER = Logger
			.getLogger(NavigationManagerImpl.class);

	private Map<String, NavigateableArea> trees = new ConcurrentHashMap<String, NavigateableArea>();

	@Inject
	public NavigationManagerImpl(Instance<Navigateable> navInstances) {
		this.trees.put(DEFAULT_TREE, new NavigationAreaImpl(DEFAULT_TREE));
		for (Navigateable nav : navInstances) {
			ProvidedCommands commandsSpec = (ProvidedCommands) nav.getClass()
					.getAnnotation(ProvidedCommands.class);
			ProvidedAreas areasSpec = (ProvidedAreas) nav.getClass()
					.getAnnotation(ProvidedAreas.class);
			ProvidedSeparators sepsSpec = (ProvidedSeparators) nav.getClass()
					.getAnnotation(ProvidedSeparators.class);
			if (commandsSpec == null && areasSpec == null && sepsSpec == null) {
				if (!nav.getClass().equals(Separator.class)) {
					LOGGER.info("Missing annotation '@ProvidedCommands', '@ProvidedAreas' or '@ProvidedSeparators' for action/area, ignoring: "
							+ nav.getClass());
				}
				continue;
			}
			if (commandsSpec != null) {
				for (UICommand cmdSpec : commandsSpec.value()) {
					try {
						NavigationAreaImpl parent = null;
						String[] parts = splitTarget(cmdSpec.value());
						parent = initParent(parts[0], cmdSpec.tree());
						NavigateableActionImpl cmd = new NavigateableActionImpl(
								parts[1], parent, (NavigateableAction) nav,
								cmdSpec.before(), cmdSpec.after());
						parent.addCommand(cmd);
					} catch (Exception e) {
						LOGGER.error("Error registering UI command: " + nav, e);
					}
				}
			}
			if (areasSpec != null) {
				for (UIArea areaSpec : areasSpec.value()) {
					try {
						NavigationAreaImpl parent = null;
						String[] parts = splitTarget(areaSpec.value());
						parent = initParent(parts[0], areaSpec.tree());
						NavigationAreaImpl areaImpl = new NavigationAreaImpl(
								parts[1], parent, (NavigateableArea) nav,
								areaSpec.before(), areaSpec.after());
						parent.addChild(areaImpl);
					} catch (Exception e) {
						LOGGER.error("Error registering UI area: " + nav, e);
					}
				}
			}
			if (sepsSpec != null) {
				for (UISeparator sepSpec : sepsSpec.value()) {
					try {
						NavigationAreaImpl parent = null;
						String[] parts = splitTarget(sepSpec.value());
						parent = initParent(parts[0], sepSpec.tree());
						parent.addChild(new NavigationSeparator(
								sepSpec.value(), parent, sepSpec.before(),
								sepSpec.after()));
					} catch (Exception e) {
						LOGGER.error("Error registering UI area: " + nav, e);
					}
				}
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
		return new String[] { parentPath, id };
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
		return this.trees.get(DEFAULT_TREE);
	}

	@Override
	public NavigateableArea getRootNavigation(String treeName) {
		return this.trees.get(treeName);
	}

}
