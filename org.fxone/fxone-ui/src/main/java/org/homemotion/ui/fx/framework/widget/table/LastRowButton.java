package org.homemotion.ui.fx.framework.widget.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LastRowButton extends Button {

	public LastRowButton(final TableView table) {
		super(null, new ImageView(new Image(LastRowButton.class.getResourceAsStream("/img/16x16/btn_last.png"))));
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (table.getItems().isEmpty()) {
					table.getSelectionModel().select(
							table.getItems().size() - 1);
				}
			}
		});
	}
}
