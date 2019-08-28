package tr.customteam.point_time.core.components;

import javafx.event.EventHandler;

public class GlobalHeaderEventHandler implements EventHandler<GlobalHeaderEvent> {

	@Override
	public void handle(GlobalHeaderEvent event) {
		System.out.println(event.toString());
	}

}
