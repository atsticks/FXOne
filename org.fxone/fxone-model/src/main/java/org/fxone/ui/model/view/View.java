package org.fxone.ui.model.view;





public interface View{

	String getName();
	
	void init(ViewContext viewContext);
	
	void opened();
	
	boolean canClose();

	void closed();
	
}
