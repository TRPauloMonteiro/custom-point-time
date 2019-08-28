package tr.customteam.point_time.core.options;

public class OptionsGeneral implements IOptions{
	private String name;
	private boolean firstRun;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFirstRun() {
		return firstRun;
	}
	public void setFirstRun(boolean firtRun) {
		this.firstRun = firtRun;
	}
	
}
