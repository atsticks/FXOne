package org.fxone.ui.rt.components.header;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Dependent
@Named("header-logo")
@Default
public class Logo extends ImageView {

	public Logo() {
		super(new Image(Logo.class.getResourceAsStream("/img/ui/logo.png")));
	}

}
