package org.fxone.core.events.notif;

import java.util.HashMap;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationDefinition;

public abstract class AsynchMethodCall extends Notification {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -107394880806535314L;
	
	private static final String RESULT = "_result";

	public AsynchMethodCall(NotificationDefinition def) {
		super(def);
	}

	public Object setResult(Object value) {
		if (this.data == null) {
			this.data = new HashMap<String, Object>();
		}
		return this.data.put(RESULT, value);
	}

	public Object getResult() {
		if (this.data == null) {
			return null;
		}
		return this.data.get(RESULT);
	}
}
