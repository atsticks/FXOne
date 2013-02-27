package org.fxone.ui.model.nav.impl;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationService;
import org.fxone.ui.model.nav.NavigationHistory;
import org.fxone.ui.model.nav.cmd.Navigation;
import org.fxone.ui.model.nav.cmd.NavigationEvent;

@Singleton
public class NavigationHistoryImpl implements NavigationHistory {

	private static final Logger LOGGER = Logger
			.getLogger(NavigationHistoryImpl.class);

	private List<HistoryItem> history = new LinkedList<HistoryItem>();
	private int pos = 0;

	/**
	 * Got to previous page in history
	 */
	public void back() {
		synchronized (history) {
			if (isBackEnabled()) {
				perform(history.get(--pos));
			} else {
				Navigation.goHome();
			}
		}
	}

	/**
	 * Go to next page in history if there is one
	 */
	public void forward() {
		synchronized (history) {
			if (isForwardEnabled()) {
				perform(history.get(++pos));
			}
		}
	}

	private void perform(HistoryItem cmd) {
		LOGGER.info("Performing command: " + cmd.cmd);
		NotificationService.get().publishEvent(cmd.cmd, NavigationEvent.class);
	}

	/**
	 * Utility method for viewing the page history
	 */
	private void printHistory() {
		System.out.println("NAVIGATION HISTORY:");
		synchronized (history) {
			for (HistoryItem histItem : history) {
				System.out.println(histItem.title + "->" + histItem.cmd);
			}
		}
	}

	private static final class HistoryItem {
		String title;
		NavigationEvent cmd;
	}

	public NavigationEvent removeNavigation(int pos) {
		synchronized (history) {
			NavigationEvent cmd = this.history.remove(pos).cmd;
			if (pos >= history.size()) {
				pos = history.size() - 1;
			}
			return cmd;
		}
	}

	@Override
	public boolean isForwardEnabled() {
		synchronized (history) {
			return pos < (history.size() - 1);
		}
	}

	@Override
	public boolean isBackEnabled() {
		synchronized (history) {
			return pos > 0;
		}
	}

	@Override
	public void addNotification(NavigationEvent cmd, String title) {
		synchronized (history) {
			if (history.size() > pos) {
				int start = pos + 1;
				for (int i = start; i < history.size(); i++) {
					history.remove(start);
				}
			}
			HistoryItem item = new HistoryItem();
			item.cmd = cmd;
			item.title = title;
			history.add(item);
			pos++;
		}
	}

	@Override
	public void clearHistory() {
		synchronized (history) {
			this.history.clear();
			pos = 0;
		}
	}

	@Override
	public int getSize() {
		return this.history.size();
	}

	@Override
	public int getIndex() {
		return pos;
	}

	@Override
	public String getNotificationTitle(int pos) {
		synchronized (history) {
			return history.get(pos).title;
		}
	}

	@Override
	public NavigationEvent getNotification(int pos) {
		synchronized (history) {
			return history.get(pos).cmd;
		}
	}

	public void notified(Notification evt) {
		if (NavigationEvent.NOTIFTYPE_NAVIGATE_BACK.isMatching(evt)) {
			back();
		} else if (NavigationEvent.NOTIFTYPE_NAVIGATE_NEXT.isMatching(evt)) {
			forward();
		} else if (NavigationEvent.NOTIFTYPE_CLEAR_HIST.isMatching(evt)) {
			clearHistory();
		} else if (NavigationEvent.NOTIFTYPE_PRINT_HIST.isMatching(evt)) {
			printHistory();
		}
	}

}
