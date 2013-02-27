package org.homemotion.ui.fx.framework.widget.table;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NextRowButton extends Button {

	public NextRowButton(final TableView table) {
		super(null, new ImageView(new Image(NextRowButton.class.getResourceAsStream("/img/16x16/btn_next.png"))));
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int index = table.getSelectionModel().getSelectedIndex();
				int target = index + 1;
				if(target>table.getItems().size()){
					target = table.getItems().size();
				}
				table.getSelectionModel().clearAndSelect(target);
			}});
	}
}
