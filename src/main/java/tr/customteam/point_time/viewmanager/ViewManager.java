package tr.customteam.point_time.viewmanager;

import java.io.IOException;
import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewManager {
	private Stage mainStage;
	private Scene mainScene;
	private Pane mainPane;
	private static SimpleStringProperty currentView;
	private static HashMap<String, ViewBean> views = new HashMap<String, ViewBean>(); ;
	
	private String appName;
	
	public void init(Stage mainStage) {
		this.mainStage = mainStage;
		currentView = new SimpleStringProperty();
		currentView.set("main");
		
		if(views.get("main") != null) {
			try {
				mainScene = new Scene(loadView("main"));
				mainScene.getStylesheets().clear();
				mainScene.getStylesheets().add(getClass().getResource("/core/styles/assets.css").toExternalForm());
				//this makes scene without background
				mainScene.setFill(null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.mainStage.setScene(mainScene);
			this.mainStage.setTitle(appName);			
			this.mainStage.initStyle(StageStyle.TRANSPARENT);
			
			if(views.get("main").getReadyOnLoad()) {
				this.mainStage.show();
			}
			
		}
	}
	
	public ViewManager addView(String viewName, ViewBean view) {
		views.put(viewName, view);
		
		return this;
	}
	
	public void addAllViews(HashMap<String, ViewBean> views) {
		this.views = views;
	}
	
	public static ViewBean getView(String viewName) {
		return views.get(viewName);
	}
	
	public void load(String viewName) throws LoadViewException{
		try {
			loadView(viewName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new LoadViewException("Ocorreu um erro ao carregar a view " + viewName, e.getCause());
		}
	}
	
	public Pane loadView(String viewName) throws IOException {
		Pane viewPane = null;
		Pane loadedPane = null;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(views.get(viewName).getFxmlPath()));
		
		loader.setController(views.get(viewName).getController());
		
		//carrega o FXML no pane
		viewPane = loader.load();
		//carrega o CSS do FXML para tornar o carregamento dinamico
		viewPane.getStylesheets().add(getClass().getResource(views.get(viewName).getStyleSheetPath()).toExternalForm());
		
		if(views.get(viewName).getPaneToLoad().equals("main")) {
			loadedPane = new AnchorPane();
		}else {
			//obtem a referencia do painel que a nova view sera adicionada
			loadedPane = (Pane) mainStage.getScene().lookup("#" + views.get(viewName).getPaneToLoad());
		}
		
		/**
		 * para que a view fique responsiva os pane que são os containers as view são AnchorPane
		 * desta forma é possivel configurar o "Anchor" do pane para que este se ajuste ao tamanho
		 */
		AnchorPane.setTopAnchor(viewPane, 0.0);
		AnchorPane.setBottomAnchor(viewPane, 0.0);
		AnchorPane.setLeftAnchor(viewPane, 0.0);
		AnchorPane.setRightAnchor(viewPane, 0.0);
		
		//adiciona a view carregada ao pane
		loadedPane.getChildren().removeAll();
		loadedPane.getChildren().setAll(viewPane);
		
		return loadedPane;
	}

	 public static String getCurrentView() {
		return currentView.get();
	}
	 
	 public static SimpleStringProperty getCurrentViewProperty() {
		 return currentView;
	 }

	public static void setCurrentView(String currentView) {
		ViewManager.currentView.set(currentView.toUpperCase());
	}
	
	public static void setCurrentViewProperty(SimpleStringProperty currentView) {
		ViewManager.currentView = currentView;
	}
	
	
	
}
