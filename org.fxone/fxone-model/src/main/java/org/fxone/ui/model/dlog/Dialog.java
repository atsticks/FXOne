package org.fxone.ui.model.dlog;

import org.fxone.core.types.Identifiable;


public interface Dialog extends Identifiable{

	String getTitle();
	public String getIcon16(); 
	public void init(DialogContext context);
	public void opened();
	public boolean closed(boolean forced);
	public boolean canClose();
	
}
