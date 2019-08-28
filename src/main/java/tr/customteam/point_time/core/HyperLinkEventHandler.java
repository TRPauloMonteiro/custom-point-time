package tr.customteam.point_time.core;

import javafx.event.EventHandler;

public class HyperLinkEventHandler implements EventHandler<HyperLinkEvent> {

	@Override
	public void handle(HyperLinkEvent event) {
		System.out.println(event.toString());		
	}

}
