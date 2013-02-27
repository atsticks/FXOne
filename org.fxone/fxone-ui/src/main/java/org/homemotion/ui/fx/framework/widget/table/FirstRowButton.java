package org.homemotion.ui.fx.framework.widget.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FirstRowButton extends Button {
	

	public FirstRowButton(final TableView table) {
		super(null, new ImageView(new Image(FirstRowButton.class.getResourceAsStream("/img/16x16/btn_first.png"))));
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				table.getSelectionModel().select(0);
			}
		});
	}
}
