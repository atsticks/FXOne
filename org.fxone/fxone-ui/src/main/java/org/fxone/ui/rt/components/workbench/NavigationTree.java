package org.fxone.ui.rt.components.workbench;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;

public class NavigationTree extends StackPane {

	private static Image FOLDERIMAGE = new Image(NavigationTree.class
			.getResourceAsStream("/img/16x16/folder_open_16x16.gif"));
	private String treeID;
	private TreeView<NavigateableAction> tree;
	private NavigationTreeItem root = null;

	public NavigationTree(String treeID) {
		this.treeID = treeID;
		init();
	}
	
	public NavigationTree() {
		this(null);
	}

	public void setTree(String treeId){
		NavigationManager man = Container.getInstance(NavigationManager.class);
		NavigateableArea current;
		if (treeID == null) {
			current = man.getRootNavigation();
		} else {
			current = man.getRootNavigation(treeID);
		}
		root = new NavigationTreeItem(current);
		tree = new TreeView<NavigateableAction>(root);
		getChildren().clear();
		getChildren().add(tree);
	}
	
	private void init() {
		NavigationManager man = Container.getInstance(NavigationManager.class);
		NavigateableArea current;
		if (treeID == null) {
			current = man.getRootNavigation();
		} else {
			current = man.getRootNavigation(treeID);
		}
		root = new NavigationTreeItem(current);
		tree = new TreeView<NavigateableAction>(root);
		getChildren().add(tree);
	}

	private static final class NavigationTreeItem extends
			TreeItem<NavigateableAction> {

		public NavigationTreeItem(NavigateableAction command, Node node) {
			super(command, node);
		}
		
		public NavigationTreeItem(NavigateableAction command) {
			super(command);
		}

		public NavigationTreeItem(NavigateableArea navItem) {
			super(navItem, new ImageView(FOLDERIMAGE));
			initChildren();
		}

		public NavigationTreeItem(NavigateableArea navItem, Node node) {
			super(navItem, node);
			initChildren();
		}
		
		private void initChildren() {
			for(NavigateableArea area: ((NavigateableArea)getValue()).getChildAreas()){
				getChildren().add(new NavigationTreeItem(area));
			}
			for(NavigateableAction command: ((NavigateableArea)getValue()).getCommands()){
				getChildren().add(new NavigationTreeItem(command));
			}
		}

		@Override
		public boolean isLeaf() {
			if(this.getValue() instanceof NavigateableArea){
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return this.getValue().getIdentifier();
		}
	}
}
