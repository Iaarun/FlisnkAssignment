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

public class SunscreensPage {
	public Utility util = new Utility();

	private WebDriver driver;

	private By sunscreens = By.xpath("//p[@class='font-weight-bold top-space-10']");
	private By pricetagName = By.tagName("p");
	private By cartXpath = By.xpath("//button[@class='thin-text nav-link']");

	public SunscreensPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getSunscreenPagetitle() {
		return driver.getTitle();
	}

	public void addSunscreen() {
		List<WebElement> sunscreenlist = driver.findElements(sunscreens);

		List<WebElement> spf30 = new ArrayList<WebElement>();
		List<WebElement> spf50 = new ArrayList<WebElement>();
		List<String> spf30price = new ArrayList<String>();
		List<String> spf50price = new ArrayList<String>();

		
		for (WebElement name : sunscreenlist) {
			if (name.getText().toUpperCase().contains("SPF-30")) {
				spf30.add(name);
			} else if (name.getText().toUpperCase().contains("SPF-50")) {
				spf50.add(name);
			}

		}

		// storing data in a Map for spf 30
		HashMap<String, Integer> mapspf30 = new HashMap<String, Integer>();
		System.out.println(spf30.size());
		for (WebElement ele1 : spf30) {
			System.out.println(ele1.getText());
			String pricetag = driver.findElement(RelativeLocator.with(pricetagName).below(ele1)).getText();
			if (pricetag.contains("Rs.")) {
				int priceValue = Integer.parseInt(pricetag.substring(11, pricetag.length()));
				mapspf30.put(ele1.getText(), priceValue);
			} else {
				int priceValue = Integer.parseInt(pricetag.substring(7, pricetag.length()));
				mapspf30.put(ele1.getText(), priceValue);

			}

			mapspf30 = util.sortByValue(mapspf30);
		}

		Map.Entry<String, Integer> entry = mapspf30.entrySet().iterator().next();
		driver.findElement(By.xpath("//*[contains(@onclick,'" + entry.getKey() + "')]")).click();


		HashMap<String, Integer> mapspf50 = new HashMap<String, Integer>();
		System.out.println(spf50.size());
		for (WebElement ele1 : spf50) {
			System.out.println(ele1.getText());
			String pricetag = driver.findElement(RelativeLocator.with(pricetagName).below(ele1)).getText();
			if (pricetag.contains("Rs.")) {
				int priceValue = Integer.parseInt(pricetag.substring(11, pricetag.length()));
				mapspf50.put(ele1.getText(), priceValue);
			} else {
				int priceValue = Integer.parseInt(pricetag.substring(7, pricetag.length()));
				mapspf50.put(ele1.getText(), priceValue);

			}
			mapspf50 = util.sortByValue(mapspf50);
		}
		// Map<String , Integer> hm50 = sortByValue(mapspf50);
		Map.Entry<String, Integer> entry1 = mapspf50.entrySet().iterator().next();
		driver.findElement(By.xpath("//*[contains(@onclick,'" + entry1.getKey() + "')]")).click();

		// click on Cart button
		WebElement cart = driver.findElement(cartXpath);
		cart.click();

		// System.out.println(mapspf30 );

	}

}
