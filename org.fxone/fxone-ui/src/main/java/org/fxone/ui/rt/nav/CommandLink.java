package org.fxone.ui.rt.nav;

import java.util.Locale;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;

import org.fxone.core.cdi.Container;
import org.fxone.ui.model.msg.ResourceProvider;
import org.fxone.ui.model.nav.impl.HeaderCommand;

public class CommandLink extends Hyperlink {

	public CommandLink(final HeaderCommand cmd) {
		setText(Container.getInstance(ResourceProvider.class).getName(cmd.getClass(), Locale.ENGLISH)); // TODO i18n
		setUnderline(false);
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				cmd.run();
			}
		});
		setUserData(cmd);
	}

}