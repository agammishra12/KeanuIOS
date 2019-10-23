package keanu.iOS_Automation.Library;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AppLibrary {

	public static final long GLOBALTIMEOUT = 10;
	private AppiumDriver<MobileElement> appiumDriver; // android Driver instance

	private Configuration config;
	public String appDirr;
	public String appName;
	public String deviceName1;
	public String deviceName2;
	public String bundleId;
	public String appActivity;
	public String driverPath;
	public String baseUrl;
	public String mailUrl;
	public String siteName;
	public String browser;
	public String device;
	public boolean isExecutionOnMobile;
	private AppLibrary appLibrary;
	public String currentTestName;
	public String browserStackUserName;
	public String browserStackAuthKey;
	public String browserStackDevice1;
	public String browserStackDevice2;
	public String browserStackOSVersion1;
	public String browserStackOSVersion2;
	public String app_Url;
	Boolean isBrowserStackExecution;

	public String getCurrentTestName() {
		return currentTestName;
	}

	public void setCurrentTestName(String currentTestName) {
		this.currentTestName = currentTestName;
	}

	private String currentSessionID;

	// This is used for parameterized tests
	public AppLibrary(String testName) {
		this.currentTestName = testName;
	}

	public AppLibrary() {
		// do nothign
	}

	/**
	 * Creates an Webdriver Instance @throws Exception
	 */
	public AppiumDriver<MobileElement> getDriverInstance(String mobiledevice, String port) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();

		String browser = (System.getProperty("browserName") != null
				&& !(System.getProperty("browserName").equals("${browserName}"))) ? (System.getProperty("browserName"))
						: (getConfiguration().getBrowserName());
		this.browser = browser;

		appDirr = (System.getProperty("appDir") != null && !(System.getProperty("appDir").equals("${appDir}")))
				? System.getProperty("appDir") : getConfiguration().getappDir();

		appName = (System.getProperty("appName") != null && !(System.getProperty("appName").equals("${appName}")))
				? System.getProperty("appName") : getConfiguration().getappName();

		deviceName1 = (System.getProperty("deviceName1") != null
				&& !(System.getProperty("deviceName1").equals("${deviceName1}"))) ? System.getProperty("deviceName1")
						: getConfiguration().getDeviceName1();

		deviceName2 = (System.getProperty("deviceName2") != null
				&& !(System.getProperty("deviceName2").equals("${deviceName2}"))) ? System.getProperty("deviceName2")
						: getConfiguration().getDeviceName2();

		bundleId = (System.getProperty("bundleId") != null && !(System.getProperty("bundleId").equals("${bundleId}")))
				? System.getProperty("bundleId") : getConfiguration().getAppBundleId();

		appActivity = (System.getProperty("appActivity") != null
				&& !(System.getProperty("appActivity").equals("${appActivity}"))) ? System.getProperty("appActivity")
						: getConfiguration().getAppActivity();

		isBrowserStackExecution = (System.getProperty("isBrowserstackExecution") != null
				&& !(System.getProperty("isBrowserstackExecution").equals("${isBrowserstackExecution}")))
						? (System.getProperty("isBrowserstackExecution").equals("true"))
						: getConfiguration().isBrowserStackExecution();
		System.out.println("Is Browser Stack execution: " + isBrowserStackExecution);

		if (isBrowserStackExecution) {

			try {

				browserStackUserName = (System.getProperty("browserStackUserName") != null
						&& !(System.getProperty("browserStackUserName").equals("${browserStackUserName}")))
								? System.getProperty("browserStackUserName")
								: getConfiguration().getBrowserStackUserName();

				browserStackAuthKey = (System.getProperty("browserStackAuthKey") != null
						&& !(System.getProperty("browserStackAuthKey").equals("${browserStackAuthKey}")))
								? System.getProperty("browserStackAuthKey")
								: getConfiguration().getBrowserStackAuthKey();

				browserStackOSVersion1 = (System.getProperty("browserStackOSVersion1") != null
						&& !(System.getProperty("browserStackOSVersion1").equals("${browserStackOSVersion1}")))
								? System.getProperty("browserStackOSVersion1")
								: getConfiguration().getBrowserStackOSVersion1();

				browserStackOSVersion2 = (System.getProperty("browserStackOSVersion2") != null
						&& !(System.getProperty("browserStackOSVersion2").equals("${browserStackOSVersion2}")))
								? System.getProperty("browserStackOSVersion2")
								: getConfiguration().getBrowserStackOSVersion2();

				browserStackDevice1 = (System.getProperty("browserStackDevice1") != null
						&& !(System.getProperty("browserStackDevice1").equals("${browserStackDevice1}")))
								? System.getProperty("browserStackDevice1")
								: getConfiguration().getBrowserStackDevice1();

				browserStackDevice2 = (System.getProperty("browserStackDevice2") != null
						&& !(System.getProperty("browserStackDevice2").equals("${browserStackDevice2}")))
								? System.getProperty("browserStackDevice2")
								: getConfiguration().getBrowserStackDevice2();

				app_Url = (System.getProperty("app_Url") != null
						&& !(System.getProperty("app_Url").equals("${app_Url}"))) ? System.getProperty("app_Url")
								: getConfiguration().getBrowserStackAppURL();

				if (browser.equalsIgnoreCase("android") || browser.equalsIgnoreCase("iPhone")) {

					if (mobiledevice.equals("device1")) {
						caps.setCapability("device", browserStackDevice1);
						caps.setCapability("os_version", browserStackOSVersion1);
					} else {
						caps.setCapability("device", browserStackDevice2);
						caps.setCapability("os_version", browserStackOSVersion2);
					}
					// caps.setCapability("automationName", "uiautomator2");
					caps.setCapability("browserstack.idleTimeout", "180");
					caps.setCapability("browserstack.debug", true);
					caps.setCapability("browserstack.networkLogs", true);
					caps.setCapability("app", app_Url);
					caps.setCapability("newCommandTimeout", 900000);

					appiumDriver = new AppiumDriver<MobileElement>(new URL("https://"
							+ (browserStackUserName.equals("") ? getConfiguration().getBrowserStackUserName()
									: browserStackUserName)
							+ ":" + (browserStackAuthKey.equals("") ? getConfiguration().getBrowserStackAuthKey()
									: browserStackAuthKey)
							+ "@hub-cloud.browserstack.com/wd/hub"), caps);

				}
			} catch (Exception e) {
				Reporter.log("Issue creating new driver instance due to following error: " + e.getMessage() + "\n"
						+ e.getStackTrace(), true);
				throw e;
			}
			currentSessionID = (appiumDriver).getSessionId().toString();
		}

		else if (browser.equalsIgnoreCase("ios")) {
			File appDir = new File(appDirr);
			File app = new File(appDir, appName);
			caps.setCapability("platformName", "iOS");
			caps.setCapability("platformVersion", "11.4");
			caps.setCapability("deviceName", "iPhone");
			caps.setCapability("automationName", "XCUITest");
			// caps.setCapability("xcodeOrgId", "94AWUYGT5Q");
			// caps.setCapability("xcodeSigningId", "iPhone Developer");
			caps.setCapability("session-override", "true");
			if (mobiledevice.equals("device1")) {
				caps.setCapability("deviceName", deviceName1);
				caps.setCapability("udid", deviceName1);
			} else {
				caps.setCapability("deviceName", deviceName2);
				caps.setCapability("udid", deviceName2);
			}
			caps.setCapability("app", app.getAbsolutePath());
			// caps.setCapability("unicodeKeyboard", true);
			// caps.setCapability("resetKeyboard", true);
			caps.setCapability("noResetValue", "false");
			caps.setCapability("autoGrantPermissions", true);
			caps.setCapability("newCommandTimeout", 90000);
			String url = "http://0.0.0.0:" + port + "/wd/hub";
			appiumDriver = new AppiumDriver<MobileElement>(new URL(url), caps);
		}
		appiumDriver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		return appiumDriver;
	}

	/**
	 * Creates an Webdriver Instance @throws Exception
	 */
	public AppiumDriver<MobileElement> getDriverInstance() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();

		String browser = (System.getProperty("browserName") != null
				&& !(System.getProperty("browserName").equals("${browserName}"))) ? (System.getProperty("browserName"))
						: (getConfiguration().getBrowserName());
		this.browser = browser;

		appDirr = (System.getProperty("appDir") != null && !(System.getProperty("appDir").equals("${appDir}")))
				? System.getProperty("appDir") : getConfiguration().getappDir();

		appName = (System.getProperty("appName") != null && !(System.getProperty("appName").equals("${appName}")))
				? System.getProperty("appName") : getConfiguration().getappName();

		deviceName1 = (System.getProperty("deviceName1") != null
				&& !(System.getProperty("deviceName1").equals("${deviceName1}"))) ? System.getProperty("deviceName1")
						: getConfiguration().getDeviceName1();

		bundleId = (System.getProperty("appPackage") != null
				&& !(System.getProperty("appPackage").equals("${appPackage}"))) ? System.getProperty("appPackage")
						: getConfiguration().getAppBundleId();

		appActivity = (System.getProperty("appActivity") != null
				&& !(System.getProperty("appActivity").equals("${appActivity}"))) ? System.getProperty("appActivity")
						: getConfiguration().getAppActivity();

		isBrowserStackExecution = (System.getProperty("isBrowserstackExecution") != null
				&& !(System.getProperty("isBrowserstackExecution").equals("${isBrowserstackExecution}")))
						? (System.getProperty("isBrowserstackExecution").equals("true"))
						: getConfiguration().isBrowserStackExecution();
		System.out.println("Is Browser Stack execution: " + isBrowserStackExecution);

		if (isBrowserStackExecution) {

			try {

				browserStackUserName = (System.getProperty("browserStackUserName") != null
						&& !(System.getProperty("browserStackUserName").equals("${browserStackUserName}")))
								? System.getProperty("browserStackUserName")
								: getConfiguration().getBrowserStackUserName();

				browserStackAuthKey = (System.getProperty("browserStackAuthKey") != null
						&& !(System.getProperty("browserStackAuthKey").equals("${browserStackAuthKey}")))
								? System.getProperty("browserStackAuthKey")
								: getConfiguration().getBrowserStackAuthKey();

				browserStackOSVersion1 = (System.getProperty("browserStackOSVersion1") != null
						&& !(System.getProperty("browserStackOSVersion1").equals("${browserStackOSVersion1}")))
								? System.getProperty("browserStackOSVersion1")
								: getConfiguration().getBrowserStackOSVersion1();

				browserStackDevice1 = (System.getProperty("browserStackDevice1") != null
						&& !(System.getProperty("browserStackDevice1").equals("${browserStackDevice1}")))
								? System.getProperty("browserStackDevice1")
								: getConfiguration().getBrowserStackDevice1();

				app_Url = (System.getProperty("app_Url") != null
						&& !(System.getProperty("app_Url").equals("${app_Url}"))) ? System.getProperty("app_Url")
								: getConfiguration().getBrowserStackAppURL();

				if (browser.equalsIgnoreCase("android") || browser.equalsIgnoreCase("iPhone")) {

					// caps.setCapability("automationName", "uiautomator2");
					caps.setCapability("browserstack.idleTimeout", "180");
					caps.setCapability("device", browserStackDevice1);
					caps.setCapability("os_version", browserStackOSVersion1);
					caps.setCapability("browserstack.debug", true);
					caps.setCapability("browserstack.networkLogs", true);
					caps.setCapability("browserstack.appium_version", "1.14.0");
					caps.setCapability("app", app_Url);
					caps.setCapability("newCommandTimeout", 900000);
					caps.setCapability("build", System.getProperty("Build"));
					caps.setCapability("project", System.getProperty("Suite"));
					caps.setCapability("name", currentTestName);

					appiumDriver = new AppiumDriver<MobileElement>(new URL("https://"
							+ (browserStackUserName.equals("") ? getConfiguration().getBrowserStackUserName()
									: browserStackUserName)
							+ ":" + (browserStackAuthKey.equals("") ? getConfiguration().getBrowserStackAuthKey()
									: browserStackAuthKey)
							+ "@hub-cloud.browserstack.com/wd/hub"), caps);
				}
			} catch (Exception e) {
				Reporter.log("Issue creating new driver instance due to following error: " + e.getMessage() + "\n"
						+ e.getStackTrace(), true);
				throw e;
			}
			currentSessionID = (appiumDriver).getSessionId().toString();
		}

		else if (browser.equalsIgnoreCase("ios")) {
			File appDir = new File(appDirr);
			File app = new File(appDir, appName);
			caps.setCapability("platformName", "iOS");
			caps.setCapability("platformVersion", "11.4");
			caps.setCapability("deviceName", "iPhone");
			caps.setCapability("automationName", "XCUITest");
			// caps.setCapability("xcodeOrgId", "94AWUYGT5Q");
			// caps.setCapability("xcodeSigningId", "iPhone Developer");
//			caps.setCapability("session-override", "true");
			caps.setCapability("udid", deviceName1);
			caps.setCapability("app", app.getAbsolutePath());
			// caps.setCapability("unicodeKeyboard", true);
			// caps.setCapability("resetKeyboard", true);
//			caps.setCapability("noResetValue", "false");
			caps.setCapability("autoGrantPermissions", true);
			caps.setCapability("newCommandTimeout", 90000);
			appiumDriver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		}
		appiumDriver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		return appiumDriver;
	}

	public void reopenApp() throws Exception {
		appiumDriver.activateApp(bundleId);
	}

	public void resetApp() throws Exception {

		appiumDriver.resetApp();
	}

	public void killApp() throws Exception {
		appiumDriver.terminateApp(bundleId);
	}

	public void openApp() throws Exception {
		AppLibrary.sleep(3000);
		if (isBrowserStackExecution) {
			appiumDriver.activateApp(bundleId);
			appiumDriver.rotate(ScreenOrientation.PORTRAIT);
		} else
			appiumDriver.activateApp(bundleId);
		AppLibrary.sleep(5000);
	}

	public static int randIntDigits(int min, int max) {
		Random rand = new Random();
		int randomNum = (rand.nextInt((max - min) + 1) + rand.nextInt((max - min) + 1)) / 2;
		return randomNum;
	}

	/**
	 * Get Driver Instance
	 */
	public AppiumDriver<MobileElement> getCurrentDriverInstance() {
		return appiumDriver;
	}

	/**
	 * Closes the Browser
	 */
	public void closeBrowser() {
		if (appiumDriver != null)
			appiumDriver.quit();
	}

	public static MobileElement findElementForMobile(AppiumDriver<MobileElement> driver, String locatorString) {
		String string = locatorString;
		String[] parts = string.split(":");
		String type = parts[0]; // 004
		String object = parts[1];
		if (parts.length > 2) {
			for (int i = 2; i < parts.length; i++) {
				object = object + ":" + parts[i];
			}
		}
		Reporter.log("Finding element with logic: " + locatorString, true);
		System.out.println("Finding element with logic: " + locatorString);
		MobileElement element = null;
		if (type.equals("id")) {
			element = driver.findElement(By.id(object));
		} else if (type.equals("xpath")) {
			element = driver.findElement(By.xpath(object));
		} else if (type.equals("name")) {
			element = driver.findElement(By.name(object));
		} else if (type.equals("class")) {
			element = driver.findElement(By.className(object));
		} else if (type.equals("link")) {
			element = driver.findElement(By.linkText(object));
		} else if (type.equals("partiallink")) {
			element = driver.findElement(By.partialLinkText(object));
		} else if (type.equals("css")) {
			element = driver.findElement(By.cssSelector(object));
		} else {
			throw new RuntimeException("Please provide correct element locating strategy");
		}
		return element;
	}

	public static WebElement findElementForMobile(AppiumDriver<MobileElement> driver, String locatorString,
			boolean isOptional) {
		try {
			System.out.println(locatorString);
			return findElementForMobile(driver, locatorString);
		} catch (NoSuchElementException nse) {
			if (isOptional) {
				return null;
			} else {
				throw new RuntimeException("Element " + locatorString + " not found");
			}
		}
	}

	public Configuration getConfiguration() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	public static boolean verifyMobileElement(AppiumDriver<MobileElement> driver, String locatorString) {
		return verifyMobileElement(driver, locatorString, false);
	}

	public static boolean verifyMobileElement(AppiumDriver<MobileElement> driver, String locatorString,
			boolean checkVisibility) {
		boolean isDisplayed = true;
		try {
			if (checkVisibility) {
				isDisplayed = (findElementForMobile(driver, locatorString).isDisplayed());
			} else {
				findElementForMobile(driver, locatorString);
			}
		} catch (NoSuchElementException nsee) {
			Assert.assertTrue(false, "Element not found using locator: " + locatorString);
		}
		return isDisplayed;
	}

	public static void sleep(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static List<MobileElement> findElementsForMobile(AppiumDriver<MobileElement> driver, String locatorString) {

		String string = locatorString;
		String[] parts = string.split(":");
		String type = parts[0]; // 004
		String object = parts[1];
		if (parts.length > 2) {
			for (int i = 2; i < parts.length; i++) {
				object = object + ":" + parts[i];
			}
		}

		Reporter.log("Finding element with logic: " + locatorString, true);
		List<MobileElement> element = null;
		if (type.equals("id")) {
			element = driver.findElements(By.id(object));
		} else if (type.equals("xpath")) {
			element = driver.findElements(By.xpath(object));
		} else if (type.equals("name")) {
			element = driver.findElements(By.name(object));
		} else if (type.equals("class")) {
			element = driver.findElements(By.className(object));
		} else if (type.equals("link")) {
			element = driver.findElements(By.linkText(object));
		} else if (type.equals("partiallink")) {
			element = driver.findElements(By.partialLinkText(object));
		} else if (type.equals("css")) {
			element = driver.findElements(By.cssSelector(object));
		} else {
			throw new RuntimeException("Please provide correct element locating strategy");
		}
		return element;
	}

	public String getCurrentSessionID() {
		return currentSessionID;
	}

	public static void waitForMobileElementClickable(AppiumDriver<MobileElement> driver, WebElement element) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForMobileElementVisible(AppiumDriver<MobileElement> driver, WebElement element) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
	}

	public void selectCheckBox(AppiumDriver<MobileElement> driver, String locator) throws Exception {
		if (!AppLibrary.findElementForMobile(driver, locator).isSelected()) {
			AppLibrary.clickMobileElement(driver, locator);
		}
	}

	public static void deselectCheckBox(AppiumDriver<MobileElement> driver, String locator) throws Exception {
		if (AppLibrary.findElementForMobile(driver, locator).isSelected()) {
			AppLibrary.clickMobileElement(driver, locator);
		}
	}

	public static void enterMobileText(AppiumDriver<MobileElement> driver, String locator, String text) {
		AppLibrary.findElementForMobile(driver, locator).click();
		AppLibrary.findElementForMobile(driver, locator).clear();
		AppLibrary.findElementForMobile(driver, locator).sendKeys(text);
	}

	public static void clickMobileElement(AppiumDriver<MobileElement> driver, String locator) throws Exception {
		AppLibrary.findElementForMobile(driver, locator).click();
	}

	public static boolean verifyCheckBox(AppiumDriver<MobileElement> driver, String locator) {
		return AppLibrary.findElementForMobile(driver, locator).isSelected();
	}

	public static void waitUntilMobileElementDisplayed(AppiumDriver<MobileElement> driver, String locatorString) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		String string = locatorString;
		String[] parts = string.split(":");
		String type = parts[0];
		String object = parts[1];

		Reporter.log("Finding element with logic: " + locatorString, true);
		if (type.equals("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(object)));
		} else if (type.equals("name")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(object)));
		} else if (type.equals("class")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(object)));
		} else if (type.equals("link")) {
			;
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(object)));
		} else if (type.equals("partiallink")) {
			;
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(object)));
		} else if (type.equals("css")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(object)));
		} else if (type.equals("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(object)));
		} else {
			throw new RuntimeException("Please provide correct element locating strategy");
		}
	}

	public static void verifyAbsent(AppiumDriver<MobileElement> driver, String locatorString) {

		String string = locatorString;
		String[] parts = string.split(":");
		String type = parts[0]; // 004
		String object = parts[1];

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = null;
		try {
			Reporter.log("Finding element with logic: " + locatorString, true);
			if (type.equals("id")) {
				element = driver.findElement(By.id(object));
			} else if (type.equals("name")) {
				element = driver.findElement(By.name(object));
			} else if (type.equals("class")) {
				element = driver.findElement(By.className(object));
			} else if (type.equals("link")) {
				element = driver.findElement(By.linkText(object));
			} else if (type.equals("partiallink")) {
				element = driver.findElement(By.partialLinkText(object));
			} else if (type.equals("css")) {
				element = driver.findElement(By.cssSelector(object));
			} else if (type.equals("xpath")) {
				element = driver.findElement(By.xpath(object));
			}
			Assert.assertTrue(false,
					"Expected element to be absent, but it was found on the page. Text = " + element.getText());

		} catch (Exception e) {
			// It's good if not found
		} finally {
			driver.manage().timeouts().implicitlyWait(AppLibrary.GLOBALTIMEOUT, TimeUnit.SECONDS);
		}
	}

	public static void verifyText(AppiumDriver<MobileElement> driver, String locator, String expectedValue) {
		String actualValue = AppLibrary.findElementForMobile(driver, locator).getText();
		Assert.assertEquals(actualValue, expectedValue,
				"values didnot match for " + locator + "Expected =" + expectedValue + "  Actual =" + actualValue);
	}

	public static void verifiyContains(AppiumDriver<MobileElement> driver, String locator, String expectedValue) {
		String actualValue = AppLibrary.findElementForMobile(driver, locator).getText();
		actualValue.contains(expectedValue);
	}

	public void swipeDownByText(String text) {
		appiumDriver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ text + "\").instance(0))"))
				.click();
	}

	public void horizontalSwipe(String startLocator, String endLocator) {
		MobileElement startElement = AppLibrary.findElementForMobile(appiumDriver, startLocator);
		MobileElement endElement = AppLibrary.findElementForMobile(appiumDriver, endLocator);

		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

		int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
		int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
		TouchAction touchAction = new TouchAction(appiumDriver);

		touchAction.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release()
				.perform();
		AppLibrary.sleep(1000);
	}

	public void horizontalRightSwipe(String startLocator) {
		WebElement element = AppLibrary.findElementForMobile(appiumDriver, startLocator);
		Dimension dim = element.getSize();
		int Ancor = element.getSize().getHeight() / 2;

		Double screenWidthStart = dim.getWidth() * 0.8;
		int scrollStart = screenWidthStart.intValue();

		Double screenWidthEnd = dim.getWidth() * 0.2;
		int scrollEnd = screenWidthEnd.intValue();

		AndroidTouchAction touch = new AndroidTouchAction((PerformsTouchActions) appiumDriver);
		touch.press(PointOption.point(scrollStart, Ancor)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
				.moveTo(PointOption.point(scrollEnd, Ancor)).release().perform();
		AppLibrary.sleep(1000);
	}

	public void horizontalLeftSwipe(String startLocator) {
		WebElement element = AppLibrary.findElementForMobile(appiumDriver, startLocator);
		Dimension dim = element.getSize();
		int Ancor = element.getSize().getHeight() / 2;

		Double screenWidthStart = dim.getWidth() * 0.8;
		int scrollStart = screenWidthStart.intValue();

		Double screenWidthEnd = dim.getWidth() * 0.2;
		int scrollEnd = screenWidthEnd.intValue();

		AndroidTouchAction touch = new AndroidTouchAction((PerformsTouchActions) appiumDriver);
		touch.press(PointOption.point(scrollEnd, Ancor)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
				.moveTo(PointOption.point(scrollStart, Ancor)).release().perform();
		AppLibrary.sleep(1000);
	}

	public static String[][] readExcel(String excelFilePath, int sheetNo) throws BiffException, IOException {
		File file = new File(excelFilePath);
		String inputData[][] = null;
		Workbook w;

		try {
			w = Workbook.getWorkbook(file);

			// Get the first sheet
			Sheet sheet = w.getSheet(sheetNo);

			int colcount = sheet.getColumns();

			int rowcount = sheet.getRows();
			int countYes = 0;

			for (int i = 0; i < rowcount; i++) {
				if (sheet.getCell(colcount - 1, i).getContents().equalsIgnoreCase("Yes")) {
					countYes = countYes + 1;

				}
			}
			inputData = new String[countYes][colcount];
			int k = 0;
			for (int i = 0; i < rowcount; i++) {
				if (sheet.getCell(colcount - 1, i).getContents().equalsIgnoreCase("Yes")) {

					for (int j = 0; j < colcount; j++) {
						Cell cell = sheet.getCell(j, i);
						inputData[k][j] = cell.getContents().trim();

					}
					k = k + 1;
				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return inputData;
	}

	public static boolean isElementPresent(AppiumDriver<MobileElement> driver, String locator) {
		try {

			AppLibrary.findElementForMobile(driver, locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public String generateRandomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

}