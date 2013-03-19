package org.fxone.ui.rt.components.view;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;
import org.fxone.ui.model.view.cmd.ViewCommand;
import org.fxone.ui.model.view.cmd.Views;

import com.sun.javafx.tk.Toolkit;

public class SingleViewPageContainer extends AnchorPane implements NotificationListener, ViewContainer<Node>{

	private View<Node> currentView;
	private Node currentNode;
	private Node defaultNode;

	public SingleViewPageContainer() {
		setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#getCurrentView()
	 */
	@Override
	public View<Node> getCurrentView() {
		return currentView;
	}

	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#openView(org.fxone.ui.model.view.View)
	 */
	@Override
	public boolean openView(View<Node> view) {
		return openView(view, null);
	}

	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#getUI()
	 */
	@Override
	public Node getUI() {
		if (this.defaultNode != null) {
			setContent(this.defaultNode, null);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#closeView(org.fxone.ui.model.view.View)
	 */
	@Override
	public boolean closeView(final View view) {
		if (view.canClose()) {
			if (Toolkit.getToolkit().isFxUserThread()) {
				closeViewInternal(view);
			} else {
				Toolkit.getToolkit().defer(new Runnable() {
					@Override
					public void run() {
						closeViewInternal(view);
					}

				});
			}
			return true;
		}
		return false;
	}

	private void closeViewInternal(View view) {
		if (view != null) {
			// adapt menu list
			if (this.currentView == view) {
				this.currentView = null;
				initView();
			}
		}
	}

	private void initView() {
		if(this.currentView!=null){
			setContent(this.currentView.getUI(),
					this.currentView.getName());
		} else {
			setContent(this.defaultNode, null);
		}
	}

	public void setDefaultNode(Node node) {
		this.defaultNode = node;
	}

	public Node getCurrentNode() {
		return this.currentNode;
	}

	private void setContent(Node node, String name) {
		this.currentNode = node;
		Node old = null;
		if (!getChildren().isEmpty()) {
			old = getChildren().get(0);
		}
		if (old != node) {
			getChildren().clear();
		}
		if (getChildren().isEmpty()) {
			getChildren().add(node);
			AnchorPane.setTopAnchor(node, 5d);
			AnchorPane.setRightAnchor(node, 5d);
			AnchorPane.setBottomAnchor(node, 5d);
			AnchorPane.setLeftAnchor(node, 5d);
		}
	}

	public boolean openView(final View view, final Object ui) {
		if (this.currentView != null && this.currentView == view) {
			return true;
		}
		if (Toolkit.getToolkit().isFxUserThread()) {
			openViewInternal(view, ui);
		} else {
			Toolkit.getToolkit().defer(new Runnable() {
				@Override
				public void run() {
					openViewInternal(view, ui);
				}
			});
		}
		return true;
	}

	private void openViewInternal(View<Node> view, Object ui) {
		if (view != null) {
			if (ui == null || !(ui instanceof Node)) {
				setContent(view.getUI(), view.getName());
			} else {
				setContent((Node) ui, view.getName());
			}
		} else {
			setContent(this.defaultNode, null);
		}
		View closedView = this.currentView;
		if (closedView != null) {
			// closedView.closed(this);
			Views.viewClosed(this, closedView);
		}
		this.currentView = view;
	}


	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#closeAllViews()
	 */
	@Override
	public boolean closeAllViews() {
		if(this.currentView!=null){
			if (!closeView(this.currentView)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void setEnabled(boolean enabled) {
		if(enabled){
			NotificationService.get().addListener(this);
		}
		else{
			NotificationService.get().removeListener(this);
		}
	}

	@Override
	public void notified(Notification event) {
		if(ViewCommand.NOTIFTYPE_VIEW_OPEN.isMatching(event)){
			ViewCommand cmd = (ViewCommand)event;
			View<Node> view = (View<Node>) cmd.getView();
			openView(view);
		}
		else if(ViewCommand.NOTIFTYPE_VIEW_CLOSE.isMatching(event)){
			ViewCommand cmd = (ViewCommand)event;
			View<Node> view = (View<Node>) cmd.getView();
			closeView(view);
		}
	}

}
