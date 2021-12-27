package com.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	private WebDriver driver;
    WebDriverWait wait; 
    private By paywithCard = By.xpath("//span[normalize-space()='Pay with Card']");
	private By email_id =By.id("email");
	private By cardnumber_id = By.id("card_number");
	private By cc_exp_id = By.id("cc-exp");
	private By cvv_id = By.id("cc-csc");
	private By payBtn_id = By.id("submitButton");
	
	private By paymentFrame = By.xpath("//iframe[@name='stripe_checkout_app']");

	public CartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
	}

	public void clickOnPayWithCardBtn() {
		driver.findElement(paywithCard).click();
	}
	
	public String getCartPageWindowHandle() {
		return driver.getWindowHandle();
	}
	
	public Set<String> getallWindowHandle() {
		 return driver.getWindowHandles();
	}
	
	public void enterEmailID(String email) {
	    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(email_id)));
		driver.findElement(email_id).sendKeys(email);
	}
	
	public void enterCCexpiryDetails(int month, int year) {
		driver.findElement(cc_exp_id).sendKeys(month+"/"+year);
	}
	
	public void enterCVVDetails(String cvv) {
		driver.findElement(cvv_id).sendKeys(cvv);
	}
	
	public void clickOnPayButton() {
		driver.findElement(payBtn_id).click();
	}
	
	public void enterCardNumber(String cardnumber) {
		driver.findElement(cardnumber_id).sendKeys(cardnumber);
	}
	
	public WebElement getFrameele() {
		System.out.println("switching to frame");
		return  driver.findElement(paymentFrame);
		
	}
}
