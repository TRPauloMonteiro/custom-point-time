package tr.customteam.point_time.core.modules;

public enum AppointmentsTime {
	START(1), LUNCH(2), LUNCH_RETURN(3), LEAVE(4);
	
	private final int value;
	
	private AppointmentsTime(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
}
