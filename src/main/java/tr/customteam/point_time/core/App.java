package tr.customteam.point_time.core;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import tr.customteam.point_time.core.components.FooterController;
import tr.customteam.point_time.core.components.GlobalHeaderController;
import tr.customteam.point_time.core.options.FCOptionsController;
import tr.customteam.point_time.core.options.GeneralOptionsController;
import tr.customteam.point_time.core.options.JiraOptionsController;
import tr.customteam.point_time.core.options.OptionsController;
import tr.customteam.point_time.core.options.OptionsGeneral;
import tr.customteam.point_time.core.options.OptionsManager;
import tr.customteam.point_time.viewmanager.LoadViewException;
import tr.customteam.point_time.viewmanager.ViewBuilder;
import tr.customteam.point_time.viewmanager.ViewManager;

/**
 * JavaFX App
 */
public class App extends Application {
	public static ViewManager viewManager = new ViewManager();	
	
	@Override
	public void init() throws Exception {        
		OptionsManager.loadOption(getClass().getResource("/core/config").getPath(), "general", OptionsGeneral.class);
		super.init();
	}
	
    @Override
    public void start(Stage stage) {
    	OptionsGeneral optionGeneral = (OptionsGeneral) OptionsManager.getOption("general");
    	
    	viewManager
    		.addView(
				"main", 
				new ViewBuilder("main")
					.withFXML("/core/views/main.fxml")
					.withController(new MainController(stage, getHostServices(), viewManager))
					.withStyleSheet("/core/styles/main.css")
				    .paneToLoad("main")
				    .readyOnLoad(false)
				    .build()
			)		
    		.addView(
				"globalHeader", 
				new ViewBuilder("globalHeader")
					.withFXML("/core/views/globalHeader.fxml")
					.withController(new GlobalHeaderController())
					.withStyleSheet("/core/styles/globalHeader.css")
				    .paneToLoad("BFX_GHWrap")
				    .build()
			)
    		.addView(
    				"footer", 
    				new ViewBuilder("footer")
    					.withFXML("/core/views/footer2.fxml")
    					.withController(new FooterController())
    					.withStyleSheet("/core/styles/footer.css")
    				    .paneToLoad("BFX_Footer")
    				    .build()
    			)
    		.addView(
    				"dashBoard", 
    				new ViewBuilder("dashBoard")
    					.withFXML("/core/views/dashboard.fxml")
    					.withController(new DashBoardController())
    					.withStyleSheet("/core/styles/main.css")
    				    .paneToLoad("BFX_MainViewWarp")
    				    .build()
    			)
    		.addView(
    				"options", 
    				new ViewBuilder("options")
    					.withFXML("/core/views/options.fxml")
    					.withController(new OptionsController())
    					.withStyleSheet("/core/styles/options.css")
    				    .paneToLoad("BFX_MainViewWarp")
    				    .build()
    			)
    		.addView(
    				"generalOptions", 
    				new ViewBuilder("generalOptions")
    					.withFXML("/core/views/general_options.fxml")
    					.withController(new GeneralOptionsController())
    					.withStyleSheet("/core/styles/options.css")
    				    .paneToLoad("optionsWrap")
    				    .build()
    			)
    		.addView(
    				"fcOptions", 
    				new ViewBuilder("fcOptions")
    					.withFXML("/core/views/fc_options.fxml")
    					.withController(new FCOptionsController())
    					.withStyleSheet("/core/styles/options.css")
    				    .paneToLoad("optionsWrap")
    				    .build()
    			)
    		.addView(
    				"jiraOptions", 
    				new ViewBuilder("jiraOptions")
    					.withFXML("/core/views/jira_options.fxml")
    					.withController(new JiraOptionsController())
    					.withStyleSheet("/core/styles/options.css")
    				    .paneToLoad("optionsWrap")
    				    .build()
    			)
    		.init(stage); 
    	
    	try {
			if(optionGeneral.isFirstRun()) {
				viewManager.load("options");
				ViewManager.setCurrentView("options");
				viewManager.load("generalOptions");
			}else {
				viewManager.load("dashBoard");
				ViewManager.setCurrentView("dashBoard");
			}
			
			viewManager.load("globalHeader");
			viewManager.load("footer");
		} catch (LoadViewException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCause());
		}
    	
    	if(FXSystemTray.isSystemTraySupported()) {
    		FXSystemTray tray = new FXSystemTray(stage);
    		MainController mainController = (MainController) viewManager.getView("main").getController();
    		mainController.setSystemTray(tray);
    		
    		Platform.setImplicitExit(false);    		
    	}else {
    		stage.show();
    	}
    }

    public static void main(String[] args) {
        launch();
    }

}