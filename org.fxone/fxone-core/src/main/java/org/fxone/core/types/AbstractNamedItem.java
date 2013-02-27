package org.fxone.core.types;

public abstract class AbstractNamedItem extends AbstractItem implements
		NamedItem {

	private String name;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [description=" + description
				+ ", identifier=" + getIdentifier() + ", name=" + name + "]";
	}

}
