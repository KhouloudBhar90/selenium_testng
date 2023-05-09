package com.todos.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static WebDriver driver;
	public static Properties prop;

	public BasePage() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/java/com/todos/configs/config.properties");
		prop.load(fis);
	}
	
	public static void initialization(String browser) {
		if (browser.equalsIgnoreCase("chrome")) { 
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			
		}else if(browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			profile.setPreference("browser.download.folderList", 1);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.useDownloadDir", true);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
			profile.setPreference("browser.download.manager.closeWhenDone", true);
			profile.setPreference("browser.download.manager.showAlertOnComplete", false);
			profile.setPreference("browser.download.manager.useWindow", false);
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
	}

}
