package org.fxone.ui.model.msg;

import java.util.List;

import org.fxone.core.events.Severity;
import org.fxone.core.events.notif.Message;


public interface MessageArchive {

	public boolean hasArchivedMessages();

	public List<Message> getArchivedMessages();

	public List<Message> getArchivedMessages(Severity... severities);

	public void clearArchivedMessages();

}
