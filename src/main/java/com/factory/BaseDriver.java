package com.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {

public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	
	public WebDriver intilizeDriver(String browser) {
		System.out.println("Browser :"+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tDriver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tDriver.set(new FirefoxDriver());
			
		}
		else {
			System.out.println("Provide the correct value: "+browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tDriver.get();
	}
}
