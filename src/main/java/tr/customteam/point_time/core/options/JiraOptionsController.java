package tr.customteam.point_time.core.options;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import tr.customteam.point_time.core.SaveEvent;
import tr.customteam.point_time.viewmanager.ISubController;

public class JiraOptionsController implements ISubController, Initializable {
	
	@FXML
    private AnchorPane jiraOptionPane;

    @FXML
    private JFXTextField tfJiraUsername;

    @FXML
    private JFXPasswordField tfJiraPass;

    @FXML
    private AnchorPane reminderGroup;

    @FXML
    private JFXToggleButton tgJiraReminder;

    @FXML
    private Label lblJiraReminder;

    @FXML
    private JFXToggleButton tgJiraSummary;

    @FXML
    private Label lblJiraSummary;
    
    @FXML
    private AnchorPane reminderOptionsGroup;
    
    @FXML
    private JFXRadioButton rbJiraDaily;

    @FXML
    private JFXRadioButton rbJiraWeekly;
    
    @FXML
    private AnchorPane summaryGroup;
	
    private OptionsProfile optProfile;
    private ToggleGroup jiraReminderType;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		optProfile = (OptionsProfile) OptionsManager.loadOption(getClass().getResource("/core/config").getPath(), "profile", OptionsProfile.class);	
		
		setUp();		
		initFields();
	}
	
	private void setUp() {
		tgJiraReminder.selectedProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue) {
				lblJiraReminder.getStyleClass().add("toogled");
				reminderGroup.getStyleClass().add("toogle-group");
				
				reminderGroup.setPrefWidth(260);
				reminderGroup.setPrefHeight(142);
				reminderOptionsGroup.setPrefWidth(260);
				reminderOptionsGroup.setPrefHeight(100);
				reminderOptionsGroup.setVisible(true);
				
				for(Node item: reminderOptionsGroup.getChildren()) {
					JFXRadioButton rb = (JFXRadioButton) item;
					
					rb.setPrefWidth(115);
					rb.setPrefHeight(25);
					rb.setVisible(true);
				}
				
				summaryGroup.setLayoutY(370);
				
			}else {
				lblJiraReminder.getStyleClass().remove("toogled");
				reminderGroup.getStyleClass().remove("toogle-group");
				
				reminderGroup.setPrefWidth(0);
				reminderGroup.setPrefHeight(0);
				reminderOptionsGroup.setPrefWidth(0);
				reminderOptionsGroup.setPrefHeight(0);
				reminderOptionsGroup.setVisible(false);
				
				for(Node item: reminderOptionsGroup.getChildren()) {
					JFXRadioButton rb = (JFXRadioButton) item;
					
					rb.setPrefWidth(0);
					rb.setPrefHeight(0);
					rb.setVisible(false);
				}
				
				summaryGroup.setLayoutY(240);
			}
			
			tgJiraReminder.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		tgJiraSummary.selectedProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue) {
				lblJiraSummary.getStyleClass().add("toogled");
			}else {
				lblJiraSummary.getStyleClass().remove("toogled");
			}
			
			tgJiraSummary.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		//verify changes
		tfJiraUsername.textProperty().addListener((obs, oldValue, newValue) -> {
			tfJiraUsername.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		tfJiraPass.textProperty().addListener((obs, oldValue, newValue) -> {
			tfJiraPass.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
	
	}
	
	private void initFields(){
		jiraReminderType = new ToggleGroup();
		
		rbJiraDaily.setToggleGroup(jiraReminderType);
		rbJiraWeekly.setToggleGroup(jiraReminderType);
		
		if(optProfile.getJiraReminderType().equalsIgnoreCase("semanal")){
			rbJiraWeekly.setSelected(true);
		}else {
			rbJiraDaily.setSelected(true);
		}
		
		tfJiraUsername.setText(optProfile.getJiraUsername());
		tfJiraPass.setText(optProfile.getJiraPassWord());
		tgJiraReminder.setSelected(optProfile.isJiraActiveReminder());
		tgJiraSummary.setSelected(optProfile.isJiraActiveSummary());
	}
	
	@Override
	public Object getData() {
		optProfile.setJiraUsername(tfJiraUsername.getText());
		optProfile.setJiraPassWord(tfJiraPass.getText());
		optProfile.setJiraActiveReminder(tgJiraReminder.isSelected());
		optProfile.setJiraActiveSummary(tgJiraSummary.isSelected());
		
		JFXRadioButton selectedRadioButton =
		        (JFXRadioButton) jiraReminderType.getSelectedToggle();
		
		if(selectedRadioButton != null) {
			optProfile.setJiraReminderType(selectedRadioButton.getText());
		} else{
			optProfile.setJiraReminderType("");
		}
		
		return null;
	}	

}
