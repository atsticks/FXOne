package org.fxone.ui.model.nav.cmd;

import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;

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
}
