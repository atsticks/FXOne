package org.fxone.core.events;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractNotificationConsumer implements NotificationConsumer {

	protected final static class ParseResult {
		public String group;
		public String name;
		public Map<String, String> params = new HashMap<String, String>();
		public Object owner;

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ParseResult [group=" + group + ", name=" + name
					+ ", params=" + params + ", owner=" + owner + "]";
		}

	}

	@Override
	public AbstractNotification parseNotification(Object owner, String notification) {
		if (owner == null) {
			throw new IllegalArgumentException("Owner is required.");
		}
		ParseResult result = parseExpression(notification);
		result.owner = owner;
		return parseNotification(result);
	}

	/**
	 * Parse notif expression, of kind: Group::Area:subName;key=value,key2=
	 * 
	 * @param exp
	 * @return
	 */
	protected ParseResult parseExpression(String exp) {
		exp = exp.trim();
		ParseResult pr = new ParseResult();
		int start = exp.indexOf("::");
		if (start >= 0) {
			pr.group = exp.substring(0, start);
			exp = exp.substring(start + 2);
		}
		start = exp.indexOf(";");
		if (start >= 0) {
			pr.name = exp.substring(0, start);
			exp = exp.substring(start + 1);
		} else {
			pr.name = exp;
			return pr;
		}
		String[] params = exp.split(",");
		for (String fullParam : params) {
			fullParam = fullParam.trim();
			if (fullParam.isEmpty()) {
				continue;
			}
			// spli at first occurence
			int eqIndex = fullParam.indexOf('=');
			pr.params.put(fullParam.substring(0, eqIndex),
					fullParam.substring(eqIndex + 1));
		}
		return pr;
	}

	protected abstract AbstractNotification parseNotification(ParseResult result);

}
