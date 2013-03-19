package org.fxone.ui.model.view;


public interface ViewContainer<T> {

	public void setEnabled(boolean enabled);
	
	public abstract View<T> getCurrentView();

	/**
	 * Take ensemble to the given page object, navigating there.
	 * 
	 * @param view
	 *            Page object to show
	 * @param addHistory
	 *            When true add current page to history before navigating
	 * @param force
	 *            When true reload page if page is current page
	 * @param swapViews
	 *            If view should be swapped to new page
	 */
	public abstract boolean openView(View<T> view);

	public abstract T getUI();

	public abstract boolean closeView(View<T> view);

	public abstract boolean closeAllViews();

}