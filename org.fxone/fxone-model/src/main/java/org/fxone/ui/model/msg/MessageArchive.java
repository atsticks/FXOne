package org.fxone.ui.model.msg;

import java.util.List;

import org.fxone.core.events.notif.Message;


public interface MessageArchive {

	public boolean hasMessages();

	public List<Message> getMessages();

	public List<Message> getMessages(MessageSeverity... severities);

	public void clearMessages();

}
