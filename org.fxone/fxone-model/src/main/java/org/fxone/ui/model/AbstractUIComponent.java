package org.fxone.ui.model;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.fxone.core.types.AbstractAdaptable;
import org.fxone.core.types.Identifiable;

@SuppressWarnings("rawtypes")
public abstract class AbstractUIComponent<T> extends AbstractAdaptable implements
		Identifiable, Presentable, Comparable {

	protected final Logger log = Logger.getLogger(getClass());

	protected String id;
	private PresentableAdapter presentableAdapter;


	public AbstractUIComponent(String id) {
		this.id = id;
		presentableAdapter = new PresentableAdapter(null, getClass().getSimpleName());
	}

	protected String getResourceFamily() {
		return null;
	}

	public String getIdentifier() {
		return id;
	}

	public boolean isLeaf() {
		return true;
	}


	@Override
	public String getName(Locale locale) {
		return presentableAdapter.getName(locale);
	}

	@Override
	public String getDescription(Locale locale) {
		return presentableAdapter.getDescription(locale);
	}

	@Override
	public String getTooltip(Locale locale) {
		return presentableAdapter.getTooltip(locale);
	}

	@Override
	public String getIcon16(Locale locale) {
		return presentableAdapter.getIcon16(locale);
	}

	@Override
	public String getIcon32(Locale locale) {
		return presentableAdapter.getIcon32(locale);
	}

	@Override
	public String getIcon48(Locale locale) {
		return presentableAdapter.getIcon48(locale);
	}

	@Override
	public String getIcon64(Locale locale) {
		return presentableAdapter.getIcon64(locale);
	}

	@Override
	public String getIcon128(Locale locale) {
		return presentableAdapter.getIcon128(locale);
	}

	@Override
	public String getImage(Locale locale) {
		return presentableAdapter.getImage(locale);
	}

	@Override
	public String toString() {
		return "AbstractUIComponent [id=" + id + "]; " + super.toString();
	}

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof AbstractUIComponent)) {
			return -1;
		}
		return this.id.compareTo(((AbstractUIComponent) o).id);
	}
	
	public abstract T getUI();

}
