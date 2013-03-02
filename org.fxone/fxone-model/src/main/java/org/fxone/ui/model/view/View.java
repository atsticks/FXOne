package org.fxone.ui.model.view;




public interface View{

	void init(ViewContext viewContext);
	
	void opened();
	
	boolean canClose();

	void closed();
	
}
