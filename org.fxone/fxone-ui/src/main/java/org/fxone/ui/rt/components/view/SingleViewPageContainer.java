package org.fxone.ui.rt.components.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.inject.Singleton;

import org.fxone.core.events.AbstractNotification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.Model;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;
import org.fxone.ui.model.view.cmd.CloseViewRequest;
import org.fxone.ui.model.view.cmd.OpenViewRequest;

import com.sun.javafx.tk.Toolkit;

@Singleton
@Default
public class SingleViewPageContainer extends AnchorPane implements NotificationListener, ViewContainer{

	private View currentView;
	private Node currentNode;
//	private Node defaultNode;

	public SingleViewPageContainer() {
		setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#getCurrentView()
	 */
	@Override
	public View getCurrentView() {
		return currentView;
	}

	/* (non-Javadoc)
	 * @see org.fxone.ui.rt.components.view.ViewContainer#openView(org.fxone.ui.model.view.View)
	 */
	@Override
	public boolean openView(View view) {
		return openView(view, new Label(view.getName()));
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
			setContent((Node)this.currentView,
					this.currentView.getName());
		}
//		else {
//			setContent(this.defaultNode, null);
//		}
	}

//	public void setDefaultNode(Node node) {
//		this.defaultNode = node;
//	}

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

	private void openViewInternal(View view, Object ui) {
		if (view != null) {
			if (ui == null || !(ui instanceof Node)) {
				setContent((Node)view, view.getName());
			} else {
				setContent((Node) ui, view.getName());
			}
		} 
//		else {
//			setContent(this.defaultNode, null);
//		}
		View closedView = this.currentView;
		if (closedView != null) {
			Model.Views.viewClosed(this, closedView);
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

//	public void setEnabled(boolean enabled) {
//		if(enabled){
//			NotificationService.get().addListener(this);
//		}
//		else{
//			NotificationService.get().removeListener(this);
//		}
//	}

	@Override
	public void notified(@Observes AbstractNotification event) {
		if(event.isMatching(OpenViewRequest.class)){
			OpenViewRequest cmd = (OpenViewRequest)event;
			View view = (View) cmd.getView();
			openView(view);
		}
		else if(event.isMatching(CloseViewRequest.class)){
			CloseViewRequest cmd = (CloseViewRequest)event;
			View view = (View) cmd.getView();
			closeView(view);
		}
	}

	@Override
	public View[] getViewsVisible() {
		if(this.currentView!=null){
			return new View[]{this.currentView};
		}
		return new View[0];
	}

}
