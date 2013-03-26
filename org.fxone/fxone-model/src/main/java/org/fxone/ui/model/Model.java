package org.fxone.ui.model;

import java.util.Locale;
import java.util.concurrent.Future;

import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.nav.cmd.ClearNavigationHistory;
import org.fxone.ui.model.nav.cmd.NavigateTo;
import org.fxone.ui.model.res.cmd.ResourceRequest;
import org.fxone.ui.model.view.View;
import org.fxone.ui.model.view.ViewContainer;
import org.fxone.ui.model.view.cmd.CloseViewRequest;
import org.fxone.ui.model.view.cmd.GetViewsRequest;
import org.fxone.ui.model.view.cmd.OpenViewRequest;
import org.fxone.ui.model.view.cmd.ViewClosedNotif;
import org.fxone.ui.model.view.cmd.ViewOpenedNotif;
import org.fxone.ui.model.workbench.cmd.SetWorkbenchStatus;
import org.fxone.ui.model.workbench.cmd.SetWorkbenchTitles;

public final class Model {

	private Model() {
	}

	public static final class Navigation {

		private Navigation() {
		}

		public static Future<NavigateTo> goHome() {
			return navigateTo("home");
		}

		public static Future<NavigateTo> goNext() {
			return navigateTo("_next");
		}

		public static Future<NavigateTo> goBack() {
			return navigateTo("_back");
		}

		public static Future<NavigateTo> goUp() {
			return navigateTo("_up");
		}

		public static Future<ClearNavigationHistory> clearHistory() {
			return NotificationService.get().publishEvent(
					new ClearNavigationHistory(), ClearNavigationHistory.class);
		}

		public static Future<NavigateTo> navigateTo(String target) {
			return NotificationService.get().publishEvent(
					createNavigateToNotif(target), NavigateTo.class);
		}

		public static NavigateTo createNavigateToNotif(String target) {
			NavigateTo notif = new NavigateTo();
			notif.setNavigationTarget(target);
			return notif;
		}
	}

	public static final class Resources {

		private Resources() {
		}

		public static Future<ResourceRequest> resolve(String family,
				String key, Locale locale, Object... context) {
			return NotificationService.get().publishEvent(
					new ResourceRequest(family, key, locale, context),
					ResourceRequest.class);
		}

		public static Future<ResourceRequest> resolve(String key,
				Locale locale, Object... context) {
			return NotificationService.get().publishEvent(
					new ResourceRequest(key, locale, context),
					ResourceRequest.class);
		}

	}

	public static final class Views {

		private Views() {
			// singleton
		}

		public static Future<OpenViewRequest> openView(View view,
				String viewContainerID) {
			OpenViewRequest notif = createOpenViewNotif(view, viewContainerID);
			return NotificationService.get().publishEvent(notif,
					OpenViewRequest.class);
		}

		public static Future<OpenViewRequest> openView(View view) {
			OpenViewRequest notif = createOpenViewNotif(view, null);
			return NotificationService.get().publishEvent(notif,
					OpenViewRequest.class);
		}

		public static Future<CloseViewRequest> closeView(View view,
				String viewContainerID) {
			CloseViewRequest notif = createCloseViewNotif(view, viewContainerID);
			return NotificationService.get().publishEvent(notif,
					CloseViewRequest.class);
		}

		public static Future<CloseViewRequest> closeView(View view) {
			CloseViewRequest notif = createCloseViewNotif(view, null);
			return NotificationService.get().publishEvent(notif,
					CloseViewRequest.class);
		}

		public static Future<GetViewsRequest> getCurrentViews() {
			GetViewsRequest notif = createGetCurrentViewsNotif(null);
			return NotificationService.get().publishEvent(notif,
					GetViewsRequest.class);
		}

		public static Future<GetViewsRequest> getCurrentViews(
				String viewContainerID) {
			GetViewsRequest notif = createGetCurrentViewsNotif(viewContainerID);
			return NotificationService.get().publishEvent(notif,
					GetViewsRequest.class);
		}

		public static GetViewsRequest createGetCurrentViewsNotif(
				String viewContainerID) {
			GetViewsRequest notif = new GetViewsRequest(viewContainerID);
			if (viewContainerID != null) {
				notif.setViewContainerID(viewContainerID);
			}
			return notif;
		}

		public static OpenViewRequest createOpenViewNotif(View view,
				String viewContainerID) {
			OpenViewRequest notif = new OpenViewRequest();
			notif.setView(view);
			if (viewContainerID != null) {
				notif.setViewContainerID(viewContainerID);
			}
			return notif;
		}

		public static CloseViewRequest createCloseViewNotif(View view,
				String viewContainerID) {
			CloseViewRequest notif = new CloseViewRequest();
			notif.setView(view);
			if (viewContainerID != null) {
				notif.setViewContainerID(viewContainerID);
			}
			return notif;
		}

		public static Future<ViewOpenedNotif> viewOpenened(
				ViewContainer viewContainer, View view) {
			ViewOpenedNotif notif = new ViewOpenedNotif(view, viewContainer);
			return NotificationService.get().publishEvent(notif,
					ViewOpenedNotif.class);
		}

		public static Future<ViewClosedNotif> viewClosed(
				ViewContainer viewContainer, View view) {
			ViewClosedNotif notif = new ViewClosedNotif(view, viewContainer);
			return NotificationService.get().publishEvent(notif,
					ViewClosedNotif.class);
		}

	}

	public static final class Workbench {

		private Workbench() {
		}

		public static Future<SetWorkbenchTitles> setTitles(String title,
				String areaTitle, String areaSubTitle, String areaDescription) {
			SetWorkbenchTitles cmd = new SetWorkbenchTitles();
			if (title != null) {
				cmd.setTitle(title);
			}
			if (areaTitle != null) {
				cmd.setAreaTitle(areaTitle);
			}
			if (areaSubTitle != null) {
				cmd.setAreaSubTitle(areaSubTitle);
			}
			if (areaDescription != null) {
				cmd.setAreaDescription(areaDescription);
			}
			return NotificationService.get().publishEvent(cmd,
					SetWorkbenchTitles.class);
		}

		public static Future<SetWorkbenchStatus> setStatus(String status,
				String info) {
			SetWorkbenchStatus cmd = new SetWorkbenchStatus();
			if (status != null) {
				cmd.setStatus(status);
			}
			if (info != null) {
				cmd.setInfo(info);
			}
			return NotificationService.get().publishEvent(cmd,
					SetWorkbenchStatus.class);
		}

	}

}
