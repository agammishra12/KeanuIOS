package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class MeScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	public static String userName = "class:XCUIElementTypeTextField";
	public static String userNameEdit = "id:ic_edit";

	// @testuser1:neo.keanu.im OR (//XCUIElementTypeStaticText)[2]
	public static String userNameDevServer = "xpath://XCUIElementTypeStaticText[@name='Replace']";
	public static String otherLinkedIdLabel = "xpath://XCUIElementTypeTable//XCUIElementTypeOther//XCUIElementTypeOther[@name='Other linked IDs']";
	public static String addLinkedIdButton = "id:Insert Link another ID";
	public static String linkAnotherIDLabel = "id:Link another ID";
	public static String changePasswordButton = "id:Change Password";
	public static String shareProfile = "id:Share Profile";
	public static String devicesAndKeysButton = "id:View Devices and Keys";
	public static String closeAccountButton = "id:Close Account";

	// link Another id

	public static String backButton = "id:Back";
	public static String idLabel = "xpath://XCUIElementTypeOther[@name='Link another ID']";
	public static String typeLabel = "xpath://XCUIElementTypeTable//XCUIElementTypeOther//XCUIElementTypeOther[@name='TYPE']";
	public static String emailButton = "id:E-Mail";
	public static String tickButton = "xpath://XCUIElementTypeButton[@name='More Info']";
	public static String mobilePhoneButton = "id:Mobile Phone";
	public static String textfield = "class:XCUIElementTypeTextField";
	public static String addButton = "id:Add";

	/// Change Password

	public static String changePassLabel = "xpath://XCUIElementTypeAlert//XCUIElementTypeStaticText[@name='Change Password']";
	public static String oldPassInput = "id:Old Password";
	public static String newPassInput = "id:Enter new password";
	public static String againNewPassInput = "id:Enter new password again";
	public static String okButton = "id:OK";
	public static String cancelButton = "id:Cancel";

	public static String successScreenLabel = "xpath://XCUIElementTypeStaticText[@name='Success']";
	public static String changedLabel = "id:Password successfully changed!";
	// Ok

	// View devices and keys blocked

	// Close Account

	public static String closeAccLabel = "xpath:(//XCUIElementTypeStaticText[@name='Close Account'])[2]";
	public static String closeYourAccLabel = "xpath://XCUIElementTypeStaticText[contains(@name,'Close your account on the server.')]";
	public static String passwordInput = "id:Password";
	public static String accForeever = "id:Close Account Forever";
	// cancel

	public MeScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	// public void deviceKeyVerify(Boolean signUp, Boolean signIn) {
	// AppLibrary.findElementForMobile(driver, deviceAndKeysButton).click();
	// AppLibrary.sleep(10000);
	// List<MobileElement> list = AppLibrary.findElementsForMobile(driver,
	// deviceList);
	// int deivceCount = list.size();
	// if (signUp)
	// assert deivceCount < 2;
	// if (signIn)
	// assert deivceCount > 1;
	// }

	public MeScreen changePass(String un, String serverName, String password, String newPass) throws Exception {
		AppLibrary.verifyText(driver, userName, un);
		AppLibrary.verifyText(driver, userNameDevServer.replace("Replace", serverName), serverName);
		AppLibrary.clickMobileElement(driver, changePasswordButton);
		AppLibrary.enterMobileText(driver, oldPassInput, password);
		AppLibrary.enterMobileText(driver, newPassInput, newPass);
		AppLibrary.enterMobileText(driver, againNewPassInput, newPass);
		AppLibrary.clickMobileElement(driver, okButton);

		// Verify pass changed
		AppLibrary.verifyMobileElement(driver, successScreenLabel);
		AppLibrary.verifyMobileElement(driver, changedLabel);
		AppLibrary.clickMobileElement(driver, okButton);
		return new MeScreen(appLibrary);
	}

	public MeScreen verifyMeElements() {
		AppLibrary.verifyMobileElement(driver, userName, true);
		AppLibrary.verifyMobileElement(driver, userNameEdit, true);
		AppLibrary.verifyMobileElement(driver, otherLinkedIdLabel, true);
		AppLibrary.verifyMobileElement(driver, addLinkedIdButton, true);
		AppLibrary.verifyMobileElement(driver, linkAnotherIDLabel, true);
		AppLibrary.verifyMobileElement(driver, changePasswordButton, true);
		AppLibrary.verifyMobileElement(driver, shareProfile, true);
		AppLibrary.verifyMobileElement(driver, devicesAndKeysButton, true);
		AppLibrary.verifyMobileElement(driver, closeAccountButton, true);

		return new MeScreen(appLibrary);

	}

	public MeScreen closeAccount(String pass) throws Exception {
		AppLibrary.clickMobileElement(driver, closeAccountButton);
		verifyCloseAccElements();
		AppLibrary.enterMobileText(driver, passwordInput, pass);
		AppLibrary.clickMobileElement(driver, accForeever);
		AppLibrary.sleep(2000);
		// Temp
		AppLibrary.verifyMobileElement(driver, AppFooters.meLabel);
		return new MeScreen(appLibrary);
	}

	public MeScreen verifyCloseAccElements() {

		AppLibrary.verifyMobileElement(driver, closeAccLabel, true);
		AppLibrary.verifyMobileElement(driver, closeYourAccLabel, true);
		AppLibrary.verifyMobileElement(driver, passwordInput, true);
		AppLibrary.verifyMobileElement(driver, accForeever, true);
		AppLibrary.verifyMobileElement(driver, cancelButton, true);
		return new MeScreen(appLibrary);
	}

	public MeScreen editUsername(String newUserName,String serverName) throws Exception {
		AppLibrary.clickMobileElement(driver, userNameEdit);
		AppLibrary.enterMobileText(driver, userName, newUserName);
		AppLibrary.clickMobileElement(driver, "id:Done");
		AppLibrary.sleep(2000);	
		//Verify Server name
//		AppLibrary.clickMobileElement(driver, userNameDevServer.replace("Replace", serverName));
		AppLibrary.verifyText(driver, userName, newUserName);
		
		//temp
		AppLibrary.clickMobileElement(driver, AppFooters.chatTab);
		AppLibrary.clickMobileElement(driver, AppFooters.meTab);
		AppLibrary.verifyText(driver, userName, newUserName);
		return new MeScreen(appLibrary);
	}
	
	public MeScreen linkAnotherIdUi() throws Exception {
		AppLibrary.clickMobileElement(driver, linkAnotherIDLabel);
		AppLibrary.verifyMobileElement(driver, backButton, true);
		AppLibrary.verifyMobileElement(driver, idLabel, true);
		AppLibrary.verifyMobileElement(driver, typeLabel, true);
		AppLibrary.verifyMobileElement(driver, emailButton, true);
		AppLibrary.verifyMobileElement(driver, tickButton, true);	
		AppLibrary.verifyMobileElement(driver, mobilePhoneButton, true);
		AppLibrary.verifyMobileElement(driver, textfield, true);
		AppLibrary.verifyMobileElement(driver, addButton, true);
		return new MeScreen(appLibrary);
	}
	
	
	
	

	// public void addProfilePictureViaCamera() {
	// AppLibrary.findElementForMobile(driver, profilePicture).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, camera).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, clickOnCamera).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, cameraClickDone).click();
	// AppLibrary.findElementForMobile(driver, cancelButton).click();
	//
	// AppLibrary.findElementForMobile(driver, profilePicture).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, camera).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, clickOnCamera).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, cameraClickDone).click();
	// AppLibrary.findElementForMobile(driver, OKButton).click();
	// }
	//
	// public void addProfilePictureViaFile() {
	// AppLibrary.findElementForMobile(driver, profilePicture).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, files).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, clickOnFile).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, cancelButton).click();
	//
	// AppLibrary.findElementForMobile(driver, profilePicture).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, files).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, clickOnFile).click();
	// AppLibrary.sleep(3000);
	// AppLibrary.findElementForMobile(driver, OKButton).click();
	// }

	public void navBack() throws Exception {
		driver.navigate().back();
	}
}
