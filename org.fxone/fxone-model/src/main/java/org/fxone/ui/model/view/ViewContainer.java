package org.fxone.ui.model.view;


public interface ViewContainer {

	public View getCurrentView();

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
	public boolean openView(View view);

	public boolean closeView(View view);

	public boolean closeAllViews();

	public View[] getViewsVisible();

}