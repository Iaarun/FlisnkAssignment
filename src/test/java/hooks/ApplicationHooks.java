package hooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.factory.BaseDriver;
import com.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	private BaseDriver baseDriver;
	private WebDriver driver;
	private ConfigReader configreader;
	Properties prop;

	@Before(order = 0)
	public void getReader() {
		configreader = new ConfigReader();
		prop = configreader.initialize_prop();

	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		baseDriver = new BaseDriver();
		driver = baseDriver.intilizeDriver(browserName);

	}

//	@After(order = 0)
	public void quitBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		String scenarioName = scenario.getName().replaceAll(" ", "_");
		byte[] impagePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(impagePath, "image/png", scenarioName);

	}

}
