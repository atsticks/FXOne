package org.fxone.ui.rt.components.events;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//
//import org.homemotion.events.EventManager;
//import org.homemotion.events.EventSender;
//import org.homemotion.events.EventDefinition;
//
//@ManagedBean
//@RequestScoped
//public final class EventDefinitionList {
//
//	@Inject
//	private EventManager manager;
//
//	private EventDefinition currentItem;
//
//	public EventDefinitionList() {
//	}
//
//	public String view() {
//		FacesContext context = FacesContext.getCurrentInstance();
////		if (this.currentItem == null) {
////			String nameString = context.getExternalContext()
////					.getRequestParameterMap().get("EventDefinitionName");
////			String sourceString = context.getExternalContext()
////					.getRequestParameterMap().get("EventDefinitionSource");
////			try {
////				this.currentItem = manager.getEventDefinition(nameString,
////						sourceString);
////			} catch (Exception e) {
////				FacesMessage message = new FacesMessage(
////						FacesMessage.SEVERITY_WARN,
////						"EventDefinition with name " + nameString
////								+ " and source " + sourceString
////								+ " was not found: " + e.getMessage(), e
////								.getMessage());
////				context.addMessage(null, message);
////			}
////		}
//		if (this.currentItem != null) {
//			return getViewTarget();
//		}
//		FacesMessage message = new FacesMessage(
//				"Could not start viewer: Object not found.");
//		context.addMessage(null, message);
//		return null;
//	}
//
//	public List<EventDefinition> getAllItems() {
//		return new ArrayList<EventDefinition>(manager.getEventDefinitions());
//	}
//
//	public EventDefinition getCurrentItem() {
//		return currentItem;
//	}
//
//	public void setCurrentItem(EventDefinition currentItem) {
//		this.currentItem = currentItem;
//	}
//
//	public String viewCurrentItem() {
//		if (currentItem != null) {
//			return getViewTarget();
//		}
//		return null;
//	}
//
//	protected String getViewTarget() {
//		return null;
//	}
//
//	public String close() {
//		this.currentItem = null;
//		return "EventUI";
//	}
//
//}
