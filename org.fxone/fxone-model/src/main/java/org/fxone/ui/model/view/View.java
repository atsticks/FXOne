package org.fxone.ui.model.view;

import org.fxone.core.types.Identifiable;


public interface View extends Identifiable{

	public String getViewContainerID();
	
	void init(ViewContext viewContext);
	
	void opened();
	
	boolean canClose();

	void closed();

}
