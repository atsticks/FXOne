package org.fxone.ui.model.view;




public interface View<T>{

	void init(ViewContext viewContext);
	
	void opened();
	
	boolean canClose();

	void closed();
	
	public T getUI();
	
}
