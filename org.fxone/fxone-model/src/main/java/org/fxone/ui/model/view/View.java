package org.fxone.ui.model.view;

import org.fxone.ui.model.view.cmd.ViewContext;




public interface View<T>{

	String getName();
	
	void init(ViewContext viewContext);
	
	void opened();
	
	boolean canClose();

	void closed();
	
	public T getUI();

}
