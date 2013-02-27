package org.fxone.core.events.notif;

import java.util.Map;
import java.util.concurrent.Future;

import org.fxone.core.events.EventGroup;
import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationDefinition;
import org.fxone.core.events.NotificationService;
import org.fxone.core.events.Severity;


public final class PreloadNotification extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7433764685161650502L;
	
	private static final String NAME = "Note:dataPreloaded";
	private static final String ITEM_TYPE = "itemType";
	private static final String ITEMS_LOADED = "itemsLoaded";

	public static NotificationDefinition NOTIFICATION_DEF = new NotificationDefinition(
			EventGroup.DATA_ACCESS_LAYER.toString(),
			NAME,
			"Event that is triggered when an item table is successfully preloaded.",
			Severity.DEBUG).defineParameter(ITEM_TYPE, Class.class, true)
			.defineParameter(ITEMS_LOADED, Map.class, true).setReadOnly();
	
	PreloadNotification() {
		super(NOTIFICATION_DEF);
	}

	public static <T> Future<PreloadNotification> send(Object owner,
			Class<T> itemType, Map<Long, T> itemsLoaded) {
		PreloadNotification evt = new PreloadNotification();
		evt.setItemType(itemType);
		evt.setItemsLoaded(itemsLoaded);
		return NotificationService.get().publishEvent(evt, PreloadNotification.class);
	}

	public Class<?> getItemType() {
		return getData(ITEM_TYPE, Class.class);
	}

	@SuppressWarnings("rawtypes")
	public void setItemType(Class type){
		setData(ITEM_TYPE, type);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Long, ?> getItemsLoaded() {
		return getData(ITEMS_LOADED, Map.class);
	}

	public void setItemsLoaded(Map<Long, ?> items){
		setData(ITEMS_LOADED, items);
	}
}
