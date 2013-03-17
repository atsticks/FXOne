package org.fxone.ui.model.nav.cmd;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.nav.NavigationEvent;

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
		notif.setNavigationTarget(target);
		return notif;
	}

	public static Future<NavigationEvent> navigateToView(String target) {
		return NotificationService.get().publishEvent(
				createNavigateToViewNotif(target), NavigationEvent.class);
	}


	public static Future<NavigationEvent> navigateToL1(String target) {
		NavigationEvent notif = new NavigationEvent(
				NavigationEvent.NOTIFTYPE_NAVIGATE_TO_L1);
		notif.setNavigationTarget(target);
		return NotificationService.get().publishEvent(notif,NavigationEvent.class);
	}

	public static Future<NavigationEvent> navigateToL2(String target) {
		NavigationEvent notif = new NavigationEvent(
				NavigationEvent.NOTIFTYPE_NAVIGATE_TO_L2);
		notif.setNavigationTarget(target);
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
		notif.setNavigationTarget(target);
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
//	public void navigateTo(NavigateableArea nav) {
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
//	protected void adaptTitles(NavigateableArea nav) {
//		if (nav == null) {
//			WorkbenchCommands.setAreaTitle("Homemotion");
//			WorkbenchCommands.setAreaTitle("");
//			return;
//		}
//		NavigateableArea section = getSection(nav);
//		if (section != null) {
//			WorkbenchCommands.setAreaTitle(section.getIdentifier());
//		} else {
//			WorkbenchCommands.setAreaTitle("HOME");
//		}
//		NavigateableArea parent = nav.getParent();
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
//	protected NavigateableArea getSection(NavigateableArea nav) {
//		if (nav == null || nav.isRoot()) {
//			return null;
//		}
//		NavigateableArea current = nav;
//		while (current.getParent() != null) {
//			if (current.getParent().isRoot()) {
//				break;
//			}
//			current = current.getParent();
//		}
//		return current;
//	}
}
