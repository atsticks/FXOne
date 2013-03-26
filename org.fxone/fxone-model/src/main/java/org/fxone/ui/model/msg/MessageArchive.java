package org.fxone.ui.model.msg;

import java.util.List;

import org.fxone.core.events.MessageEvent;
import org.fxone.core.events.Severity;


public interface MessageArchive {

	public boolean hasArchivedMessages();

	public List<MessageEvent> getArchivedMessages();

	public List<MessageEvent> getArchivedMessages(Severity... severities);

	public void clearArchivedMessages();

}
