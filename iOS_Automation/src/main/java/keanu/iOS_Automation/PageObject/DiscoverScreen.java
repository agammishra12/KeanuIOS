package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;


public class DiscoverScreen {
	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	public static String navToPhotoStream = "id:Photo Stream";
	public static String navToCreateGroup = "id:Create a Group";
	public static String editGroupSubject = "xpath://XCUIElementTypeCell[XCUIElementTypeStaticText[@name='Empty room']]//XCUIElementTypeButton";
	public static String editGroupTextField = "xpath://XCUIElementTypeTextField[@value='Empty room']";
	public static String clickOKButton = "id:OK";
	public static String searchHistory = "id:info.guardianproject.keanuapp:id/menu_search";

	public static String waleSticket = "xpath://android.widget.ImageView[@content-desc='success-whale.png']";
	
	//Empty Room Back button
	public static String emptyRoomBackButton = "xpath://XCUIElementTypeButton[@name='Empty room']";
	public static String doneButton = "id:Done";
	
	
	public DiscoverScreen(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}
	
	
	public void navigateToPhotoStream() {
		AppLibrary.findElementForMobile(driver, navToPhotoStream).click();
//		AppLibrary.verifyText(driver, navToPhotoStream, "Photo Stream");
	}

	public void createAGroup(String roomName) throws Exception {
		AppLibrary.clickMobileElement(driver, navToCreateGroup);
		updateChatRoom(roomName);
	}
	
	
	public void updateChatRoom(String roomName) throws Exception {
		AppLibrary.clickMobileElement(driver, editGroupSubject);
		AppLibrary.enterMobileText(driver, editGroupTextField, roomName);
		AppLibrary.clickMobileElement(driver, clickOKButton);
		AppLibrary.sleep(3000);
		AppLibrary.clickMobileElement(driver, emptyRoomBackButton);
		AppLibrary.clickMobileElement(driver, doneButton);
	}

	public void navBack() {
		driver.navigate().back();
	}
}
