package org.fxone.ui.model.nav.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.ui.annot.UINavigation;
import org.fxone.ui.model.nav.NavigationArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.nav.UIAction;

@Singleton
public class NavigationManagerImpl implements NavigationManager {

	private static final Logger LOGGER = Logger
			.getLogger(NavigationManagerImpl.class);

	private Map<String, NavigationArea> trees = new ConcurrentHashMap<String, NavigationArea>();

	@Inject
	public NavigationManagerImpl(Instance<UIAction> commandInstances) {
		this.trees.put("default", new NavigationAreaImpl("default"));
		for (UIAction actionCommand : commandInstances) {
			try {
				UINavigation navSpec = (UINavigation) actionCommand.getClass()
						.getAnnotation(UINavigation.class);
				if (navSpec == null) {
					LOGGER.warn("Missing annotation '@UINavigation' for action, ignoring: "
							+ actionCommand.getClass());
					continue;
				}
				int index = navSpec.target().lastIndexOf('/');
				String id = null;
				String parentPath = null;
				if(index>=0){
					parentPath = navSpec.target().substring(0, index);
					id =  navSpec.target().substring(index+1);
				}
				else{
					parentPath = "";
					id =  navSpec.target();
				}
				String tree = navSpec.tree();
				String perspective = navSpec.perspective();
				int prio = navSpec.priority();
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
				if (navSpec.area()) {
					NavigationAreaImpl areaImpl = new NavigationAreaImpl(id, parent, actionCommand);
					areaImpl.setPerspective(perspective);
					parent.addChildArea(areaImpl);
				}
				else{
					UICommandImpl cmd = new UICommandImpl(id, parent, actionCommand);
					cmd.setPerspective(perspective);
					parent.addCommand(cmd);
				}
			} catch (Exception e) {
				LOGGER.error("Error registering navigation command: "
						+ actionCommand, e);
			}
		}
	}

	@Override
	public NavigationArea getRootNavigation() {
		return this.trees.get("default");
	}

	@Override
	public NavigationArea getRootNavigation(String treeName) {
		return this.trees.get(treeName);
	}

}
