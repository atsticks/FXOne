package org.fxone.ui.rt.nav;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

import org.apache.log4j.Logger;

public class MenuButton extends Button {

	private Glow effects = new Glow(0.02955974842767295);
	private Paint oldFill;
	
	public MenuButton() {
		 setEffect(effects);
		 setOnMouseEntered(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evt) {
				effects.setInput(new Bloom(0.25555555555555554));
			}});
		 setOnMouseExited(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent evt) {
					effects.setInput(null);
				}});
		 setOnMousePressed(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent evt) {
					scaleXProperty().set(1.1);
					scaleYProperty().set(1.1);
					oldFill = getTextFill();
					setTextFill(Paint.valueOf("#fffdcc"));
				}});
		 setOnMouseReleased(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent evt) {
					scaleXProperty().set(1);
					scaleYProperty().set(1);
					setTextFill(oldFill);
				}});
	}
	

	protected Node createResource(String icon) {
		if (icon == null) {
			return null;
		}
		try{
		   return new ImageView(new Image(getClass().getResourceAsStream(icon)));
		}
		catch(Exception e){
			Logger.getLogger(getClass()).error("Failed to load icon: " + icon, e);
			return null;
		}
	}

}
