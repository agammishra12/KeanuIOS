package keanu.iOS_Automation.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestBase {
	public AppiumDriver<MobileElement> driver, driver1;
	protected AppLibrary appLibrary1, appLibrary;
	Properties usersProperties = null;
	private String suite;
	protected String testName;
	ITestContext testContext;

	@BeforeClass(alwaysRun = true)
	public void aaasetUp(ITestContext context) throws Exception {

		suite = context.getCurrentXmlTest().getSuite().getName();
		suite = ((suite != null && !(suite.equals("Default suite"))) ? suite
				: InetAddress.getLocalHost().getHostName());
		suite = (suite.contains("Neo") ? "NishantLocal" : suite);
		testName = this.getClass().getSimpleName();
		testName = ((testName != null && !(testName.equals("Default test"))) ? testName
				: this.getClass().getSimpleName());

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMddhhmm");
		String currentDate = sdf.format(date);

		if (System.getProperty("Build") == null) {
			System.setProperty("Build", suite + "_" + currentDate);
			System.setProperty("Suite", suite);
		}
		System.setProperty("Test", testName);
		System.out.println("TestName: " + testName);
		testContext = context;
	}

	public void getScreenshot(String name) throws IOException {
		driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
		String path = "screenshots/" + name;
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		System.out.println("screenshot for at :" + path);
		Reporter.log("screenshot " + name + " available at :" + path, true);
	}

	public Properties loadUserProperties() {
		if (usersProperties == null) {
			usersProperties = new Properties();
			try {
				File f = new File("Users.properties");
				if (!f.exists()) {
					f = new File("TestData/users.properties");
				}

				usersProperties.load(new FileInputStream(f));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return usersProperties;
	}

	@AfterMethod
	public void handelErrorPopup(){
		
	}
	
	@AfterMethod
	public void checkAlerts(ITestResult result) throws Exception {
		String screenshotName = result.getName() + "_" + appLibrary.browser + "_" + AppLibrary.randIntDigits(1, 9999999)
				+ ".png";

		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				getScreenshot(screenshotName);
				Reporter.log("Failed at URL: " + appLibrary.getCurrentDriverInstance().getCurrentUrl(), true);
				int paramsLength = result.getParameters().length;
				Reporter.log("Screenshot Name : " + screenshotName, true);
				Reporter.log("ScreenShot for " + testName + " "
						+ ((paramsLength > 0) ? " with parameter " + result.getParameters()[1] : "") + " saved as "
						+ screenshotName + ".png", true);

			} catch (Exception e) {
				Reporter.log("Failed fetching URL and screenshot due to error:" + e.getMessage(), true);
				e.printStackTrace();
			}

			if (appLibrary.getCurrentSessionID() != null) {
				Reporter.log("Session id for " + testName + " is " + appLibrary.getCurrentSessionID(), true);
				Reporter.log("Session details for " + testName
						+ " can be found at https://www.browserstack.com/automate/sessions/"
						+ appLibrary.getCurrentSessionID() + ".json", true);
			}
		}
	}

	// @BeforeMethod
	// public void nameBefore(Method method) {
	// appLibrary = new AppLibrary(testName + "." + method.getName());
	// }

	@AfterClass(alwaysRun = true)
	public void quitBrowser() {
		driver.quit();
		try {
			driver1.quit();
		} catch (Exception e) {
			// No instance for driver 2
		}
		Reporter.log("Closing the Browser Successfully", true);
		System.out.println("Closing the Browser Successfully");
		Reporter.log("</table>");
	}
}
