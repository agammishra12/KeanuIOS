package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class SignUpScreen {
	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	// Sign up Locators
	public static String addAccountSignUpLabel = "id:Add Account";
	public static String SignUpLabel = "xpath://XCUIElementTypeStaticText[@name='Sign Up']";
	// done,username,pass loactors signin screen
	public static String thinkOfUniqueLabel = "xpath:(//XCUIElementTypeOther[contains(@name,'Think of a unique nickname')])[2]";
	public static String confirmPassInput = "xpath://XCUIElementTypeSecureTextField[@value='Confirm']";
	public static String makeSurePassLabel = "xpath:(//XCUIElementTypeOther[contains(@name,'Make sure your password')])[2]";

	// Validation
	public static String usernameSignupValidation = "id:Replace";

	public SignUpScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public SignUpScreen signUp(String username, String password, String serverName) throws Exception {
		AppLibrary.verifyText(driver, SignUpLabel, "Sign Up");
		AppLibrary.enterMobileText(driver, SignInScreen.userNameInput, username);
		AppLibrary.sleep(1000);
		AppLibrary.verifyText(driver, thinkOfUniqueLabel,
				"Think of a unique nickname that you don't use anywhere else and doesn't contain personal information.");
		AppLibrary.enterMobileText(driver, SignInScreen.PassInput, password);
		AppLibrary.enterMobileText(driver, confirmPassInput, password);
		AppLibrary.sleep(1000);
		AppLibrary.verifyText(driver, makeSurePassLabel,
				"Make sure your password is a unique password you don't use anywhere else.");
		AppLibrary.clickMobileElement(driver, SignInScreen.toggleSwitch);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, SignInScreen.advancedOptionLabel);

		AppLibrary.verifyText(driver, SignInScreen.serverLabel, "SERVER");
		AppLibrary.enterMobileText(driver, SignInScreen.homeServerInput, serverName);

		// for disable keyboard
		AppLibrary.clickMobileElement(driver, SignInScreen.advancedOptionLabel);

		AppLibrary.clickMobileElement(driver, SignInScreen.toggleSwitchserver);
		AppLibrary.verifyText(driver, SignInScreen.identityServerLabel, "Identity Server");
		AppLibrary.enterMobileText(driver, SignInScreen.identityServerInput, serverName);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, SignInScreen.advancedOptionLabel);
		AppLibrary.clickMobileElement(driver, SignInScreen.doneLabel);
		return new SignUpScreen(appLibrary);
	}

	public SignUpScreen signUpUi() throws Exception {

		AppLibrary.verifyMobileElement(driver, addAccountSignUpLabel, true);
		AppLibrary.verifyMobileElement(driver, SignUpLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.doneLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.userNameInput, true);
		AppLibrary.sleep(1000);
		AppLibrary.verifyMobileElement(driver, thinkOfUniqueLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.PassInput, true);
		AppLibrary.verifyMobileElement(driver, confirmPassInput, true);
		AppLibrary.sleep(1000);
		AppLibrary.verifyMobileElement(driver, makeSurePassLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.toggleSwitch, true);

		AppLibrary.verifyMobileElement(driver, SignInScreen.advancedOptionLabel, true);

		AppLibrary.clickMobileElement(driver, SignInScreen.toggleSwitch);
		AppLibrary.verifyMobileElement(driver, SignInScreen.serverLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.homeServerLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.homeServerInput, true);

		AppLibrary.verifyMobileElement(driver, SignInScreen.customServerLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.toggleSwitchserver, true);

		AppLibrary.clickMobileElement(driver, SignInScreen.toggleSwitchserver);

		AppLibrary.verifyMobileElement(driver, SignInScreen.identityServerLabel, true);
		AppLibrary.verifyMobileElement(driver, SignInScreen.identityServerInput, true);
		return new SignUpScreen(appLibrary);
	}

	public SignUpScreen signUpValidation(String username, String password, String validation) throws Exception {

		AppLibrary.enterMobileText(driver, SignInScreen.userNameInput, username);
		if (password.equalsIgnoreCase("Tab")) {
			AppLibrary.clickMobileElement(driver, SignInScreen.PassInput);
		} else {
			AppLibrary.enterMobileText(driver, SignInScreen.PassInput, password);

		}
		if (!password.equalsIgnoreCase("Tab")) {
			AppLibrary.enterMobileText(driver, confirmPassInput, password);
		}
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, SignInScreen.advancedOptionLabel);

		if (!password.equalsIgnoreCase("Tab"))
			AppLibrary.clickMobileElement(driver, SignInScreen.doneLabel);

		AppLibrary.verifyText(driver, usernameSignupValidation.replace("Replace", validation), validation);

		return new SignUpScreen(appLibrary);
	}

}
