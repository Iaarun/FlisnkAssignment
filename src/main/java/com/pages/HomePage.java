package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
 private WebDriver driver;
 
    private By tempValue=   By.id("temperature");
    private By buyMoisturizersBtn=  By.xpath ("//button[normalize-space()='Buy moisturizers']");
    private By buySunscreensBtn=  By.xpath ("//button[normalize-space()='Buy sunscreens']"); 
    
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public int getTemperature() throws InterruptedException {
		Thread.sleep(2000);
		WebElement tempEle = driver.findElement(tempValue);
		String temperature = tempEle.getText().substring(0, tempEle.getText().indexOf(" "));
		int temp = Integer.parseInt(temperature);
		return temp;
	}

	public MoisturizersPage clickonMoisturizerButton() {
		driver.findElement(buyMoisturizersBtn).click();
		return new MoisturizersPage(driver);
	}

	public SunscreensPage clickOnSunscreenButton() {
		driver.findElement(buySunscreensBtn).click();
		return new SunscreensPage(driver);
	}
	
	
	public String getBuyPageTitle() {
		return driver.getTitle();
	}
}
