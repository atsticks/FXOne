package org.fxone.ui.rt.components.dialog;

public interface Dialog {

	public void init(DialogContext context);

	public void beforeOpen(DialogContainer owner);

}
