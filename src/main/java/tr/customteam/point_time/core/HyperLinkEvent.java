package tr.customteam.point_time.core;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class HyperLinkEvent extends Event {
	
	private static final long serialVersionUID = 118082019L;
	
	private String url;
	
	public static final EventType<HyperLinkEvent> ANY = 
			new EventType<HyperLinkEvent>(Event.ANY, "HYPERLINK");
	
	public static final EventType<HyperLinkEvent> OPEN_DOCUMENT = 
			new EventType<HyperLinkEvent>(HyperLinkEvent.ANY, "OPEN_DOCUMENT");
	
	public HyperLinkEvent(EventType<? extends Event> eventType) {
		super(eventType);
	}
	
	public HyperLinkEvent(EventType<? extends Event> eventType, String url) {
		super(eventType);
		this.url = url;
	}	
	
	public HyperLinkEvent copyFor(Object newSource, EventTarget newTarget) {
		return (HyperLinkEvent) super.copyFor(newSource, newTarget);
	}
	
	public String getUrl() {
		return url;
	}

}
