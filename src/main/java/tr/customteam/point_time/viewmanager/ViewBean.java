package tr.customteam.point_time.viewmanager;

public class ViewBean {
	private String name;
	private String fxmlPath;
	private String paneToLoad;
	private String styleSheetPath;
	private IController controller;
	private boolean readyOnLoad;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFxmlPath() {
		return fxmlPath;
	}
	public void setFxmlPath(String fxmlPath) {
		this.fxmlPath = fxmlPath;
	}
	public String getPaneToLoad() {
		return paneToLoad;
	}
	public void setPaneToLoad(String paneToLoad) {
		this.paneToLoad = paneToLoad;
	}
	public String getStyleSheetPath() {
		return styleSheetPath;
	}
	public void setStyleSheetPath(String styleSheetPath) {
		this.styleSheetPath = styleSheetPath;
	}
	public IController getController() {
		return controller;
	}
	public void setController(IController controller) {
		this.controller = controller;
	}
	public boolean getReadyOnLoad() {
		return readyOnLoad;
	}
	public void setReadyOnLoad(boolean readyOnLoad) {
		this.readyOnLoad = readyOnLoad;
	}	
}
