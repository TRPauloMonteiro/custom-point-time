package tr.customteam.point_time.core;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import tr.customteam.point_time.viewmanager.IController;

public class DashBoardController implements IController, Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("Dash board ok");
	}

}
