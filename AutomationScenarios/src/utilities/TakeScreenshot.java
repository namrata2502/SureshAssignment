package utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class TakeScreenshot {
	public static void screenShot(WebDriver driver, String filePath) {		
		try {
			TakesScreenshot snapShot = ((TakesScreenshot)driver);
			File SrcFile = snapShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(filePath);
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		System.out.println("Successfully captured a screenshot");
	}
}
