package org.homemotion.ui.fx.framework.widget.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageCell<T> extends TableCell<T, Image> {

	private ImageView imageView = new ImageView();
	
	public ImageCell() {
		super();
		setGraphic(imageView);
	}

	@Override
	protected void updateItem(Image image, boolean empty) {
		imageView.setImage(image);
	}
	
	public ImageView getImageView(){
		return imageView;
	}
	
	public Image getImage(){
		return imageView.getImage();
	}
	
}
