package org.fxone.ui.rt.components.workbench;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.nav.NavigateableAction;
import org.fxone.ui.model.nav.NavigateableArea;
import org.fxone.ui.model.nav.NavigationManager;

public class NavigationTree extends StackPane {

	private static Image FOLDERIMAGE = new Image(
			NavigationTree.class
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

	public void setTree(String treeId) {
		this.treeID = treeId;
		init();
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
		tree = new TreeView<NavigateableAction>();
		tree.setCellFactory(new Callback<TreeView<NavigateableAction>, TreeCell<NavigateableAction>>() {
			@Override
			public TreeCell<NavigateableAction> call(
					TreeView<NavigateableAction> p) {
				return new NavigationLink();
			}
		});
		tree.setRoot(root);
		getChildren().setAll(tree);
	}

	private static final class NavigationTreeItem extends
			TreeItem<NavigateableAction> {

		public NavigationTreeItem(NavigateableAction command, Node node) {
			super(command, node);
		}

		public NavigationTreeItem(final NavigateableAction command) {
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
			for (NavigateableArea area : ((NavigateableArea) getValue())
					.getChildAreas()) {
				getChildren().add(new NavigationTreeItem(area));
			}
			for (NavigateableAction command : ((NavigateableArea) getValue())
					.getCommands()) {
				getChildren().add(new NavigationTreeItem(command));
			}
		}

		@Override
		public boolean isLeaf() {
			if (this.getValue() instanceof NavigateableArea) {
				return false;
			}
			return true;
		}

		@Override
		public String toString() {
			return this.getValue().getIdentifier();
		}
	}

	private final class NavigationLink extends TreeCell<NavigateableAction> {

		private Hyperlink link = new Hyperlink("-");

		public NavigationLink() {
			link.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					if (getItem().isEnabled()) {
						link.setDisable(false);
						getItem().run();
					}
					else{
						link.setDisable(true);
					}
				}
			});
		}

		 @Override
		 public void startEdit() {
			super.startEdit();
		 	setText(getItem().getIdentifier());
		 	setGraphic(link);
		 }
		
		@Override
		public void cancelEdit() {
			super.cancelEdit();
			setText(getItem().getIdentifier());
			setGraphic(getTreeItem().getGraphic());
		}

		@Override
		public void updateItem(NavigateableAction item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText("-");
				setGraphic(null);
			} else {
				link.setText(getString());
				link.setGraphic(getTreeItem().getGraphic());
				setText(null);
				setGraphic(link);//getTreeItem().getGraphic());
			}
		}

		private String getString() {
			return getItem() == null ? "-" : getItem().getIdentifier();
		}
	}

}
