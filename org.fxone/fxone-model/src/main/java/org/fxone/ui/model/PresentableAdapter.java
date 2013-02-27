package org.fxone.ui.model;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.fxone.core.cdi.Container;
import org.fxone.ui.model.msg.ResourceProvider;

public class PresentableAdapter implements Presentable {

	protected final Logger log = Logger.getLogger(getClass());

	protected String resourceBundle;
	private String resourceKey = "";

	public PresentableAdapter(String resourceBundle, String resourceKey) {
		this.resourceBundle = resourceBundle;
		this.resourceKey = resourceKey;
	}

	protected String getResourceFamily() {
		return resourceBundle;
	}

	protected String getResourceKey() {
		return resourceKey;
	}

	@Override
	public String getName(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getMessage(
				getResourceFamily(), getResourceKey() + ".name", locale);
	}

	@Override
	public String getDescription(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getMessage(
				getResourceFamily(), getResourceKey() + ".description", locale);
	}

	@Override
	public String getTooltip(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getMessage(
				getResourceFamily(), getResourceKey() + ".tooltip", locale);
	}

	@Override
	public String getIcon16(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getIcon16(
				getResourceFamily(), getResourceKey(), locale);
	}

	@Override
	public String getIcon32(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getIcon32(
				getResourceFamily(), getResourceKey(), locale);
	}

	@Override
	public String getIcon48(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getIcon48(
				getResourceFamily(), getResourceKey(), locale);
	}

	@Override
	public String getIcon64(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getIcon64(
				getResourceFamily(), getResourceKey(), locale);
	}

	@Override
	public String getIcon128(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getIcon128(
				getResourceFamily(), getResourceKey(), locale);
	}

	@Override
	public String getImage(Locale locale) {
		return Container.getInstance(ResourceProvider.class).getImage(
				getResourceFamily(), getResourceKey(), locale);
	}

	@Override
	public String toString() {
		return "PresentableAdapter [resourceBundle="
				+ resourceBundle + ", resourceKey=" + getResourceKey() + "]; "
				+ super.toString();
	}

}
