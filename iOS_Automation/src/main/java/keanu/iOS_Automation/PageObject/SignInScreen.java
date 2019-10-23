package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class SignInScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	// Sign in Locators
	public static String addAccountSigninLabel = "id:Add Account";
	public static String signInLabel = "xpath://XCUIElementTypeOther[@name='Sign In']";
	public static String doneLabel = "id:Done";
	public static String userNameInput = "xpath://XCUIElementTypeTextField[@value='User Name']";
	public static String PassInput = "xpath://XCUIElementTypeSecureTextField[@value='Password']";
	public static String infoLabel = "id:More Info";
	public static String toggleSwitch = "xpath://XCUIElementTypeSwitch[@name='Show Advanced Options']";
	public static String advancedOptionLabel = "xpath://XCUIElementTypeStaticText[@name='Show Advanced Options']";

	// server login
	public static String serverLabel = "xpath:(//XCUIElementTypeOther[@name='SERVER'])[2]";
	public static String homeServerLabel = "name:Home Server";
	public static String homeServerInput = "xpath://XCUIElementTypeCell[XCUIElementTypeStaticText[@name='Home Server']]//XCUIElementTypeTextField[@value='https://neo.keanu.im']";
	public static String customServerLabel = "xpath://XCUIElementTypeStaticText[@name='Use Custom Identity Server']";
	public static String toggleSwitchserver = "xpath://XCUIElementTypeSwitch[@name='Use Custom Identity Server']";
	public static String identityServerLabel = "id:Identity Server";
	public static String identityServerInput = "xpath://XCUIElementTypeCell[XCUIElementTypeStaticText[@name='Identity Server']]//XCUIElementTypeTextField[@value='https://neo.keanu.im']";


	// After Login
	public static String chatsLabel = "xpath://XCUIElementTypeOther[@name='Chats']";

	public static String signInValidation = "id:Invalid password";

	public SignInScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public SignInScreen signIn(String username, String password, String serverName) throws Exception {

		AppLibrary.enterMobileText(driver, userNameInput, username);
		AppLibrary.enterMobileText(driver, PassInput, password);
		AppLibrary.clickMobileElement(driver, toggleSwitch);
		AppLibrary.verifyText(driver, serverLabel, "SERVER");
		AppLibrary.enterMobileText(driver, homeServerInput, serverName);
		AppLibrary.clickMobileElement(driver, toggleSwitchserver);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, advancedOptionLabel);
		AppLibrary.verifyText(driver, identityServerLabel, "Identity Server");
		AppLibrary.enterMobileText(driver, identityServerInput, serverName);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, advancedOptionLabel);
		AppLibrary.clickMobileElement(driver, doneLabel);
		return new SignInScreen(appLibrary);
	}

	public SignInScreen signInUi() throws Exception {

		AppLibrary.verifyMobileElement(driver, addAccountSigninLabel, true);
		AppLibrary.verifyMobileElement(driver, signInLabel, true);
		AppLibrary.verifyMobileElement(driver, doneLabel, true);
		AppLibrary.verifyMobileElement(driver, userNameInput, true);
		AppLibrary.verifyMobileElement(driver, PassInput, true);
		AppLibrary.verifyMobileElement(driver, toggleSwitch, true);

		AppLibrary.clickMobileElement(driver, toggleSwitch);

		AppLibrary.verifyMobileElement(driver, serverLabel, true);
		AppLibrary.verifyMobileElement(driver, homeServerInput, true);

		AppLibrary.clickMobileElement(driver, toggleSwitchserver);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, advancedOptionLabel);

		AppLibrary.verifyText(driver, identityServerLabel, "Identity Server");
		AppLibrary.verifyMobileElement(driver, identityServerInput, true);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, advancedOptionLabel);
		return new SignInScreen(appLibrary);
	}

	public SignInScreen signInValidation(String username, String password, String validation) throws Exception {

		AppLibrary.enterMobileText(driver, userNameInput, username);
		AppLibrary.enterMobileText(driver, PassInput, password);
		// for disable keyboard
		AppLibrary.clickMobileElement(driver, advancedOptionLabel);
		AppLibrary.clickMobileElement(driver, doneLabel);

		AppLibrary.verifyText(driver, signInValidation, validation);

		return new SignInScreen(appLibrary);
	}

}
