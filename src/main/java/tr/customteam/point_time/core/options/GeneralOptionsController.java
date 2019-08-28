package tr.customteam.point_time.core.options;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import tr.customteam.point_time.core.App;
import tr.customteam.point_time.core.SaveEvent;
import tr.customteam.point_time.viewmanager.ISubController;

public class GeneralOptionsController implements ISubController, Initializable {
	
	@FXML
    private AnchorPane generalOptionsPane;

    @FXML
    private JFXTextField tfSummaryEmail;

    @FXML
    private JFXPasswordField tfSummaryEmailPass;

    @FXML
    private AnchorPane modulesGroup;

    @FXML
    private JFXToggleButton tgJiraMytime;

    @FXML
    private Label lblJiraMytime;

    @FXML
    private JFXToggleButton tgFolhaCerta;

    @FXML
    private Label lblFolhaCerta;
	
    private OptionsProfile optProfile;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		optProfile = (OptionsProfile) OptionsManager.loadOption(getClass().getResource("/core/config").getPath(), "profile", OptionsProfile.class);
		setUp();
		initFields();
	}
	
	private void setUp() {
		tgJiraMytime.selectedProperty().addListener((obs, oldValue, newValue) -> {
			OptionsController optController =  (OptionsController) App.viewManager.getView("options").getController();
			
			if(newValue) {
				lblJiraMytime.getStyleClass().add("toogled");
				optController.setMenuIconVisible("jira", true);
			}else {
				lblJiraMytime.getStyleClass().remove("toogled");
				optController.setMenuIconVisible("jira", false);
			}
			tgJiraMytime.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		tgFolhaCerta.selectedProperty().addListener((obs, oldValue, newValue) -> {
			OptionsController optController =  (OptionsController) App.viewManager.getView("options").getController();
			
			if(newValue) {
				lblFolhaCerta.getStyleClass().add("toogled");
				optController.setMenuIconVisible("fc", true);
			}else {
				lblFolhaCerta.getStyleClass().remove("toogled");
				optController.setMenuIconVisible("fc", false);
			}
			tgFolhaCerta.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
	}
	
	private void initFields() {
		tfSummaryEmail.setText(optProfile.getSummaryEmail());
		tfSummaryEmailPass.setText(optProfile.getSummaryEmailPass());
		tgJiraMytime.setSelected(optProfile.isJiraModule());
		tgFolhaCerta.setSelected(optProfile.isFolhaCertaModule());
	}

	@Override
	public Object getData() {
		optProfile.setSummaryEmail(tfSummaryEmail.getText());
		optProfile.setSummaryEmailPass(tfSummaryEmailPass.getText());
		optProfile.setJiraModule(tgJiraMytime.isSelected());
		optProfile.setFolhaCertaModule(tgFolhaCerta.isSelected());
		
		return null;
	}

}
