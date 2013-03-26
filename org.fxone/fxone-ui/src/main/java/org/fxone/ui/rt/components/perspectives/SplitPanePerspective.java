package org.fxone.ui.rt.components.perspectives;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javax.inject.Named;

import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
import org.fxone.ui.rt.components.view.SingleViewPageContainer;
import org.fxone.ui.rt.components.workbench.NavigationTree;

@Named("default")
public class SplitPanePerspective extends AbstractFXMLComponent implements
		Perspective {

	private Workbench workbench;
	private SingleViewPageContainer pageContainer;

	@FXML
	private AnchorPane splitPanePerspectiveLeft;

	@FXML
	private AnchorPane splitPanePerspectiveRight;

	public SplitPanePerspective() {
		super("/org/fxone/ui/rt/components/perspectives/SplitPanePerspective.fxml");
		setId("SplitPanePerspective");
		NavigationTree tree = new NavigationTree();
		splitPanePerspectiveLeft.getChildren().add(tree);
		AnchorPane.setLeftAnchor(tree, 0.0d);
		AnchorPane.setRightAnchor(tree, 0.0d);
		AnchorPane.setTopAnchor(tree, 0.0d);
		AnchorPane.setBottomAnchor(tree, 0.0d);
		pageContainer = new SingleViewPageContainer();
		splitPanePerspectiveRight.getChildren().add(pageContainer);
		AnchorPane.setLeftAnchor(splitPanePerspectiveRight, 0.0d);
		AnchorPane.setRightAnchor(splitPanePerspectiveRight, 0.0d);
		AnchorPane.setTopAnchor(splitPanePerspectiveRight, 0.0d);
		AnchorPane.setBottomAnchor(splitPanePerspectiveRight, 0.0d);
	}

	@Override
	public void activated(Workbench workbench) {
		this.workbench = workbench;
		pageContainer.setEnabled(true);
	}

	@Override
	public void deactivated(Workbench owner) {
		pageContainer.setEnabled(false);
		this.workbench = null;
	}

	public Workbench getWorkbench(){
		return this.workbench;
	}

}
