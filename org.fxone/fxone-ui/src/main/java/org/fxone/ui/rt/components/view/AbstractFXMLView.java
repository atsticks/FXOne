package org.fxone.ui.rt.components.view;

import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.cmd.ViewContext;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

public class AbstractFXMLView extends AbstractFXMLComponent
		implements View {

	private ViewContext viewContext;

	public AbstractFXMLView() {
		super();
	}
	
	public AbstractFXMLView(String fxmlResource) {
		super(fxmlResource);
	}
	
	public AbstractFXMLView(String fxmlResource, String resourceBundle) {
		super(fxmlResource, resourceBundle);
	}

	@Override
	public void opened() {
	}

	@Override
	public boolean canClose() {
		return true;
	}

	@Override
	public void closed() {
	}

	@Override
	public void init(ViewContext ctx) {
		this.viewContext = ctx;
	}

	protected final ViewContext getViewContext() {
		return this.viewContext;
	}
	
	public Node getUI(){
		return this;
	}
}