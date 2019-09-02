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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tr.customteam.point_time.core.SaveEvent;
import tr.customteam.point_time.viewmanager.ISubController;

public class FCOptionsController implements ISubController, Initializable {


	@FXML
    private AnchorPane fcOptionPane;

    @FXML
    private JFXTextField tfFolhaCertaUsername;

    @FXML
    private JFXPasswordField tfFolhaCertaPass;

    @FXML
    private AnchorPane reminderGroup;

    @FXML
    private JFXToggleButton tgFCReminder;

    @FXML
    private Label lblFCReminder;

    @FXML
    private AnchorPane reminderOptionsGroup;

    @FXML
    private JFXComboBox<String> cbFCStartTime;

    @FXML
    private JFXComboBox<String> cbFCLunchTime;

    @FXML
    private JFXComboBox<String> cbFCLeaveTime;

    @FXML
    private JFXComboBox<String> cbFCMinutesToReminder;
    
    @FXML
    private AnchorPane summaryGroup;
    
    @FXML
    private JFXToggleButton tgFCSummary;

    @FXML
    private Label lblFCSummary;
    
    private OptionsProfile optProfile;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		optProfile = (OptionsProfile) OptionsManager.loadOption(getClass().getResource("/core/config").getPath(), "profile", OptionsProfile.class);	
		setUp();
		initFields();
	}
	
	private void setUp() {
		tgFCReminder.selectedProperty().addListener((obs, oldValue, newValue) -> {
			System.out.println(newValue);
			if(newValue) {
				lblFCReminder.getStyleClass().add("toogled");
				reminderGroup.getStyleClass().add("toogle-group");
				
				reminderGroup.setPrefWidth(260);
				reminderGroup.setPrefHeight(142);
				reminderOptionsGroup.setPrefWidth(260);
				reminderOptionsGroup.setPrefHeight(100);
				reminderOptionsGroup.setVisible(true);
				
				for(Node item: reminderOptionsGroup.getChildren()) {
					JFXComboBox<String> cb = (JFXComboBox<String>) item;
					
					cb.setPrefWidth(115);
					cb.setPrefHeight(25);
					cb.setVisible(true);
				}
				
				summaryGroup.setLayoutY(370);
				
			}else {
				lblFCReminder.getStyleClass().remove("toogled");
				reminderGroup.getStyleClass().remove("toogle-group");
				
				reminderGroup.setPrefWidth(0);
				reminderGroup.setPrefHeight(0);
				reminderOptionsGroup.setPrefWidth(0);
				reminderOptionsGroup.setPrefHeight(0);
				reminderOptionsGroup.setVisible(false);
				
				for(Node item: reminderOptionsGroup.getChildren()) {
					JFXComboBox<String> cb = (JFXComboBox<String>) item;
					
					cb.setPrefWidth(0);
					cb.setPrefHeight(0);
					cb.setVisible(false);
				}
				
				summaryGroup.setLayoutY(240);
			}
			
			tgFCReminder.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		tgFCSummary.selectedProperty().addListener((obs, oldValue, newValue) -> {
			if(newValue) {
				lblFCSummary.getStyleClass().add("toogled");
			}else {
				lblFCSummary.getStyleClass().remove("toogled");
			}
			
			tgFCSummary.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		//verify changes
		tfFolhaCertaUsername.textProperty().addListener((obs, oldValue, newValue) -> {
			tfFolhaCertaUsername.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		tfFolhaCertaPass.textProperty().addListener((obs, oldValue, newValue) -> {
			tfFolhaCertaPass.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		cbFCStartTime.valueProperty().addListener((obs, oldValue, newValue) -> {
			cbFCStartTime.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});

		cbFCLunchTime.valueProperty().addListener((obs, oldValue, newValue) -> {
			cbFCLunchTime.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		cbFCLeaveTime.valueProperty().addListener((obs, oldValue, newValue) -> {
			cbFCLeaveTime.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
		cbFCMinutesToReminder.valueProperty().addListener((obs, oldValue, newValue) -> {
			cbFCMinutesToReminder.fireEvent(new SaveEvent(SaveEvent.SAVE));
		});
		
	}
	
	public void initFields(){
		tfFolhaCertaUsername.setText(optProfile.getFolhaCertaUsername());
		tfFolhaCertaPass.setText(optProfile.getFolhaCertaPassWord());
		tgFCReminder.setSelected(true);
		tgFCReminder.setSelected(optProfile.isFolhaCertaActiveReminder());
		tgFCSummary.setSelected(optProfile.isFolhaCertaActiveSummary());
		
		for(int i = 0; i < 24; i++) {
			String hour = i < 10 ? "0" + i + ":00" : i + ":00";
			String minutes = i < 10 ? "0" + i : Integer.toString(i);
			
			cbFCStartTime.getItems().add(hour);
			cbFCLunchTime.getItems().add(hour);
			cbFCLeaveTime.getItems().add(hour);
			cbFCMinutesToReminder.getItems().add(minutes);
		}
		
		cbFCStartTime.setValue(optProfile.getFolhaCertaStartTime());
		cbFCLunchTime.setValue(optProfile.getFolhaCertaLunchtTime());
		cbFCLeaveTime.setValue(optProfile.getFolhaCertaLeaveTime());
		cbFCMinutesToReminder.setValue(optProfile.getFolhaCertaMinutesToReminder());
	}
	
	@Override
	public Object getData() {
		optProfile.setFolhaCertaUsername(tfFolhaCertaUsername.getText());
		optProfile.setFolhaCertaPassWord(tfFolhaCertaPass.getText());
		optProfile.setFolhaCertaActiveReminder(tgFCReminder.isSelected());
		optProfile.setFolhaCertaStartTime(cbFCStartTime.getValue());
		optProfile.setFolhaCertaLunchtTime(cbFCLunchTime.getValue());
		optProfile.setFolhaCertaLeaveTime(cbFCLeaveTime.getValue());
		optProfile.setFolhaCertaMinutesToReminder(cbFCMinutesToReminder.getValue());
		optProfile.setFolhaCertaActiveSummary(tgFCSummary.isSelected());
		
		System.out.println(cbFCStartTime.getValue());
		System.out.println(cbFCLunchTime.getValue());
		System.out.println(cbFCLeaveTime.getValue());
		System.out.println(cbFCMinutesToReminder.getValue());
		
		return null;
	}

}
