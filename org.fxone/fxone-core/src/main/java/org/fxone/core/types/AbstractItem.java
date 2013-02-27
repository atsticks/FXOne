package org.fxone.core.types;


public abstract class AbstractItem extends AbstractAdaptable implements Identifiable {

	private String identifier;

	public String getIdentifier() {
		if (identifier == null) {
			return "N/A";
		}
		return identifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractItem other = (AbstractItem) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [identifier=" + identifier + "]";
	}

}
