package tr.customteam.point_time.core;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDriver {
	private WebDriver driver;
	private ChromeOptions options;
	
	public WDriver(boolean headLess) {
		options = buildOptions(headLess);
		
		driver = new ChromeDriver(options);
	}
	
	public WebDriver getWebriver() {
		return driver;
	}
	
	private ChromeOptions buildOptions(boolean headLess) {
		File fDrive;
		ChromeOptions opt = new ChromeOptions();
		
		opt.addArguments("--disable-extensions");
		opt.addArguments("--test-type");
		opt.addArguments("--ignore-certificate-errors");
		opt.addArguments("--no-sandbox");
		opt.addArguments("--disable-dev-shm-usage");
		
		if(headLess) {
			opt.addArguments("--headless");// hide
		}
		
		fDrive = new File("target\\classes\\core\\assets\\chromedriver.exe");
		
		System.out.println(fDrive.getAbsolutePath());
		
		
		if(fDrive.exists()) {
			System.out.println("Drive exists");
			opt.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.driver", fDrive.getAbsolutePath());
		}
		
		return opt;
	}
	
	public static boolean waitForPageLoaded(WebDriver driver, String msg) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);

			return true;
		} catch (Exception e) {
			
		}

		return false;
	}

	public void close() {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao fechar o driver");
		}		
	}

}
