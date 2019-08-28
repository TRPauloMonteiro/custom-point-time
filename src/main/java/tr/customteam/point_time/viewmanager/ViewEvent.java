package tr.customteam.point_time.viewmanager;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.stage.WindowEvent;

public class ViewEvent extends Event {
	
	private String viewName;
	private boolean isCurrentView;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6497551096624037864L;
	
	public static final EventType<ViewEvent> ANY = 
			new EventType<ViewEvent>(Event.ANY, "VIEW");
	
	public static final EventType<ViewEvent> LOAD_VIEW =
			new EventType<ViewEvent>(ViewEvent.ANY, "LOAD_VIEW");
	
	public ViewEvent(EventType<? extends Event> eventType) {
		super(eventType);
		// TODO Auto-generated constructor stub
	}
	
	public ViewEvent(EventType<? extends Event> eventType, String viewName, boolean isCurrentView) {
		super(eventType);
		
		this.viewName = viewName;
		this.isCurrentView = isCurrentView;
	}
	
	 @Override
    public ViewEvent copyFor(Object newSource, EventTarget newTarget) {
        return (ViewEvent) super.copyFor(newSource, newTarget);
    }
	 
	@Override
    public EventType<ViewEvent> getEventType() {
        return (EventType<ViewEvent>) super.getEventType();
    }

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean isCurrentView() {
		return isCurrentView;
	}

	public void setCurrentView(boolean isCurrentView) {
		this.isCurrentView = isCurrentView;
	}
	
}
