package com.utils;

import java.time.Duration;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	WebDriver driver;

	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	public WebDriver switchtoNewWindow(WebDriver driver, Set<String> allIds) {
		this.driver = driver;
		System.out.println("window ids set " + allIds);
		Iterator<String> it = allIds.iterator();
		String parentId = it.next();
		String childid = it.next();
		this.driver.switchTo().window(childid);

		return this.driver;

	}

	public static int getCurrentYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return Integer.parseInt(String.valueOf(year).substring(2));
	}

	public int getCurrentMonth() {
		int month = Calendar.getInstance().get(Calendar.MONTH);
		return month;
	}

	public void switchToIframe(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
		// driver.switchTo().frame(ele);
	}

	public void switchToWindow(String mainId, Set<String> allId) {

		for (String id : allId) {
			if (!id.equals(mainId)) {
				driver.switchTo().window(id);
			}
		}
	}
}
