package org.fxone.ui.model.workbench;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.fxone.core.cdi.Container;
import org.fxone.core.types.AnnotationManager;
import org.fxone.ui.model.msg.ResourceProvider;
import org.fxone.ui.model.nav.NavigationArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.perspective.Perspective;

public abstract class AbstractWorkbench implements Workbench {

	protected static final String DEFAULT_STYLE_ID = "default";
	protected final Map<String, Perspective> perspectives = new ConcurrentHashMap<String, Perspective>();
	private Perspective currentPerspective;
	private final Logger log = Logger.getLogger(getClass());
	private NavigationArea currentNavigation;
	private ResourceProvider resourceProvider;

	public AbstractWorkbench(ResourceProvider resourceProvider) {
		if (resourceProvider == null) {
			throw new IllegalArgumentException(
					"resourceProvider required (available in CDI context.");
		}
		this.resourceProvider = resourceProvider;
		initPerspectives();
	}

	protected void initPerspectives() {
		Set<String> perspectiveClasses = AnnotationManager
				.getAnnotatedClasses(org.fxone.ui.annot.UIPerspective.class
						.getName());
		if (perspectiveClasses != null) {
			for (String className : perspectiveClasses) {
				try {
					Class<Perspective> c = (Class<Perspective>) Class
							.forName(className);
					org.fxone.ui.annot.UIPerspective styleSpec = (org.fxone.ui.annot.UIPerspective) c
							.getAnnotation(org.fxone.ui.annot.UIPerspective.class);
					this.perspectives.put(styleSpec.value(), c.newInstance());
				} catch (Exception e) {
					log.error("Error registering style: " + className, e);
				}
			}
		}
	}

	@Override
	public String[] getPerspectives() {
		return perspectives.keySet().toArray(new String[perspectives.size()]);
	}

	@Override
	public Perspective getCurrentPerspective() {
		return currentPerspective;
	}

	@Override
	public boolean setCurrentPerspective(String persepectiveID) {
		Perspective styleInstance = perspectives.get(persepectiveID);
		if (styleInstance == getCurrentPerspective()) {
			return true;
		}
		if (styleInstance != null) {
			Perspective current = getCurrentPerspective();
			this.currentPerspective = styleInstance;
			applyStyle(styleInstance);
			if (current != null) {
				current.deactivated(this);
			}
			styleInstance.activated(this);
		} else {
			log.warn("Failed to apply style (not found): " + persepectiveID);
		}
		return false;
	}

	public void home() {
		navigateTo(Container.getInstance(NavigationManager.class)
				.getRootNavigation());
	}

	// public void up() {
	// NavigationNode node = getCurrentNavigation();
	// if (node != null) {
	// NavigationNode parNode = node.getParent();
	// if (parNode != null) {
	// navigateTo(parNode);
	// }
	// }
	// }

	public void navigateTo(NavigationArea nav) {
		String perspective = nav.getPerspective();
		if (perspective == null) {
			perspective = DEFAULT_STYLE_ID;
		}
		setCurrentPerspective(perspective);
		if (this.currentPerspective == null) {
			setDefaultPerspective();
		}
		adaptTitles(nav);
		// if (this.currentPerspective != null) {
		// this.currentPerspective.navigateTo(nav);
		// } else {
		// log.error("Failed to navigate to: " + nav.getName()
		// + " - no style active!");
		// }
	}

	protected void adaptTitles(NavigationArea nav) {
		if (nav == null) {
			WorkbenchCommands.setAreaTitle("Homemotion");
			WorkbenchCommands.setAreaTitle("");
			return;
		}
		NavigationArea section = getSection(nav);
		if (section != null) {
			WorkbenchCommands.setAreaTitle(section.getIdentifier());
		} else {
			WorkbenchCommands.setAreaTitle("HOME");
		}
		NavigationArea parent = nav.getParent();
		if (parent != null && parent != section) {
			WorkbenchCommands.setAreaSubTitle(this.resourceProvider.getName(parent.getIdentifier(), Locale.getDefault())); // TODO i18n
			WorkbenchCommands.setAreaDescription(this.resourceProvider.getDescription(parent.getIdentifier(),Locale.getDefault())); // TODO i18n
		} else {
			WorkbenchCommands.setAreaSubTitle("");
			WorkbenchCommands.setAreaDescription("");
		}
	}

	protected NavigationArea getSection(NavigationArea nav) {
		if (nav == null || nav.isRoot()) {
			return null;
		}
		NavigationArea current = nav;
		while (current.getParent() != null) {
			if (current.getParent().isRoot()) {
				break;
			}
			current = current.getParent();
		}
		return current;
	}

	@Override
	public void setDefaultPerspective() {
		setCurrentPerspective(DEFAULT_STYLE_ID);
	}

	protected abstract void applyStyle(Perspective styleInstance);

}
