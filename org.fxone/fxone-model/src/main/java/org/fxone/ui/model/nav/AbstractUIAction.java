package org.fxone.ui.model.nav;

public abstract class AbstractUIAction implements NavigateableAction {

	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getIdentifier() {
		return getClass().getSimpleName();
	}

	@Override
	public String getPlacedAfter() {
		return null;
	}

	@Override
	public String getPlacedBefore() {
		return null;
	}

	@Override
	public int compareTo(Navigateable o) {
		if (o == null) {
			return -1;
		}
		if (o.getIdentifier().equals(this.getPlacedBefore())) {
			return -1;
		}
		if (o.getIdentifier().equals(this.getPlacedAfter())) {
			return 1;
		}
		if (this.getIdentifier().equals(o.getPlacedBefore())) {
			return 1;
		}
		if (this.getIdentifier().equals(o.getPlacedAfter())) {
			return -1;
		}
		return 0;
	}

}
