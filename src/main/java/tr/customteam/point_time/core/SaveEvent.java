package tr.customteam.point_time.core;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.stage.WindowEvent;

public class SaveEvent extends Event {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8945738889875193435L;
	
	public static final EventType<SaveEvent> ANY = 
			new EventType<SaveEvent>(Event.ANY, "SAVE_EVENT");
	
	public static final EventType<SaveEvent> SAVE = 
			new EventType<SaveEvent>(SaveEvent.ANY, "SAVE");
	
	public static final EventType<SaveEvent> HIDE_SAVE = 
			new EventType<SaveEvent>(SaveEvent.ANY, "HIDE_SAVE");	
	
	public SaveEvent(EventType<? extends Event> eventType) {
		super(eventType);
	}
	
	public SaveEvent(Object source, EventType<? extends Event> eventType) {
		 super(source, null, eventType);
	}
	
	public SaveEvent copyFor(Object newSource, EventTarget newTarget) {
		return (SaveEvent) super.copyFor(newSource, newTarget);
	}
	
}
