package org.fxone.ui.rt.components.perspectives;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

import javax.inject.Named;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.view.ViewContainer;
import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
import org.fxone.ui.rt.components.workbench.NavigationTree;

@Named("split")
public class SplitPaneMenuPerspective extends AbstractFXMLComponent implements
		Perspective {

	private Workbench workbench;

	private ViewContainer pageContainer;

	@FXML
	private AnchorPane viewLeft;

	@FXML
	private AnchorPane viewRight;

	@FXML
	private ToolBar toolbar;

	public SplitPaneMenuPerspective() {
		super(
				"/org/fxone/ui/rt/components/perspectives/SplitPaneMenuPerspective.fxml");
		setId("SplitPaneMenuPerspective");
		NavigationTree tree = new NavigationTree();
		viewLeft.getChildren().add(tree);
		AnchorPane.setLeftAnchor(tree, 0.0d);
		AnchorPane.setRightAnchor(tree, 0.0d);
		AnchorPane.setTopAnchor(tree, 0.0d);
		AnchorPane.setBottomAnchor(tree, 0.0d);

//		pageContainer = Container.getInstance(ViewContainer.class);
//		viewRight.getChildren().add((Node) pageContainer);
//		AnchorPane.setLeftAnchor(viewRight, 0.0d);
//		AnchorPane.setRightAnchor(viewRight, 0.0d);
//		AnchorPane.setTopAnchor(viewRight, 0.0d);
//		AnchorPane.setBottomAnchor(viewRight, 0.0d);
		ToolbarConfigurator.configure(toolbar);
	}

	@Override
	public void activated(Workbench workbench) {
		this.workbench = workbench;
//		pageContainer.setEnabled(true);
	}

	@Override
	public void deactivated(Workbench owner) {
//		((Node) pageContainer).setEnabled(false);
		this.workbench = null;
	}

	public Workbench getWorkbench() {
		return this.workbench;
	}

}
