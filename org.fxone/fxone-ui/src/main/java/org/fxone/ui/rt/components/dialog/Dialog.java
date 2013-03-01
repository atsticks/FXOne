package org.fxone.ui.rt.components.dialog;

import javafx.scene.Node;


public interface Dialog {

	public void init(DialogContext context);
	
	public Node getUI();
	
	public void beforeOpen(DialogContainer owner);

}
