package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class WelcomeScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	public static String welcomeToKeanuLabel = "name:Welcome to Keanu";
	public static String skipButton = "name:>>";

	public WelcomeScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public WelcomeScreen skipToSignIn() {
		AppLibrary.verifyText(driver, welcomeToKeanuLabel, "Welcome to Keanu");
		AppLibrary.findElementForMobile(driver, skipButton).click();
		return new WelcomeScreen(appLibrary);
	}
}
