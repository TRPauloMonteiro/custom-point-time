package tr.customteam.point_time.core;

import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tr.customteam.point_time.core.components.GlobalHeaderEvent;
import tr.customteam.point_time.viewmanager.IController;
import tr.customteam.point_time.viewmanager.IControllerSave;
import tr.customteam.point_time.viewmanager.LoadViewException;
import tr.customteam.point_time.viewmanager.ViewBean;
import tr.customteam.point_time.viewmanager.ViewEvent;
import tr.customteam.point_time.viewmanager.ViewManager;

public class MainController implements IController, Initializable{
	
	@FXML
    private StackPane BFX_DialogWrap;

    @FXML
    private AnchorPane BFX_GHWrap;

    @FXML
    private AnchorPane BFX_SideMenu;

    @FXML
    private AnchorPane BFX_MainViewWarp;

    @FXML
    private AnchorPane BFX_Footer;
    
    @FXML
    private Label sectionTitle;
    
    @FXML
    private FontAwesomeIconView btnSave;
	
	private Stage mainStage;
	private HostServices hostService;
	private ViewManager viewManager;
	private FXSystemTray systemTray;
	
	public MainController(Stage mainStage, HostServices hostService, ViewManager viewManager) {
		this.mainStage = mainStage;
		this.hostService = hostService;
		this.viewManager = viewManager;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUp();
	}
	
	public void setSystemTray(FXSystemTray systemTray) {
		this.systemTray  = systemTray;
	}
	
	private void setUp() {
		sectionTitle.textProperty().bind(ViewManager.getCurrentViewProperty());
		btnSave.setVisible(false);
		
		mainStage.addEventHandler(ViewEvent.LOAD_VIEW, e -> {
			if(e.isCurrentView()) {
				viewManager.setCurrentView(e.getViewName());
			}
			
			try {
				System.out.println("Load view catch: " + e.getViewName());
				viewManager.load(e.getViewName());
			} catch (LoadViewException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		mainStage.addEventHandler(GlobalHeaderEvent.EXIT_APP, e -> {
			if(systemTray != null) {
				systemTray.removeTrayIcon();
			}
			
			Platform.exit();
		});
		
		mainStage.addEventHandler(HyperLinkEvent.OPEN_DOCUMENT, e -> {
			hostService.showDocument(e.getUrl());
		});
		
		mainStage.addEventHandler(SaveEvent.SAVE, e ->{
			System.out.println("Save Event");
			if(!btnSave.isVisible()) {
				btnSave.setVisible(true);
				new FadeIn(btnSave).play();
			}			
		});
		
		btnSave.setOnMouseClicked(e -> {
			if(onSave(ViewManager.getCurrentView().toLowerCase())) {
				new FadeOut(btnSave).play();
				btnSave.setVisible(false);
			}
			System.out.println("btnSave Clicked");
		});
		
	}
	
//	private void closeSideMenu(){
//        TranslateTransition ttCloseMenu = new TranslateTransition();
//        ttCloseMenu.setNode(BFX_SideMenu);
//
//        ttCloseMenu.setByX(BFX_SideMenu.getPrefWidth() * -1);
//        ttCloseMenu.play();
//
//        BFX_MainViewWarp.setPrefWidth(mainStage.getMaxWidth());
//        AnchorPane.setLeftAnchor(BFX_MainViewWarp, 0.0);
//
//        BFX_Footer.setPrefWidth(mainStage.getMaxWidth());
//        AnchorPane.setLeftAnchor(BFX_Footer, 0.0);
//    }
	
	private boolean onSave(String currentView) {
		System.out.println(currentView);
		ViewBean view = ViewManager.getView(currentView);
		System.out.println(view);
		IControllerSave controller = (IControllerSave) view.getController();
		
		return controller.onSave();
	}
}
