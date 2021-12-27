package stepdefinitions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.factory.BaseDriver;
import com.pages.CartPage;
import com.pages.HomePage;
import com.pages.MoisturizersPage;
import com.pages.SunscreensPage;
import com.utils.Constants;
import com.utils.Utility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class EcommerceSteps {

	private HomePage homepage = new HomePage(BaseDriver.getDriver());
	private MoisturizersPage moisturizerPage = new MoisturizersPage(BaseDriver.getDriver());
	private SunscreensPage sunscreenPage = new SunscreensPage(BaseDriver.getDriver());
	private CartPage cartPage = new CartPage(BaseDriver.getDriver());
	private Utility util = new Utility();
	private int tempValue;
	private String title;

	@Given("Application is launched")
	public void application_is_launched() {
		BaseDriver.getDriver().get("https://weathershopper.pythonanywhere.com/");
	}
	@When("User get temperature value")
	public void user_get_temperature_value() throws InterruptedException {
		tempValue = homepage.getTemperature();
		System.out.println(tempValue);
	}
	@When("if temp is less than {int} click on buymoisterizer or temp greater than {int} click on buysunscreen")
	public void if_temp_is_less_than_buy_moisterizer_or_temp_greater_than_buy_sunscreen(Integer int1, Integer int2) {
		if (tempValue < int1) {
			homepage.clickonMoisturizerButton();
			title = moisturizerPage.getMoisturizerPageTitle();
			Assert.assertTrue(title.equals(Constants.MOISTURIZER_TITLE));
			moisturizerPage.addMoisturizers();
		} else if (tempValue > int2) {
			homepage.clickOnSunscreenButton();
			title = sunscreenPage.getSunscreenPagetitle();
			Assert.assertTrue(title.equals(Constants.SUNSCREEN_TITLE));
			sunscreenPage.addSunscreen();
		}
	}

	@Then("Click on PaywithCard btn")
	public void click_on_paywith_card_btn() {
	   
	   String mainPageid = cartPage.getCartPageWindowHandle();
	   cartPage.clickOnPayWithCardBtn();
	   System.out.println("click on pay button ");
	   BaseDriver.getDriver().switchTo().frame(BaseDriver.getDriver().findElement(By.xpath("//iframe[@name='stripe_checkout_app']")));
	   System.out.println("switched to frame");
	   cartPage.enterEmailID(Constants.EMAIL_ID);
	   cartPage.enterCardNumber(Constants.CARD_NUMBER);
	   cartPage.enterCCexpiryDetails(util.getCurrentMonth(), util.getCurrentYear());
	   cartPage.enterCVVDetails(Constants.CVV);
	}
}
