package tr.customteam.point_time.core.modules;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import tr.customteam.point_time.core.WDriver;
import tr.customteam.point_time.core.options.OptionsProfile;

public class FCWebDriverService extends Service<HashMap<Integer, LocalTime>> {
	
	private WebDriver wd;
	private OptionsProfile opt;
	private HashMap<String, String> urls;
	
	public FCWebDriverService(WebDriver wd, OptionsProfile opt) {
		this.wd = wd;
		this.opt = opt;
		
		urls = new HashMap<String, String>();
		urls.put("login", "https://app.folhacerta.com/webapp2/");
	}
	
	private boolean doLogin() {
		try {
			wd.get(urls.get("login"));
			if (WDriver.waitForPageLoaded(wd, "login page")) {
				WebElement formDiv = wd.findElement(By.className("login-form"));
				WebElement inputLogin = formDiv.findElement(By.cssSelector("div.login-form > input[type='text']"));
				WebElement inputPass = formDiv.findElement(By.cssSelector("div.login-form > input[type='password']"));
				WebElement formBtn = formDiv.findElement(By.tagName("button"));

				inputLogin.sendKeys(opt.getFolhaCertaUsername());
				inputPass.sendKeys(opt.getFolhaCertaPassWord());
				formBtn.click();

				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
	
	private HashMap<Integer, LocalTime> getAppointments(){
		HashMap<Integer, LocalTime> appointments = new HashMap<Integer, LocalTime>();
		
		if(wd.getCurrentUrl().contains("marcar-ponto")) {
			List<WebElement> appointmentsList = wd.findElements(By.cssSelector("div.content-marcacao div.component-marcacao span.hora-marcacao"));
			
			if(appointmentsList != null && appointmentsList.size() > 0) {
				for(int i = 0; i < appointmentsList.size(); i++) {
					appointments.put(i, LocalTime.parse(appointmentsList.get(i).getText()));
				}
			}
		}
		
		return appointments;
	}
	
	@Override
	protected Task<HashMap<Integer, LocalTime>> createTask() {
		return new Task<HashMap<Integer, LocalTime>>(){

			@Override
			protected HashMap<Integer, LocalTime> call() throws Exception {
				if(doLogin()){
					return getAppointments();
				}else {
					System.out.println("erro login");
				}
				return null;
			}
			
		};	
	}

}
