package tr.customteam.point_time.viewmanager;

public class ViewBuilder {
	
	private String name;
	private String fxmlPath;
	private String paneToLoad;
	private String styleSheetPath;
	private IController controller;
	private boolean readyOnLoad;
	
	public ViewBuilder (String viewName) {
		this.name = viewName;
	}
	
	public ViewBuilder withController(IController controller) {
		this.controller = controller;
		return this;
	}
	
	public ViewBuilder withFXML(String fxmlPath) {
		this.fxmlPath = fxmlPath;
		return this;
	}
	
	public ViewBuilder withStyleSheet(String styleSheet) {
		this.styleSheetPath = styleSheet;
		return this;
	}
	
	public ViewBuilder paneToLoad(String paneToLoad) {
		this.paneToLoad = paneToLoad;
		return this;
	}
	
	public ViewBuilder readyOnLoad(boolean readyOnLoad) {
		this.readyOnLoad = readyOnLoad;
		return this;
	}
	
	public ViewBean build() {
		var view = new ViewBean();
		
		view.setName(name);
		view.setController(controller);
		view.setFxmlPath(fxmlPath);
		view.setStyleSheetPath(styleSheetPath);
		view.setPaneToLoad(paneToLoad);
		view.setReadyOnLoad(readyOnLoad);
		
		return view;
	}
}
