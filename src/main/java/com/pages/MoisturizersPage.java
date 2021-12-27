package com.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import com.utils.Utility;

public class MoisturizersPage {
	public Utility util = new Utility();
	private WebDriver driver;

	private By moisturizers = By.xpath("//p[@class='font-weight-bold top-space-10']");
	private By pricetagName = By.tagName("p");
	private By cartXpath =  By.xpath("//button[@class='thin-text nav-link']");

	public MoisturizersPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getMoisturizerPageTitle() {
		return driver.getTitle();
	}

	public void addMoisturizers() {

		List<WebElement> moisturizerslist = driver.findElements(moisturizers);
		List<String> moisturizersNamesList = new ArrayList<String>();
		List<WebElement> aloe = new ArrayList<WebElement>();
		List<WebElement> almond = new ArrayList<WebElement>();
		List<String> aloeprice = new ArrayList<String>();
		List<String> almondprice = new ArrayList<String>();

		for (WebElement name : moisturizerslist) {
			if (name.getText().toLowerCase().contains("aloe")) {
				aloe.add(name);
			} else if (name.getText().toLowerCase().contains("almond")) {
				almond.add(name);
			}
		}

		// storing data in a Map for aloe
		HashMap<String, Integer> aloemap = new HashMap<String, Integer>();
		System.out.println(aloe.size());
		for (WebElement ele1 : aloe) {
			System.out.println(ele1.getText());
			String pricetag = driver.findElement(RelativeLocator.with(pricetagName).below(ele1)).getText();
			if (pricetag.contains("Rs.")) {
				int priceValue = Integer.parseInt(pricetag.substring(11, pricetag.length()));
				aloemap.put(ele1.getText(), priceValue);
			} else {
				int priceValue = Integer.parseInt(pricetag.substring(7, pricetag.length()));
				aloemap.put(ele1.getText(), priceValue);
				aloemap = util.sortByValue(aloemap);
			}
		}

		Map.Entry<String, Integer> entry = aloemap.entrySet().iterator().next();
		driver.findElement(By.xpath("//*[contains(@onclick,'" + entry.getKey() + "')]")).click();

		// storing data in a Map for almond
		HashMap<String, Integer> almondmap = new HashMap<String, Integer>();
		System.out.println(almond.size());
		for (WebElement ele1 : almond) {
			String pricetag = driver.findElement(RelativeLocator.with(By.tagName("p")).below(ele1)).getText();
			if (pricetag.contains("Rs.")) {
				int priceValue = Integer.parseInt(pricetag.substring(11, pricetag.length()));
				almondmap.put(ele1.getText(), priceValue);
			} else {
				int priceValue = Integer.parseInt(pricetag.substring(7, pricetag.length()));
				almondmap.put(ele1.getText(), priceValue);
				almondmap = util.sortByValue(almondmap);
			}
		}

		Map.Entry<String, Integer> entry1 = almondmap.entrySet().iterator().next();

		driver.findElement(By.xpath("//*[contains(@onclick,'" + entry1.getKey() + "')]")).click();
		WebElement cart = driver.findElement(cartXpath);
		cart.click();

		// pay with card

		// cardnumber 4242 4242 4242 4242

		/*
		 * String testEmail = "abc@test.com";
		 * 
		 * String mainWindowId = driver.getWindowHandle();
		 * 
		 * WebElement paywithCardbtn =
		 * driver.findElement(By.xpath("//span[normalize-space()='Pay with Card']"));
		 * paywithCardbtn.click();
		 */

	}

}
