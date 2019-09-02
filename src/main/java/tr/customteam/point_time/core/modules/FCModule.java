package tr.customteam.point_time.core.modules;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;
import tr.customteam.point_time.core.WDriver;
import tr.customteam.point_time.core.options.OptionsProfile;
import tr.customteam.point_time.history.FCHistory;
import tr.customteam.point_time.utils.DateTimeUtil;

public class FCModule implements IModule {
	
	private HashMap<String, String> urls;
	private HashMap<String, String> reminders;
	private OptionsProfile profile;
	private boolean isRunning;
	
	private ScheduledService<Void> fcService;
	
	public FCModule(OptionsProfile profile) {
		this.profile = profile;
		
		isRunning = init();
		
		if(isRunning) {
			FCHistory history = new FCHistory();
			history.setCreatedDate(LocalDate.now());
			
			System.out.println(history.getCreatedDate());
			
			fcService = new ScheduledService<Void>() {
				
				private int count = 0;
				
				@Override
				protected Task<Void> createTask() {
					return new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							System.out.println("FC is running. Times: " + count++);
							reminder(history);
							return null;
						}
					};
				} 
			};
			
			fcService.setPeriod(Duration.minutes(15));
			fcService.start();
		}		
	}
	
	private boolean init() {
		urls = new HashMap<String, String>();
		urls.put("login", "https://app.folhacerta.com/webapp2/");
		
		reminders = new HashMap<String, String>();
		if(!profile.getFolhaCertaStartTime().isEmpty() || !profile.getFolhaCertaStartTime().isBlank()) {
			reminders.put("start",  profile.getFolhaCertaStartTime());
		}
		
		if(!profile.getFolhaCertaLunchtTime().isEmpty() || !profile.getFolhaCertaLunchtTime().isBlank()) {
			reminders.put("lunch",  profile.getFolhaCertaLunchtTime());
		}
		
		if(!profile.getFolhaCertaLeaveTime().isEmpty() || !profile.getFolhaCertaLeaveTime().isBlank()) {
			reminders.put("leave", profile.getFolhaCertaLeaveTime());
		}	
		
		if(reminders.size() > 0) {
			return true;
		}
		
		return false;
	}
	
	private boolean reminder(FCHistory history) {
		WDriver wd = new WDriver(false);
	        
	    FCWebDriverService fcWDService = new FCWebDriverService(wd.getWebriver(), profile);
     	fcWDService.setOnSucceeded(e -> {
     		history.setAppointments(fcWDService.getValue());
     		
     		System.out.println("Quantidade de apontamentos: " + history.getQtdAppointments());
     		
     		if(history.getQtdAppointments() == 0) {
     			
     		}else if(history.getQtdAppointments() == AppointmentsTime.START.value()) {
     		
     		}else if(history.getQtdAppointments() == AppointmentsTime.LUNCH.value()) {
     			
     		}else if(history.getQtdAppointments() == AppointmentsTime.LUNCH_RETURN.value()) {
     			
     		}else if(history.getQtdAppointments() >= AppointmentsTime.LEAVE.value()) {
     			
     		}
     		
     		Iterator it = reminders.entrySet().iterator();
    		
    		while (it.hasNext()) {
    	        Map.Entry pair = (Map.Entry)it.next();
    	        String reminder = pair.getKey().toString();
    	        String reminderTime = pair.getValue().toString();
    	        
    	        System.out.println(reminder + " = " + reminderTime);
    	        
    	        try {
     	        	System.out.println("Try login");
     	        	
     				if(DateTimeUtil.isValidReminderTime(reminderTime, reminderTime, profile.getFolhaCertaMinutesToReminder())) {
     					System.out.println(pair.getKey() + " is valid");
     					
     					//se nunca foi avisado
     					if(history.getReminderStatus(reminder) == -1) {
     						//verificar antes se não houve o apontamento
     						history.setReminderStatus(reminder, 1);
     					//se ja foi avisado pelo menos 1 vez
     					}else if(history.getReminderStatus(reminder) > 0){
     						System.out.println("já lembrou varias vezes");
     					}
     				};
     			} catch (ParseException pe) {
     				// TODO Auto-generated catch block
     				pe.printStackTrace();
     			}   					
    		      
    	    }     		
     		
    		wd.close();
     	});
     	fcWDService.start();   
		
		

		return true;
	}
	
	public boolean shutDown() {
		
		return true;
	}
}
