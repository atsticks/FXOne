package org.fxone.ui.rt.components.dialog;

public interface Dialog<T> {

	public void init(DialogContext context);

	public void beforeOpen(DialogContainer owner);

	public T getUI();

}
