package org.fxone.ui.rt.components.view;

import javafx.scene.Node;

import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContext;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

public class AbstractFXMLView<T extends Node> extends AbstractFXMLComponent<T>
		implements View {

	private ViewContext viewContext;

	public AbstractFXMLView(String fxmlResource) {
		super(fxmlResource);
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

	@Override
	public String getViewContainerID() {
		return null;
	}

	protected final ViewContext getViewContext() {
		return this.viewContext;
	}
}
