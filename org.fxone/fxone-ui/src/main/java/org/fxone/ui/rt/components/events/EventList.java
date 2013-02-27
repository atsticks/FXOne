package org.fxone.ui.rt.components.events;
//
//import java.util.List;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//
//import org.homemotion.events.AbstractEvent;
//import org.homemotion.events.EventManager;
//import org.homemotion.events.PersistableEvent;
//import org.homemotion.events.impl.EventCollector;
//
//@ManagedBean
//@SessionScoped
//public final class EventList {
//
//	@Inject
//	private EventManager manager;
//	
//	@Inject
//	private EventCollector collector;
//	
//	
//	private AbstractEvent currentItem;
//	
//	public EventList(){
//	}
//	
//	public String view() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		if (this.currentItem != null) {
//			return getViewTarget();
//		}
//		FacesMessage message = new FacesMessage("Could not start viewer: Object not found.");
//		context.addMessage(null,message);
//		return null;
//	}
//
//	
//	
//	public List<PersistableEvent> getPersistentItems() {
//		return manager.getAllItems();
//	}
//	
//	public List<AbstractEvent> getAllItems() {
//		return collector.getCurrentEvents();
//	}
//
//	public AbstractEvent getCurrentItem() {
//		return currentItem;
//	}
//
//	public void setCurrentItem(AbstractEvent currentItem) {
//		this.currentItem = currentItem;
//	}
//
//	public String viewCurrentItem() {
//		if(currentItem!=null){
//			return getViewTarget();
//		}
//		return null;
//	}
//	
//	protected String getViewTarget() {
//		return "EventForm";
//	}
//	
//	public String close() {
//		this.currentItem = null;
//		return "EventUI";
//	}
//
//}
