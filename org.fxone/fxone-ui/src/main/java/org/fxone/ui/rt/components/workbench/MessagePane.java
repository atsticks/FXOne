package org.fxone.ui.rt.components.workbench;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import org.fxone.core.events.Notification;
import org.fxone.core.events.NotificationListener;
import org.fxone.core.events.NotificationService;
import org.fxone.core.events.Severity;
import org.fxone.ui.annot.UIComponent;
import org.fxone.ui.model.workbench.cmd.WorkbenchCommands;
import org.fxone.ui.rt.components.AbstractFXMLComponent;
import org.homemotion.ui.fx.framework.widget.table.ImageCell;

@Dependent
@Named("message-pane")
@Default
@UIComponent(fxmlLocation = "/org/fxone/ui/rt/components/workbench/MessagePane.fxml")
public class MessagePane extends AbstractFXMLComponent implements
		NotificationListener {
	@FXML
	private TableView<MessageEntry> messageTickerTable;

	@FXML
	private MenuButton messageLevelButton;

	private final ObservableList<MessageEntry> data = FXCollections
			.observableArrayList();

	private Object selectedItem;

	private String status = "OK";

	private Severity level = Severity.DEBUG;

	@SuppressWarnings("unchecked")
	public MessagePane() {
		super();
		TableColumn<MessageEntry, Image> severityCol = new TableColumn<MessageEntry, Image>(
				" ");
		severityCol.setMaxWidth(22);
		severityCol.setMinWidth(22);
		TableColumn<MessageEntry, String> groupCol = new TableColumn<MessageEntry, String>(
				"Group");
		TableColumn<MessageEntry, String> nameCol = new TableColumn<MessageEntry, String>(
				"Name");
		TableColumn<MessageEntry, String> paramsCol = new TableColumn<MessageEntry, String>(
				"Parameters");
		paramsCol.setMaxWidth(Integer.MAX_VALUE);
		severityCol
				.setCellValueFactory(new PropertyValueFactory<MessageEntry, Image>(
						"image"));
		Callback<TableColumn<MessageEntry, Image>, TableCell<MessageEntry, Image>> cf = new Callback<TableColumn<MessageEntry, Image>, TableCell<MessageEntry, Image>>() {
			@Override
			public TableCell<MessageEntry, Image> call(
					TableColumn<MessageEntry, Image> param) {
				return new ImageCell<MessageEntry>();
			}
		};
		severityCol.setCellFactory(cf);
		groupCol.setCellValueFactory(new PropertyValueFactory<MessageEntry, String>(
				"group"));
		nameCol.setCellValueFactory(new PropertyValueFactory<MessageEntry, String>(
				"name"));
		paramsCol
				.setCellValueFactory(new PropertyValueFactory<MessageEntry, String>(
						"parameters"));
		messageTickerTable.getColumns().addAll(severityCol, groupCol, nameCol,
				paramsCol);
		messageTickerTable.setItems(data);
		connectActions();
		// ContextManager.setInstance(Notifier.class, this);
		NotificationService.get().addListener(this);
	}

	private void connectActions() {
		MenuItem action = (MenuItem) messageLevelButton.getItems().get(0);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(null);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(2);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(Severity.ALARM);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(3);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(Severity.FATAL);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(4);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(Severity.ERROR);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(5);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(Severity.WARNING);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(6);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(Severity.INFO);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(7);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				setMessageLevel(Severity.DEBUG);
				clearChecks(messageLevelButton.getItems());
				((CheckMenuItem) evt.getSource()).setSelected(true);
			}
		});
		action = (MenuItem) messageLevelButton.getItems().get(9);
		action.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt) {
				clearMessages();
			}
		});

	}

	protected void clearChecks(ObservableList<MenuItem> items) {
		for (Iterator iterator = items.iterator(); iterator.hasNext();) {
			MenuItem menuItem = (MenuItem) iterator.next();
			if (menuItem instanceof CheckMenuItem) {
				((CheckMenuItem) menuItem).setSelected(false);
			}
		}
	}

	public void setMessageLevel(Severity level) {
		this.level = level;
	};

	public void addMessage(Notification notif) {
		if (this.level != null
				&& this.level.compareTo(notif.getSeverity()) <= 0) {
			MessageEntry msg = new MessageEntry();
			msg.group = notif.getType().getGroup().getName();
			msg.name = notif.getType().getName();
			msg.severity = notif.getSeverity();
			msg.params = String.valueOf(notif.getAttributes());
			data.add(msg);
		}
	}

	// public void addMessages(Severity severity, String... messages) {
	// List<MessageEntry> collection = new ArrayList<MessageEntry>(
	// messages.length);
	// for (String message : messages) {
	// addMessage(severity, message);
	// }
	// data.addAll(collection);
	// }

	public static final class MessageEntry {

		private Severity severity;
		private String group;
		private String name;
		private String params;
		private long timestamp = System.currentTimeMillis();
		private static Map<Severity, Image> images = new HashMap<Severity, Image>();

		static {
			images.put(
					Severity.ALARM,
					new Image(MessageEntry.class
							.getResourceAsStream("/img/16x16/Alert.png")));
			images.put(
					Severity.FATAL,
					new Image(MessageEntry.class
							.getResourceAsStream("/img/16x16/Stop.png")));
			images.put(
					Severity.ERROR,
					new Image(MessageEntry.class
							.getResourceAsStream("/img/16x16/error.gif")));
			images.put(
					Severity.WARNING,
					new Image(MessageEntry.class
							.getResourceAsStream("/img/16x16/warning.png")));
			images.put(
					Severity.INFO,
					new Image(MessageEntry.class
							.getResourceAsStream("/img/16x16/Info.png")));
			images.put(
					Severity.DEBUG,
					new Image(MessageEntry.class
							.getResourceAsStream("/img/16x16/debug.gif")));
		}

		public Image getImage() {
			return images.get(this.severity);
		}

		public String getGroup() {
			return this.group;
		}

		public String getName() {
			return this.name;
		}

		public String getParameters() {
			return this.params;
		}
	}

	// public static class MessagePaneTester extends PaneTester {
	// @Override
	// protected Pane createPane() {
	// MessagePane pane = new MessagePane();
	// pane.addMessage(Severity.INFO, "An info message.");
	// pane.addMessage(Severity.DEBUG, "A debug message.");
	// pane.addMessage(Severity.ERROR, "An error message.");
	// pane.addMessage(Severity.INFO, "An info message.");
	// pane.addMessage(Severity.WARNING, "A warning message.");
	// pane.addMessage(Severity.INFO, "An info message.");
	// pane.addMessage(Severity.INFO, "An info message.");
	// pane.addMessage(Severity.FATAL, "An fatal message.");
	// pane.addMessage(Severity.ERROR, "An error message.");
	// pane.addMessage(Severity.INFO, "An info message.");
	// pane.addMessage(Severity.DEBUG, "A debug message.");
	// pane.addMessage(Severity.ERROR, "An error message.");
	// pane.addMessage(Severity.INFO, "An info message.");
	// pane.addMessage(Severity.DEBUG, "A debug message.");
	// pane.addMessage(Severity.DEBUG, "A debug message.");
	// pane.addMessage(Severity.DEBUG, "A debug message.");
	// pane.addMessage(Severity.ERROR, "An error message.");
	// return pane;
	// }
	// }

	@Override
	public void notified(Notification notif) {
		this.addMessage(notif);
	}

	public void clearMessages() {
		data.clear();
	}

	public void clearMessages(Severity severity) {
		List<MessageEntry> collection = new ArrayList<MessageEntry>(data.size());
		for (MessageEntry msg : this.data) {
			if (msg.severity.equals(severity)) {
				collection.add(msg);
			}
		}
		data.removeAll(collection);
	}

	public void clearMessages(long duration) {
		List<MessageEntry> collection = new ArrayList<MessageEntry>(data.size());
		long min = System.currentTimeMillis() - duration;
		for (MessageEntry msg : this.data) {
			if (msg.timestamp < min) {
				collection.add(msg);
			}
		}
		data.removeAll(collection);
	}

	public void setStatus(String status) {
		this.status = status;
		WorkbenchCommands.setStatus(status);
	}

	public String getStatus() {
		return this.status;
	}

	public Object getSelectedItem() {
		return this.selectedItem;
	}

	public Object setSelectedItem(Object source, Object item) {
		Object oldItem = this.selectedItem;
		this.selectedItem = item;
//		CurrentSelection.set(item); // TODO
		return oldItem;
	}

}
