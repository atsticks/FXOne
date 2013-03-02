package org.fxone.ui.model.nav.cmd;

import java.util.Locale;
import java.util.concurrent.Future;

import org.fxone.core.cdi.Container;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.nav.NavigationArea;
import org.fxone.ui.model.nav.NavigationManager;
import org.fxone.ui.model.workbench.cmd.WorkbenchCommands;

public final class Navigation {

	private Navigation(){
		
	}
	
	public static Future<NavigationEvent> goHome() {
		return NotificationService.get().publishEvent(createGoHomeNotif(), NavigationEvent.class);
	}

	public static Future<NavigationEvent> goNext() {
		return NotificationService.get().publishEvent(createGoNextNotif(), NavigationEvent.class);
	}

	public static Future<NavigationEvent> goBack() {
		return NotificationService.get().publishEvent(createGoBackNotif(), NavigationEvent.class);
	}

	public static Future<NavigationEvent> goUp() {
		return NotificationService.get().publishEvent(createGoUpNotif(), NavigationEvent.class);
	}

	public static Future<NavigationEvent> clearHistory() {
		return NotificationService.get()
				.publishEvent(createClearHistoryNotif(), NavigationEvent.class);
	}

	public static Future<NavigationEvent> createPrintHistory() {
		NavigationEvent notif = new NavigationEvent(NavigationEvent.NOTIFTYPE_PRINT_HIST);
		return NotificationService.get().publishEvent(notif, NavigationEvent.class);
	}

	public static Future<NavigationEvent> navigateTo(String target) {
		return NotificationService.get().publishEvent(
				createNavigateToNotif(target),NavigationEvent.class);
	}

	public static NavigationEvent createNavigateToNotif(String target) {
		NavigationEvent notif = new NavigationEvent(NavigationEvent.NOTIFTYPE_NAVIGATE_TO);
		notif.setTarget(target);
		return notif;
	}

	public static Future<NavigationEvent> navigateToView(String target) {
		return NotificationService.get().publishEvent(
				createNavigateToViewNotif(target), NavigationEvent.class);
	}


	public static Future<NavigationEvent> navigateToL1(String target) {
		NavigationEvent notif = new NavigationEvent(
				NavigationEvent.NOTIFTYPE_NAVIGATE_TO_L1);
		notif.setTarget(target);
		return NotificationService.get().publishEvent(notif,NavigationEvent.class);
	}

	public static Future<NavigationEvent> navigateToL2(String target) {
		NavigationEvent notif = new NavigationEvent(
				NavigationEvent.NOTIFTYPE_NAVIGATE_TO_L2);
		notif.setTarget(target);
		return NotificationService.get().publishEvent(notif,NavigationEvent.class);
	}

	public static NavigationEvent createClearHistoryNotif() {
		return new NavigationEvent(NavigationEvent.NOTIFTYPE_CLEAR_HIST);
	}

	public static NavigationEvent createGoBackNotif() {
		return new NavigationEvent(NavigationEvent.NOTIFTYPE_NAVIGATE_BACK);
	}

	public static NavigationEvent createGoHomeNotif() {
		return new NavigationEvent(NavigationEvent.NOTIFTYPE_NAVIGATE_HOME);
	}

	public static NavigationEvent createGoNextNotif() {
		return new NavigationEvent(NavigationEvent.NOTIFTYPE_NAVIGATE_NEXT);
	}

	public static NavigationEvent createGoUpNotif() {
		return new NavigationEvent(NavigationEvent.NOTIFTYPE_NAVIGATE_UP);
	}

	public static NavigationEvent createNavigateToViewNotif(String target) {
		NavigationEvent notif = new NavigationEvent(
				NavigationEvent.NOTIFTYPE_NAVIGATE_TOVIEW);
		notif.setTarget(target);
		return notif;
	}
	
	
	
	
	
	
//	
//	
//	public void home() {
//		navigateTo(Container.getInstance(NavigationManager.class)
//				.getRootNavigation());
//	}
//
//	// public void up() {
//	// NavigationNode node = getCurrentNavigation();
//	// if (node != null) {
//	// NavigationNode parNode = node.getParent();
//	// if (parNode != null) {
//	// navigateTo(parNode);
//	// }
//	// }
//	// }
//
//	public void navigateTo(NavigationArea nav) {
//		String perspective = nav.getPerspective();
//		if (perspective == null) {
//			perspective = DEFAULT_STYLE_ID;
//		}
//		setCurrentPerspective(perspective);
//		if (this.currentPerspective == null) {
//			setDefaultPerspective();
//		}
//		adaptTitles(nav);
//		// if (this.currentPerspective != null) {
//		// this.currentPerspective.navigateTo(nav);
//		// } else {
//		// log.error("Failed to navigate to: " + nav.getName()
//		// + " - no style active!");
//		// }
//	}
//
//	protected void adaptTitles(NavigationArea nav) {
//		if (nav == null) {
//			WorkbenchCommands.setAreaTitle("Homemotion");
//			WorkbenchCommands.setAreaTitle("");
//			return;
//		}
//		NavigationArea section = getSection(nav);
//		if (section != null) {
//			WorkbenchCommands.setAreaTitle(section.getIdentifier());
//		} else {
//			WorkbenchCommands.setAreaTitle("HOME");
//		}
//		NavigationArea parent = nav.getParent();
//		if (parent != null && parent != section) {
//			WorkbenchCommands.setAreaSubTitle(this.resourceProvider.getName(
//					parent.getIdentifier(), Locale.getDefault())); // TODO i18n
//			WorkbenchCommands
//					.setAreaDescription(this.resourceProvider.getDescription(
//							parent.getIdentifier(), Locale.getDefault())); // TODO
//																			// i18n
//		} else {
//			WorkbenchCommands.setAreaSubTitle("");
//			WorkbenchCommands.setAreaDescription("");
//		}
//	}
//
//	protected NavigationArea getSection(NavigationArea nav) {
//		if (nav == null || nav.isRoot()) {
//			return null;
//		}
//		NavigationArea current = nav;
//		while (current.getParent() != null) {
//			if (current.getParent().isRoot()) {
//				break;
//			}
//			current = current.getParent();
//		}
//		return current;
//	}
}
