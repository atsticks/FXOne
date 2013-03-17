package org.fxone.ui.model.view;



public interface ViewContainer{

	boolean isViewOpened(String id);

	boolean isViewVisible(String id);

	View<?> getCurrentView();

	boolean closeView(String id);
	
	boolean closeView(View<?> view);

	boolean closeAllViews();

	View<?>[] getViewsOpened();

	/**
	 * Take ensemble to the given page path, navigating there and adding current
	 * page to history.
	 * 
	 * @param pagePath
	 *            The path for the new page to show
	 * @param addHistory
	 *            When true add current page to history before navigating
	 * @param force
	 *            Reload page even if its the current page
	 */
	View<?> getView(String id);
	
	boolean openView(View<?> view);
	
	boolean showView(String id);
	
	View<?>[] getViewsVisible();
	
}

