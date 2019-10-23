package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class AppFooters {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;

	// Footer Tabs
	public static String chatTab = "xpath:(//XCUIElementTypeTabBar//XCUIElementTypeButton)[1]";
	public static String friendsTab = "xpath:(//XCUIElementTypeTabBar//XCUIElementTypeButton)[2]";
	public static String discoverTab = "xpath:(//XCUIElementTypeTabBar//XCUIElementTypeButton)[3]";
	public static String meTab = "xpath:(//XCUIElementTypeTabBar//XCUIElementTypeButton)[4]";

	// chats
	public static String chatslabel = "xpath://XCUIElementTypeOther[@name='Chats']";
	public static String chosseFriendsaddButton = "id:Add";
	
	
	//ME
	public static String meLabel = "xpath://XCUIElementTypeOther[@name='Me']";

	// Discover
	public static String discoverScreenTitle = "xpath://XCUIElementTypeOther[@name='Discover']";

	public static String searchEditor = "id:info.guardianproject.keanuapp:id/search_src_text";
	public static String searchResultVerify = "xpath://android.widget.TextView[@text='replace']";
	public static String cancelSearchEditor = "id:info.guardianproject.keanuapp:id/search_close_btn";
	public static String friendsScreenTitle = "xpath://android.widget.TextView[@text='Keanu | Friends']";
	public static String chatsScreenTitle = "xpath://android.widget.TextView[@text='Keanu | Chats']";
	public static String archiveScreenTitle = "xpath://android.widget.TextView[@text='Keanu | Archive']";

	public static String activeMenuOption = "xpath://android.widget.TextView[@text='Active']";
	public static String archiveMenuOption = "xpath://android.widget.TextView[@text='Archive']";

	public static String myAccountsMenuOption = "xpath://android.widget.TextView[@text='My Accounts']";
	public static String settingsMenuOption = "xpath://android.widget.TextView[@text='Settings']";
	public static String lockAppMenuOption = "xpath://android.widget.TextView[@text='Lock the app']";
	public static String signOutMenuOption = "xpath://android.widget.TextView[@text='Sign out']";

	public static String navigateBack = "xpath:(//android.widget.ImageButton)[1]";

	public static String userName = "xpath://android.widget.TextView[contains(@resource-id,'providerName')]";
	public static String server = "xpath://android.widget.TextView[contains(@resource-id,'loginName')]";
	public static String fullScreen = "id:info.guardianproject.keanuapp:id/viewpager";

	public static String closeAppButton = "id:android:id/aerr_close";

	public AppFooters(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
		this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
	}

	public AppFooters verifyFootersTabs() throws Exception {
		AppLibrary.verifyMobileElement(driver, chatTab);
		AppLibrary.verifyMobileElement(driver, friendsTab);
		AppLibrary.verifyMobileElement(driver, discoverTab);
		AppLibrary.verifyMobileElement(driver, meTab);
		return new AppFooters(appLibrary);
	}

	// Chats
	public AppFooters navigateToChat() throws Exception {
		 AppLibrary.clickMobileElement(driver, chatTab);
		AppLibrary.verifyText(driver, chatslabel, "Chats");
		 verifyFootersTabs();
		return new AppFooters(appLibrary);
	}

	public void footerNavigate() throws Exception {
		AppLibrary.clickMobileElement(driver, chatTab);
		AppLibrary.clickMobileElement(driver, friendsTab);
		AppLibrary.clickMobileElement(driver, discoverTab);
		AppLibrary.clickMobileElement(driver, meTab);
	}

	public AppFooters navigateToDiscover() throws Exception {
		AppLibrary.clickMobileElement(driver, discoverTab);
		AppLibrary.verifyText(driver, discoverScreenTitle, "Discover");
		verifyFootersTabs();
		return new AppFooters(appLibrary);
	}

	public AppFooters navigateToMeTab() throws Exception {
		AppLibrary.clickMobileElement(driver, meTab);
		new MeScreen(appLibrary).verifyMeElements();
		return new AppFooters(appLibrary);
	}

	public void navigateChatToFriend() {
		appLibrary.horizontalRightSwipe(fullScreen);
	}

	public void navigateFriendToDiscover() throws Exception {
		appLibrary.horizontalRightSwipe(fullScreen);
	}

	public void navigateDiscoverToMyProfile() throws Exception {
		appLibrary.horizontalRightSwipe(fullScreen);
	}

	public void navigateMyProfileToDiscover() throws Exception {
		appLibrary.horizontalLeftSwipe(fullScreen);
	}

	public void navigateDiscoverToFriend() throws Exception {
		appLibrary.horizontalLeftSwipe(fullScreen);
	}

	public void navigateFriendToChat() throws Exception {
		appLibrary.horizontalLeftSwipe(fullScreen);
	}

	public void verifyChatsScreen() throws Exception {
		AppLibrary.verifyText(driver, chatsScreenTitle, "Keanu | Chats");
		verifyFootersTabs();
	}

	public void verifyFriendScreen() throws Exception {
		AppLibrary.verifyText(driver, friendsScreenTitle, "Keanu | Friends");
		verifyFootersTabs();
	}

	public void verifyDiscoverScreen() throws Exception {
		AppLibrary.verifyText(driver, discoverScreenTitle, "Keanu | Discover");
		verifyFootersTabs();
	}

	public void verifyMyProfileScreen() throws Exception {
		// AppLibrary.verifyText(driver, myProfileScreenTitle, "Keanu | Me");
		verifyFootersTabs();
	}

	public void navBack() throws Exception {
		driver.navigate().back();
	}
}
