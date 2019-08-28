package tr.customteam.point_time.core;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import animatefx.animation.BounceInUp;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXSystemTray extends Pane{
	private SystemTray tray;
	private TrayIcon trayIcon;
	private String appTittle;
	
	private Stage mainStage;
	
	public FXSystemTray(Stage mainStage){
		appTittle = "Custom Point Time";
		this.mainStage = mainStage;
		
		try {
			//make sure defaultToolkit is active			
			java.awt.Toolkit.getDefaultToolkit();
			//get the OS system tray instance
		    tray = SystemTray.getSystemTray();
		    
		    //load the image icon
		    BufferedImage image = ImageIO.read(new URL(getClass().getResource("/core/imgs/pointTime_black.png").toExternalForm()));
		    //create a trayIcon with a hint on mouse hover
		    trayIcon = new TrayIcon(image, appTittle);
		    //important without this if the image is not the right size the icon will break
		    trayIcon.setImageAutoSize(true);
		    trayIcon.setToolTip(appTittle);
	    
			tray.add(trayIcon);
			
			buildMenu();
			
			sendMessage("App Running", appTittle + " está sendo executado em background!", MessageType.INFO);
			
			//todo
			Rectangle2D screenBounds = Screen.getPrimary().getBounds();			    		
			this.mainStage.setOnShown(e -> {
    			System.out.println(this.mainStage.getWidth());
    			System.out.println(this.mainStage.getHeight());
    			
    			this.mainStage.setX(screenBounds.getWidth() - this.mainStage.getWidth());
    			this.mainStage.setY(screenBounds.getHeight() - this.mainStage.getHeight() - 40);
    			this.mainStage.setAlwaysOnTop(true);
    		});
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildMenu() {
		// if the user double-clicks on the tray icon, show the main app stage.
        trayIcon.addActionListener(event -> Platform.runLater(() -> {
        	if(mainStage.isShowing()) {
        		//when use this animation and open up again then effect become weird
        		//new BounceOutDown(mainStage.getScene().getRoot()).play();
        		mainStage.hide();        		
        	}else {
        		mainStage.show();
        		new BounceInUp(mainStage.getScene().getRoot()).play();
        	}       	
        }));
		// if the user selects the default menu item (which includes the app name), 
        // show the main app stage.
        java.awt.MenuItem openItem = new java.awt.MenuItem(appTittle);
        openItem.addActionListener(event -> Platform.runLater(() -> {
        	mainStage.show();
        }));

        // the convention for tray icons seems to be to set the default icon for opening
        // the application stage in a bold font.
        java.awt.Font defaultFont = java.awt.Font.decode(null);
        java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
        openItem.setFont(boldFont);

        // to really exit the application, the user must go to the system tray icon
        // and select the exit option, this will shutdown JavaFX and remove the
        // tray icon (removing the tray icon will also shut down AWT).
        java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
        exitItem.addActionListener(event -> {
        	removeTrayIcon();
        	Platform.exit();
            
        });

        // setup the popup menu for the application.
        final java.awt.PopupMenu popup = new java.awt.PopupMenu();
        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);
        trayIcon.setPopupMenu(popup);
	}
	
	public void sendMessage(String title, String message, MessageType type) {
		trayIcon.displayMessage(title, message, type);
	}
	
	public static boolean isSystemTraySupported() {
		return SystemTray.isSupported();
	}
	
	public boolean removeTrayIcon() {
		if(tray != null && trayIcon != null) {
			tray.remove(trayIcon);
			return true;
		}
		return false;
	}
}
