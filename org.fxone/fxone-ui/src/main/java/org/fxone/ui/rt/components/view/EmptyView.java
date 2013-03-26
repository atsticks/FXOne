package org.fxone.ui.rt.components.view;


import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Label;

import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContext;

@SuppressWarnings("unchecked")
public class EmptyView extends Label implements View {

	private String id;
	
	public EmptyView(String id) {
		this.id = id;
		setText("Undefined View: '" + id + "'.");
	}
	
	public EmptyView(String id, Throwable err) {
		this.id = id;
		StringWriter tgt = new StringWriter(200);
		PrintWriter w = new PrintWriter(tgt);
		w.println("Failed to load View: '" + id + "': ");
		if(err!=null){
			err.printStackTrace(w);
		}
		setText(tgt.toString());
	}
	
	@Override
	public boolean canClose() {
		return true;
	}

	@Override
	public void closed() {
	}

	@Override
	public void init(ViewContext viewContext) {
		// may be empty
	}

	@Override
	public void opened() {
		// may be empty
	}

	@Override
	public String getName() {
		return this.id;
	}

}
