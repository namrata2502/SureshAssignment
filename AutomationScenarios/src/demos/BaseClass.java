package demos;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class BaseClass {
	static WebDriver driver;
	static SoftAssert sfAssert = new SoftAssert();
	Logger log = Logger.getLogger("log");

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\SDETTraining\\Software\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterTest
	public void teadDown() {
		log.info("Closing all the browsers opened");
		driver.quit();
	}
}
