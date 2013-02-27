package org.fxone.ui.model.view;

import org.fxone.ui.annot.UIComponent;


public interface ViewFactory {

	public void configure(String id, UIComponent annotation, Class<View> clazz);
	public View createView(String viewContainerID, Object... params);

}
