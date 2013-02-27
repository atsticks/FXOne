package org.fxone.ui.model.view;

import org.fxone.ui.model.AbstractUIComponent;

public abstract class AbstractView extends AbstractUIComponent implements View {

	private ViewContext viewContext;

	protected AbstractView(String id) {
		super(id);
	}

	@Override
	public void init(ViewContext viewContext) {
		this.viewContext = viewContext;
	}

	@Override
	public boolean canClose() {
		return true;
	}

	@Override
	public void opened() {
		// empty
	}

	@Override
	public void closed() {
		// empty
	}

	public String getViewContainerID(){
		return "default";
	}
	
	public ViewContext getViewContext() {
		return viewContext;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractView [viewContext=" + viewContext + "]";
	}
	
}
