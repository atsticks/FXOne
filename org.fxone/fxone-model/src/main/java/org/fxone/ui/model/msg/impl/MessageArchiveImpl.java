package org.fxone.ui.model.msg.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.fxone.core.events.Notification;
import org.fxone.core.events.notif.Message;
import org.fxone.ui.model.msg.MessageArchive;
import org.fxone.ui.model.msg.MessageSeverity;
import org.fxone.ui.model.msg.ResourceProvider;

@Singleton
public final class MessageArchiveImpl implements MessageArchive {

	private Logger LOGGER = Logger.getLogger(MessageArchiveImpl.class);

	private Object LOCK = new Object();

	@Inject
	private ResourceProvider resourceProvider;

	private List<Message> messages = new ArrayList<Message>(20);

	public MessageArchiveImpl(){
	}

	public boolean hasMessages() {
		synchronized (LOCK) {
			return !messages.isEmpty();
		}
	}

	public List<Message> getMessages() {
		synchronized (LOCK) {
			return Collections.unmodifiableList(messages);
		}
	}

	public List<Message> getMessages(MessageSeverity... severities) {
		synchronized (LOCK) {
			List<Message> result = new ArrayList<Message>();
			if (severities == null || severities.length == 0) {
				return result;
			}
			for (Message message : messages) {
				for (MessageSeverity sev : severities) {
					if (message.getSeverity().equals(sev)) {
						result.add(message);
					}
				}
			}
			return result;
		}
	}

	public void clearMessages() {
		synchronized (LOCK) {
			this.messages.clear();
		}
	}

	@Override
	public String toString() {
		synchronized (LOCK) {
			return "MessageManager [messages=" + messages + "]";
		}
	}

	public void notified(Notification n) {
		// TODO implement correctly
//		MessageEvent me = n.getAdapter(MessageEvent.class);
//		if (me!=null) {
//			String family = n.getData("family", String.class);
//			String key = n.getData("key", String.class);
//			Locale locale = Locale.getDefault();
//			if (n.getData("locale", Locale.class) != null) {
//				locale = n.getData("locale", Locale.class);
//			}
//			Object[] contextData = (Object[]) n.getData("contextData",
//					new Object[0]);
//			n.setData("result", resourceProvider.getMessage(family, key,
//					locale, contextData));
//			n.setCompleted();
//		} else if (n.getName().equals("Messages.postMessage")) {
//			MessageEvent msg = (MessageEvent) n.getData(MessageEvent.class);
//			this.messages.add(msg);
//		}
	}

}
