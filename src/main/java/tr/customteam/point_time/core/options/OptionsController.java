package tr.customteam.point_time.core.options;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tr.customteam.point_time.core.SaveEvent;
import tr.customteam.point_time.viewmanager.IControllerSave;
import tr.customteam.point_time.viewmanager.ISubController;
import tr.customteam.point_time.viewmanager.ViewEvent;
import tr.customteam.point_time.viewmanager.ViewManager;

public class OptionsController implements IControllerSave, Initializable {
	
    @FXML
    private AnchorPane optionsPane;

    @FXML
    private VBox optionsMenu;

    @FXML
    private ImageView generalMenuIcon;
    
    @FXML
    private ImageView fcMenuIcon;

    @FXML
    private ImageView jiraMenuIcon;

    @FXML
    private AnchorPane optionsWrap;
    
    private OptionsProfile optProfile;
    private List<String> selectOptionsPane = new ArrayList<String>();
    private SimpleStringProperty selectedMenu;
    private HashMap<String, Image> imageCache;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		optProfile = (OptionsProfile) OptionsManager.loadOption(getClass().getResource("/core/config").getPath(), "profile", OptionsProfile.class);	
		imageCache = new HashMap<String, Image>();
		
		setUp();
	}
	
	private void setUp() {
		setUpMenu();
	}
	
	private void setUpMenu() {
		if(optProfile.isJiraModule()) {
			setMenuIconVisible("jira", true);
		}else {
			setMenuIconVisible("jira", false);
		}
		
		if(optProfile.isFolhaCertaModule()) {
			setMenuIconVisible("fc", true);
		}else {
			setMenuIconVisible("fc", false);
		}
		
		selectedMenu = new SimpleStringProperty();
		selectedMenu.addListener((obs, oldText, newText) -> {
			Image imageIcon;
			ImageView menuIcon;
			ObservableList<Node> itemChildren;
			
			ObservableList<Node> menuItens = optionsMenu.getChildren();
			for(Node menuItem: menuItens) {
				StackPane item = (StackPane) menuItem;
				if(item.getStyleClass().contains(newText)) {					
					itemChildren = item.getChildren();
					
					for(Node child: itemChildren) {
						menuIcon = (ImageView) child;
						
						item.getStyleClass().add("selected");
						
						if(imageCache.containsKey(newText + "_orange")) {
							imageIcon = imageCache.get(newText + "_orange");
						}else {
							imageIcon = new Image(getClass().getResource("/core/imgs/" + newText + "_logo_orange.png").toString());
							imageCache.put(newText, imageIcon);
						}					
						
						menuIcon.setImage(imageIcon);
						//tell main controller to load the view
						item.fireEvent(new ViewEvent(ViewEvent.LOAD_VIEW, newText + "Options", false));
						
						if(!selectOptionsPane.contains(newText)) {
							selectOptionsPane.add(newText);
						}
					}
				}else {
					if(item.getStyleClass().contains("selected")) {
						itemChildren = item.getChildren();
						
						for(Node child: itemChildren) {
							menuIcon = (ImageView) child;
									
							item.getStyleClass().remove("selected");
							
							if(imageCache.containsKey(oldText + "_white")) {
								imageIcon = imageCache.get(oldText + "_white");
							}else {
								imageIcon = new Image(getClass().getResource("/core/imgs/" + oldText + "_logo_white.png").toString());
								imageCache.put(newText, imageIcon);
							}
							
							menuIcon.setImage(imageIcon);
						}
						
						onSave();
						optionsMenu.fireEvent(new SaveEvent(SaveEvent.HIDE_SAVE));
					}
				}
			}			
		});
		selectedMenu.set("general");
		
		generalMenuIcon.setOnMouseClicked(e -> {
			selectedMenu.set("general");
		});
		
		fcMenuIcon.setOnMouseClicked(e -> {
			selectedMenu.set("fc");
		});
		
		jiraMenuIcon.setOnMouseClicked(e -> {
			selectedMenu.set("jira");
		});
		
		Tooltip.install(generalMenuIcon, new Tooltip("Opções gerais"));
		Tooltip.install(fcMenuIcon, new Tooltip("Opções módulo Folha Certa"));
		Tooltip.install(jiraMenuIcon, new Tooltip("Opções módulo Jira"));
	}
	
	public void setMenuIconVisible(String iconName, boolean isVisible) {
		if(isVisible) {
			StackPane wrap;
			if(iconName.equalsIgnoreCase("jira")) {
				wrap = (StackPane) jiraMenuIcon.getParent();
				jiraMenuIcon.setVisible(true);				
			}else {
				wrap = (StackPane) fcMenuIcon.getParent();
				fcMenuIcon.setVisible(true);	
			}
			
			wrap.setPrefHeight(60);
			wrap.setPrefWidth(60);
			
		}else {
			StackPane wrap;
			if(iconName.equalsIgnoreCase("jira")) {
				wrap = (StackPane) jiraMenuIcon.getParent();
				jiraMenuIcon.setVisible(false);				
			}else {
				wrap = (StackPane) fcMenuIcon.getParent();
				fcMenuIcon.setVisible(false);	
			}
			
			wrap.setPrefHeight(0);
			wrap.setPrefWidth(0);
		}
	}
	
	public boolean onSave() {
		System.out.println("Save from options");
		
		if(optProfile == null) {
			optProfile = new OptionsProfile();
			OptionsManager.addOption("profile", optProfile);
			
			System.out.println("options nulo");
		}
		
		try {
			
			for(String paneOption: selectOptionsPane) {
				System.out.println(paneOption);
				ISubController optionController = (ISubController) ViewManager.getView(paneOption + "Options").getController();
				optionController.getData();
			}
			
			OptionsManager.saveOption(getClass().getResource("/core/config").getPath(), "profile");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
