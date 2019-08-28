package tr.customteam.point_time.core.components;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class GlobalHeaderEvent extends Event{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2681071426214111338L;

	public static final EventType<GlobalHeaderEvent> ANY = 
			new EventType<GlobalHeaderEvent>(Event.ANY, "GLOBALHEADER");
	
	public static final EventType<GlobalHeaderEvent> EXIT_APP = 
			new EventType<GlobalHeaderEvent>(GlobalHeaderEvent.ANY, "EXIT_APP");
	
	public static final EventType<GlobalHeaderEvent> OPEN_MENU = 
			new EventType<GlobalHeaderEvent>(GlobalHeaderEvent.ANY, "OPEN_MENU");

	public GlobalHeaderEvent(EventType<? extends Event> eventType) {
		super(eventType);
	}
	
	public GlobalHeaderEvent(Object source, EventType<? extends Event> eventType) {
       super(source, (EventTarget) source, eventType);
    }	
	
	public GlobalHeaderEvent copyFor(Object newSource, EventTarget newTarget) {
		return (GlobalHeaderEvent) super.copyFor(newSource, newTarget);
	}
}
