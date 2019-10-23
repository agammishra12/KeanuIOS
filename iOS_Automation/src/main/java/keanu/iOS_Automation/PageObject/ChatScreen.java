package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class ChatScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	public static String clickOnParticularChatRoom = "xpath://XCUIElementTypeStaticText[@name='Replace']";
	public static String chooseFriendLabel = "xpath://XCUIElementTypeOther[@name='Choose Friends']";
	public static String addButton = "id:Add";

	// choose friend Screen

	public static String cancelButton = "id:Cancel";
	public static String chooseFriendsLabel = "xpath://XCUIElementTypeOther[@name='Choose Friends']";
	public static String doneButton = "id:Done";
	public static String addFriendLogo = "xpath:(//XCUIElementTypeStaticText)[1]";
	public static String addFriendLabel = "id:Add Friend";

	/// Archive and Delete
	public static String deleteChat = "id:Delete";
	public static String archiveChat = "id:Archive";

	public static String activeChat = "id:Active";
	public static String unarchiveChat = "id:Unarchive";

	// chats

	public static String chatRoomUserLabel = "xpath://XCUIElementTypeOther[@name='Replace']";
	public static String done = "xpath://XCUIElementTypeButton[@name='Done']";
	public static String sendButton = "xpath:(//XCUIElementTypeStaticText)[7]";
	

	public ChatScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public void navigateToChatRoom(String chatRoom) {
		AppLibrary.findElementForMobile(driver, clickOnParticularChatRoom.replace("Replace", chatRoom)).click();
	}

	public void closeScreen() throws Exception {
		boolean VerifyAddFriendLabel = AppLibrary.isElementPresent(driver, FriendsScreen.addFriendLabel);

		if (VerifyAddFriendLabel) {
			AppLibrary.clickMobileElement(driver, FriendsScreen.backButton);
		}

		boolean VerifyChooseFriendLabel = AppLibrary.isElementPresent(driver, chooseFriendLabel);
		if (VerifyChooseFriendLabel) {
			AppLibrary.clickMobileElement(driver, FriendsScreen.cancelButton);
		}
	}

	public ChatScreen VerifyChooseFriAndAddFriendScreen() throws Exception {
		new FriendsScreen(appLibrary).friendsScreenUi();
		AppLibrary.clickMobileElement(driver, FriendsScreen.backButton);
		ChooseFriendScreenUi();
		AppLibrary.clickMobileElement(driver, FriendsScreen.cancelButton);
		return new ChatScreen(appLibrary);

	}

	public ChatScreen ChooseFriendScreenUi() {
		AppLibrary.verifyMobileElement(driver, cancelButton, true);
		AppLibrary.verifyMobileElement(driver, chooseFriendsLabel);
		AppLibrary.verifyMobileElement(driver, doneButton, true);
		AppLibrary.verifyMobileElement(driver, addFriendLabel, true);
		AppLibrary.verifyMobileElement(driver, addFriendLogo, true);
		AppLibrary.verifyMobileElement(driver, addFriendLabel, true);
		return new ChatScreen(appLibrary);
	}

}
