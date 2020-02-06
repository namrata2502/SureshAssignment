package demos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Assignment extends BaseClass {

	@Test(priority = 1)
	public void firstAvatar() throws InterruptedException {
		log.info("************Assessment 1**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/hovers");

		driver.manage().window().maximize();
		WebElement firstAvatar = driver.findElement(By.xpath("//img[contains(@src, 'avatar')]"));

		Actions builder = new Actions(driver);
		log.info("Hover over the first avatar");
		builder.moveToElement(firstAvatar).build().perform();
		Thread.sleep(1000);

		String expectedAvatarName = "name: user1";
		String actualAvatarName = driver.findElement(By.xpath("//h5[contains(text(),'user1')]")).getText();

		Assert.assertTrue(actualAvatarName.contains(expectedAvatarName),
				"Test Failed: Avatar name is not same as expected");
		log.info("Test passed: Avatar name is same as expected\n");
	}

	@Test(priority = 2)
	public void jsConfirmAlerts() throws InterruptedException {
		log.info("************Assessment  2**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		driver.manage().window().maximize();

		log.info("Clicking on the second button on the page");
		WebElement jsConfirmButton = driver.findElement(By.xpath("//button[contains(text(),'Confirm')]"));
		jsConfirmButton.click();

		log.info("Javascript alert appears");
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		log.info("Alert box with message: " + alertMessage + " is displayed.");

		log.info("Click OK from the alert box");
		alert.accept();
		String actualResult = driver.findElement(By.id("result")).getText();
		String expectedResult = "You clicked: Ok";
		Assert.assertEquals(actualResult, expectedResult, "Test failed: User has not clicked on OK button");
		log.info("Test passed: OK button is verified");

		jsConfirmButton.click();
		alert.dismiss();
		String expected = "You clicked: Cancel";
		String actual = driver.findElement(By.id("result")).getText();
		Assert.assertEquals(actual, expected, "Test failed: User has not clicked on CANCEL button");
		log.info("Test passed: CANCEL button is verified\n");
	}

	@Test(priority = 3)
	public void keyEvent() throws InterruptedException {
		log.info("************Assessment  3**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/key_presses");
		log.info("Opening a website");
		driver.manage().window().maximize();

		log.info("Press space key");
		WebElement inputText = driver.findElement(By.id("target"));
		Actions builder = new Actions(driver);
		builder.moveToElement(inputText).click().sendKeys(Keys.SPACE).perform();

		log.info("Validate the space key entered from the result text appearing on the screen");
		String expectedResult = "You entered: SPACE";
		String actualresult = driver.findElement(By.xpath("//p[@id='result']")).getText();
		log.info(actualresult + " key");
		Assert.assertTrue(actualresult.contains(expectedResult),
				"Test Failed: Expected Result for space key is not matching with the actual result");
		log.info("Test Passed: Validation for Space key is successfully tested");

		log.info("Press left key");
		Thread.sleep(1000);
		builder.moveToElement(inputText).click().sendKeys(Keys.LEFT).perform();
		String expected = "You entered: LEFT";
		String actual = driver.findElement(By.xpath("//p[@id='result']")).getText();

		log.info("Validate the left key entered from the result text appearing on the screen");
		log.info(actual + " key");
		Assert.assertTrue(actual.contains(expected),
				"Expected Result for left arrow key is not matching with the actual result");

		log.info("Test Passed: Validation for left arrow key is successfully tested\n");
	}

	@Test(priority = 4)
	public void windowSwitch() {
		log.info("************Assessment  4**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();

		log.info("Click to open a new window");
		driver.findElement(By.xpath("//a[contains(text(),'Click')]")).click();
		String mainWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String childWindow = i1.next();

			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				log.info("Switch to the new window");
				driver.switchTo().window(childWindow);

				log.info("Checking the page title of new window");
				String expectedTitle = "New Window";
				String actualTitle = driver.switchTo().window(childWindow).getTitle();
				Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle), "Test Failed: Title mismatch");
				log.info("Test Passed: Title of the child window is :" + actualTitle);

				log.info("Closing the new window");
				driver.close();
			}
		}

		// Switching to Parent Window i.e. Main window
		log.info("Switching to the main window\n");
		driver.switchTo().window(mainWindow);
	}

	@Test(priority = 5)
	public void contextClick() {
		log.info("************Assessment  5**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/context_menu");

		log.info("Right click the area that renders context menu");
		WebElement contextMenu = driver.findElement(By.id("hot-spot"));
		Actions actions = new Actions(driver);
		actions.contextClick(contextMenu).perform();
		Alert alert = driver.switchTo().alert();

		log.info("Grab the text from javascript alert box");
		String expectedValue = "You selected a context menu";
		String actualValue = alert.getText();
		Assert.assertTrue(expectedValue.contains(actualValue),
				"Test Failed: Context menu value is not matching the expected value");
		log.info("Test Passed: Context menu value is not matching the expected value");
		log.info("Click on Ok button of alert box\n");
		alert.accept();
	}

	@Test(priority = 6)
	public void sorting() {
		log.info("************Assessment  6**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/tables");
		driver.manage().window().maximize();

		List<WebElement> expectedList = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[1]"));
		ArrayList<String> values = new ArrayList<>();

		for (int j = 0; j < expectedList.size(); j++) {
			values.add(expectedList.get(j).getText());
		}

		// Sorting the list of WebElements before Click action
		Collections.sort(values);
		log.info("Sorted Strings " + values);

		log.info("Clicking on the first column heading");
		driver.findElement(By.xpath("//span[contains(text(), 'Last Name')]")).click();
		log.info("Grabing the values from the column");
		List<WebElement> actualList = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[1]"));

		// To assert the values
		for (int i = 0; i < actualList.size(); i++) {
			Assert.assertEquals(values.get(i), actualList.get(i).getText());
		}

		log.info("Test Passed: Column values are sorted in ascending order\n");
	}

	@Test(priority = 7)
	public static void snapshot() throws IOException {
		sfAssert.assertEquals(true, false);
		screenshot();
		sfAssert.assertAll();
	}

	public static void screenshot() throws IOException {
		driver.get("http://the-internet.herokuapp.com/");
		driver.manage().window().maximize();
		utilities.TakeScreenshot.screenShot(driver, "C:\\Temp\\failshot_Screenshot.ScreenShotOnFailure.png");
	}

	@Test(priority = 8)
	public void nestedFrames() throws InterruptedException {
		log.info("************Assessment  8**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/nested_frames");
		driver.manage().window().maximize();

		WebElement topFrame, bottomFrame;
		topFrame = driver.findElement(By.xpath("//frame[@src='/frame_top']"));
		bottomFrame = driver.findElement(By.xpath("//frame[@src='/frame_bottom']"));

		log.info("Switch to top frameset");
		driver.switchTo().frame(topFrame);
		int size = driver.findElements(By.tagName("frame")).size();
		log.info("Number of frames present inside the top frame " + size);

		for (int i = 0; i < size; i++) {
			driver.switchTo().frame(i);
			Thread.sleep(3000);

			switch (i) {
			case 0:
				String reqValue1 = "LEFT";
				String actualValue1 = driver.findElement(By.xpath("//body[contains(text(),'LEFT')]")).getText();
				log.info("Actual value received from top left frame: " + actualValue1);
				Assert.assertTrue(actualValue1.equals(reqValue1),
						"Test Failed: The expected and actual values are not matching for topLeft");
				driver.switchTo().parentFrame();
				break;

			case 1:
				String reqValue2 = "MIDDLE";
				String actualValue2 = driver.findElement(By.id("content")).getText();
				log.info("Actual value received from top middle frame: " + actualValue2);
				Assert.assertTrue(actualValue2.equals(reqValue2),
						"Test Failed: The expected and actual values are not matching for topMiddle");
				driver.switchTo().parentFrame();
				break;

			case 2:
				String reqValue3 = "RIGHT";
				String actualValue3 = driver.findElement(By.xpath("//body[contains(text(),'RIGHT')]")).getText();
				log.info("Actual value received from top right frame: " + actualValue3);
				Assert.assertTrue(actualValue3.equals(reqValue3),
						"Test Failed: The expected and actual values are not matching for topRight");
				driver.switchTo().parentFrame();
				break;
			}
			log.info("Top frame elements i.e. left, middle and right is verfied");
		}

		driver.switchTo().defaultContent();
		driver.switchTo().frame(bottomFrame);
		String expectedValue = "BOTTOM";
		String actualValue = driver.findElement(By.xpath("//body[contains(text(),'BOTTOM')]")).getText();
		log.info("Actual value received from bottom frame: " + actualValue);
		Assert.assertTrue(actualValue.equals(expectedValue),
				"Test Failed: The expected and actual values are not matching for topRight");
		log.info("Bottom frame is verified");
		log.info("Test Passed: All the elements of the frame has been verified\n");
	}

	@Test(priority = 9)
	public void editorFrame() {
		log.info("************Assessment  9**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/tinymce");
		driver.manage().window().maximize();

		log.info("Switch to the frame that contains the TinyMCE editor");
		driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));

		log.info("Find and store the text found in the editor in a string value");
		WebElement content = driver.findElement(By.id("tinymce"));
		String defaultValue = content.getText();

		log.info("Clear the text in the editor");
		content.clear();

		log.info("Input the new text in the editor");
		String newValue = "The new content in the editor";
		content.sendKeys(newValue);

		Assert.assertFalse(defaultValue.contentEquals(newValue),
				"Test Failed: The new and old value in editor is matching");
		log.info("The new and old value in the editor is not matching");

		log.info("Switching to the top level of the page");
		driver.switchTo().defaultContent();

		log.info("Grab the text from the top level of the page");
		String expectedText = "An iFrame containing the TinyMCE WYSIWYG Editor";
		String actualText = driver.findElement(By.xpath("//h3[contains(text(),'TinyMCE')]")).getText();
		Assert.assertEquals(actualText, expectedText,
				"Test Failed: The actual and expected value on top of the page is not matching");
		log.info("Test Passed: The actual and expected value on the top of page is matching\n");
	}

	@Test(priority = 10)
	public void dropdown() {
		log.info("************Assessment  10**************");
		log.info("Launching the website");
		driver.get("http://the-internet.herokuapp.com/dropdown");
		driver.manage().window().maximize();

		log.info("Grab the contents displayed in the dropdown list");
		List<WebElement> drpList = driver.findElements(By.tagName("option"));

		Assert.assertFalse(drpList.get(0).isEnabled(), "Test Failed: The Target element is enabled");
		log.info("Test Passed: The target element of the dropdown menu is not enabled\n");
	}

}
