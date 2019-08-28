package tr.customteam.point_time.core.components;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import tr.customteam.point_time.core.HyperLinkEvent;
import tr.customteam.point_time.viewmanager.IController;

public class FooterController implements IController, Initializable {
	
    @FXML
    private AnchorPane footer;

    @FXML
    private HBox footerIconsWrap;

    @FXML
    private FontAwesomeIconView footerBug;

    @FXML
    private Button footerBtnBug;

    @FXML
    private FontAwesomeIconView footerGitHub;

    @FXML
    private Button footerBtnGit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUP();		
	}
	
	private void setUP() {
		Tooltip gitToolTip = new Tooltip("Developed by Paulo Monteiro");
		gitToolTip.setShowDelay(Duration.seconds(0.5));
		footerBtnGit.setTooltip(gitToolTip);
		
		footerBtnGit.setOnMouseClicked(e -> {
			String url = "https://github.com/TRPauloMonteiro/custom-point-time";
			footerBtnGit.fireEvent(new HyperLinkEvent(HyperLinkEvent.OPEN_DOCUMENT, url));
		});
		
		footerBtnGit.setOnMouseEntered(e -> {
			Color c = Color.web("0xFF8000", 1.0);   
			footerGitHub.setFill(c);
		});
		
		footerBtnGit.setOnMouseExited(e -> {
			Color c = Color.web("0xFFFFFF", 1.0);   
			footerGitHub.setFill(c);
		});
		
		Tooltip bugToolTip = new Tooltip("Report a Bug! Not available yet!");
		bugToolTip.setShowDelay(Duration.seconds(0.5));
		footerBtnBug.setTooltip(bugToolTip);
		
		footerBtnBug.setOnMouseClicked(e -> {
			System.out.println("Report a bug!");
		});
		
		footerBtnBug.setOnMouseEntered(e -> {
			Color c = Color.web("0xFFFFFF", 1.0);   
			footerBug.setFill(c);
		});
		
		footerBtnBug.setOnMouseExited(e -> {
			Color c = Color.web("0x606168", 1.0);   
			footerBug.setFill(c);
		});
	}

}
