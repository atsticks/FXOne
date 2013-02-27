package org.homemotion.ui.fx.framework.widget.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PreviousRowButton extends Button {

	public PreviousRowButton(final TableView table) {
		super(null, new ImageView(new Image(PreviousRowButton.class.getResourceAsStream("/img/16x16/btn_previous.png"))));
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int index = table.getSelectionModel().getSelectedIndex();
				int target = index - 1;
				if (target < 0) {
					target = 0;
				}
				table.getSelectionModel().clearAndSelect(target);
			}
		});
	}
}
