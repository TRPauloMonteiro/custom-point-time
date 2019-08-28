package tr.customteam.point_time.core.components;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tr.customteam.point_time.core.App;
import tr.customteam.point_time.viewmanager.IController;
import tr.customteam.point_time.viewmanager.LoadViewException;
import tr.customteam.point_time.viewmanager.ViewManager;

public class GlobalHeaderController implements IController, Initializable {
	
	@FXML
    private AnchorPane globalHeader;

    @FXML
    private Pane ghTitleWrap;

    @FXML
    private Label brandTitle;

    @FXML
    private Label productTitle;

    @FXML
    private HBox ghRightButtonsWrap;

    @FXML
    private MaterialDesignIconView ghHomeBtn;

    @FXML
    private MaterialDesignIconView ghHistBtn;

    @FXML
    private MaterialDesignIconView ghSettingsBtn;

    @FXML
    private MaterialDesignIconView ghExitBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUp();		
	}
	
	private void setUp() {
		setUpTitle();
		setUpSettingBtn();
		
		System.out.println(App.viewManager.getCurrentView());
		
		if(App.viewManager.getCurrentView().equalsIgnoreCase("dashBoard")) {
			setCurrentIcon("ghHomeBtn");
		}else if(App.viewManager.getCurrentView().equalsIgnoreCase("options")) {
			setCurrentIcon("ghSettingsBtn");
		}
		
		ghHomeBtn.setOnMouseClicked(e -> {
			//todo
			if(!App.viewManager.getCurrentView().equalsIgnoreCase("dashBoard")) {
				setCurrentIcon("ghHomeBtn");
				
				try {
					App.viewManager.load("dashBoard");
					App.viewManager.setCurrentView("dashBoard");
				} catch (LoadViewException e1) {
					e1.printStackTrace();
				}
			}	
		});
		
		ghExitBtn.setOnMouseClicked(e -> {
			ghExitBtn.fireEvent(new GlobalHeaderEvent(GlobalHeaderEvent.EXIT_APP));
		});
		
		ghSettingsBtn.setOnMouseClicked(e -> {
			//todo
			if(!App.viewManager.getCurrentView().equals("options")) {
				setCurrentIcon("ghSettingsBtn");
				
				try {
					App.viewManager.load("options");
					App.viewManager.load("fcOptions");
					App.viewManager.setCurrentView("options");
				} catch (LoadViewException e1) {
					e1.printStackTrace();
				}
			}			
		});
		
		Tooltip tooltip = new Tooltip("Tela Inicial");
		tooltip.setShowDelay(Duration.seconds(.5));
		tooltip.setStyle("-fx-font-size: 12");		
		Tooltip.install(ghHomeBtn, tooltip);
		
		tooltip = new Tooltip("Opções do APP");
		tooltip.setShowDelay(Duration.seconds(.5));
		tooltip.setStyle("-fx-font-size: 12");
		Tooltip.install(ghSettingsBtn,tooltip);
		
		tooltip = new Tooltip("Sair do APP");
		tooltip.setShowDelay(Duration.seconds(.5));
		tooltip.setStyle("-fx-font-size: 12");
		Tooltip.install(ghExitBtn, tooltip);
	}
	
	private void setUpTitle() {
		brandTitle.setText("Custom Team");
		productTitle.setText("JiraPoint");
	}
	
	private void setUpSettingBtn() {
		ghSettingsBtn.setOnMouseEntered(e -> {
			RotateTransition rotateTransition = new RotateTransition();
			rotateTransition.setNode(ghSettingsBtn);
			rotateTransition.setByAngle(90);
			rotateTransition.setDuration(Duration.seconds(.3));
			//rotateTransition.setRate(10);
			rotateTransition.play();
			
			//new RotateIn(ghSettingsBtn).play();
		});
	}
	
	private void setCurrentIcon(String id) {
		for(Node itemIcon: ghRightButtonsWrap.getChildren()) {
			itemIcon.getStyleClass().remove("selected");
			
			if(itemIcon.getId().equals(id)) {
				itemIcon.getStyleClass().add("selected");
			}
		}
	}

}
