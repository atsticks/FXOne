package org.fxone.ui.rt.components.workbench;

import java.util.Locale;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

import org.fxone.core.types.Identifiable;
import org.fxone.core.types.NamedItem;
import org.fxone.ui.model.msg.ResourceProvider;

@Dependent
@Default
@Named("selection-info")
public class SelectionInfoPane extends BorderPane {

	private ImageView icon = new ImageView();
	private GridPane infoPane = new GridPane();
	private Label idLabel;
	private Label idValue = new Label("-");
	private Label nameLabel;
	private Label nameValue = new Label("");
	private Label descriptionLabel;
	private Label descriptionValue = new Label("");

	@Inject
	private ResourceProvider resourceProvider;

	private Object item;

	public SelectionInfoPane() {
		setLeft(icon);
		ObservableList<Node> content = infoPane.getChildren();

		idLabel = new Label("ID:");
		GridPane.setConstraints(idLabel, 0, 0);
		GridPane.setHalignment(idLabel, HPos.LEFT);
		content.add(idLabel);

		GridPane.setConstraints(idValue, 1, 0);
		GridPane.setHalignment(idValue, HPos.LEFT);
		content.add(idValue);

		nameLabel = new Label("Name:");
		GridPane.setConstraints(nameLabel, 2, 0);
		GridPane.setHalignment(nameLabel, HPos.LEFT);
		content.add(nameLabel);

		GridPane.setConstraints(nameValue, 3, 0);
		GridPane.setHalignment(nameValue, HPos.LEFT);
		content.add(nameValue);

		descriptionLabel = new Label("Details:");
		GridPane.setConstraints(descriptionLabel, 0, 1);
		GridPane.setHalignment(descriptionLabel, HPos.LEFT);
		content.add(descriptionLabel);

		GridPane.setConstraints(descriptionValue, 1, 1);
		GridPane.setHalignment(descriptionValue, HPos.LEFT);
		content.add(descriptionValue);

		setCenter(infoPane);
	}

	public Object getItem() {
		return item;
	}

	public void setItem(Object item) {
		if (item == null) {
			idValue.setText("-");
			nameValue.setText("");
			descriptionValue.setText("");
		} else {
			String id = null;
			if (item instanceof Identifiable) {
				id = ((Identifiable) item).getIdentifier();
			}
			if (id == null) {
				id = "-";
			}
			idValue.setText(id);
			if (item instanceof NamedItem) {
				nameValue.setText(((NamedItem) item).getName());
				descriptionValue.setText(((NamedItem) item).getDescription());
			} else {
				nameValue.setText(resourceProvider.getName(item.getClass(),
						Locale.ENGLISH)); // TODO i18n
				nameValue.setText(resourceProvider.getDescription(
						item.getClass(), Locale.ENGLISH)); // TODO i18n
			}
		}
	}
}
