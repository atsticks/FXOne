package org.fxone.ui.rt.components.view;


import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Label;

import org.fxone.ui.model.view.AbstractView;

@SuppressWarnings("unchecked")
public class EmptyView extends AbstractView {

	private Label label = new Label();
	
	public EmptyView(String id) {
		super("UndefinedView: " + id);
		label.setText("Undefined View: '" + id + "'.");
	}
	
	public EmptyView(String id, Throwable err) {
		super("ViewError: " + id);
		StringWriter tgt = new StringWriter(200);
		PrintWriter w = new PrintWriter(tgt);
		w.println("Failed to load View: '" + id + "': ");
		if(err!=null){
			err.printStackTrace(w);
		}
		label.setText(tgt.toString());
	}
	
	@Override
	public boolean canClose() {
		return true;
	}

	@Override
	public void closed() {
	}

	@Override
	public String getViewContainerID() {
		return null;
	}

	@Override
	public Object getUI() {
		return this.label;
	}

}
