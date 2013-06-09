package org.fxone.ui.rt.components.perspectives;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import javax.inject.Named;
import javax.inject.Singleton;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.view.ViewContainer;
import org.fxone.ui.model.workbench.Perspective;
import org.fxone.ui.model.workbench.Workbench;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

@Singleton
@Named("default")
public class DefaultMenuPerspective extends AbstractFXMLComponent implements
		Perspective {

	private Workbench workbench;
	
	private ViewContainer pageContainer;

	@FXML
	private BorderPane perspectiveRoot;

	@FXML
	private MenuBar menubar;
	
	@FXML
	private ToolBar toolbar;

	public DefaultMenuPerspective() {
		super("/org/fxone/ui/rt/components/perspectives/DefaultMenuPerspective.fxml");
		setId("defaultMenuPerspective");
		pageContainer = Container.getInstance(ViewContainer.class);
		perspectiveRoot.setCenter((Node)pageContainer);
		ToolbarConfigurator.configure(toolbar);
	}

	@Override
	public void activated(Workbench workbench) {
		this.workbench = workbench;
//		pageContainer.setEnabled(true);
	}

	@Override
	public void deactivated(Workbench owner) {
//		pageContainer.setEnabled(false);
		this.workbench = null;
	}

	public Workbench getWorkbench(){
		return this.workbench;
	}

}
