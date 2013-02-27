package org.fxone.core.types;

public abstract class AbstractIdentifiable implements Identifiable {

	private String id;

	public AbstractIdentifiable(String id) {
		if (id == null) {
			throw new IllegalArgumentException("id is required.");
		}
		this.id = id;
	}

	@Override
	public String getIdentifier() {
		return this.id;
	}

}
