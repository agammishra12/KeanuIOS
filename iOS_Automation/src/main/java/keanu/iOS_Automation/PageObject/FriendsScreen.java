package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class FriendsScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	// headers
	public static String friendsLabel = "xpath://XCUIElementTypeOther[@name='Friends']";
	public static String friendsAddbutton = "name:Add";

	// Add friends screen
	public static String backButton = "id:Back";
	public static String addFriendLabel = "xpath://XCUIElementTypeOther[@name='Add Friend']";
	public static String matrixLabel = "id:Find your friend on Matrix";
	public static String matrixIdInput = "class:XCUIElementTypeTextField";
	public static String friendsMatrixLabel = "id:Enter your friend's Matrix ID. Yours is @testuser2:neo.keanu.im";
	public static String addFriendButton = "xpath://XCUIElementTypeButton[@name='Add Friend']";
	public static String inviteLinkLabel = "id:Send your invite link";
	public static String threeDotsButton = "id:ic more";
	public static String standingLabel = "id:Standing next to them?";
	public static String airdropButton = "id:ic airdrop";
	
	
	//edit and delete
	public static String editnameButton = "id:Edit Name";
	//Delete from chat screen
	
	//edit name 
	
	public static String editNamelabel = "xpath://XCUIElementTypeStaticText[@name='Edit Name']";
	public static String nameInput = "class:XCUIElementTypeTextField";
	public static String crossButton = "id:Clear text";
	public static String cancel = "id:Cancel";
	public static String ok = "id:OK";
	
	
	
	

	
	
	

	// chats screen
	public static String cancelButton = "id:Cancel";

	public FriendsScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public FriendsScreen friendsScreenUi() throws Exception {

		AppLibrary.clickMobileElement(driver, AppFooters.friendsTab);
		AppLibrary.verifyMobileElement(driver, friendsLabel, true);
		AppLibrary.verifyMobileElement(driver, friendsAddbutton, true);
		AppLibrary.clickMobileElement(driver, friendsAddbutton);
		AppLibrary.verifyMobileElement(driver, backButton, true);
		AppLibrary.verifyMobileElement(driver, addFriendLabel, true);
		AppLibrary.verifyMobileElement(driver, matrixLabel, true);
		AppLibrary.verifyMobileElement(driver, matrixIdInput, true);
		AppLibrary.verifyMobileElement(driver, friendsMatrixLabel, true);
		AppLibrary.verifyMobileElement(driver, addFriendButton, true);
		AppLibrary.verifyMobileElement(driver, inviteLinkLabel, true);
		AppLibrary.verifyMobileElement(driver, threeDotsButton, true);
		AppLibrary.verifyMobileElement(driver, standingLabel, true);
		AppLibrary.verifyMobileElement(driver, airdropButton, true);
		return new FriendsScreen(appLibrary);
	}

	public FriendsScreen InviteFriend(String user) throws Exception {

		AppLibrary.clickMobileElement(driver, AppFooters.friendsTab);
		AppLibrary.clickMobileElement(driver, friendsAddbutton);
		AppLibrary.enterMobileText(driver, matrixIdInput, user);
		AppLibrary.clickMobileElement(driver, addFriendButton);
		return new FriendsScreen(appLibrary);
	}

}
