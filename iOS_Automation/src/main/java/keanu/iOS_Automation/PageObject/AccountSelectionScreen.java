package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class AccountSelectionScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	// Account Selection Locators (New or existing account)
	public static String createAnAccountButton = "id:Create an Account";
	public static String signInToExistingAccButton = "id:Sign into an Existing Account";

	//Contractor
	public AccountSelectionScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public AccountSelectionScreen accountSelection(Boolean existingAccount) {
		if (existingAccount) {
			AppLibrary.sleep(2000);
			AppLibrary.findElementForMobile(driver, signInToExistingAccButton).click();
		} else{
			AppLibrary.sleep(2000);
			AppLibrary.findElementForMobile(driver, createAnAccountButton).click();
		}
		return new AccountSelectionScreen(appLibrary);
	}
}
