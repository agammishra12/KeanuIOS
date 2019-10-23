package Keanu.iOS_Automation;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;
import keanu.iOS_Automation.Library.TestBase;
import keanu.iOS_Automation.PageObject.AccountSelectionScreen;
import keanu.iOS_Automation.PageObject.AppFooters;
import keanu.iOS_Automation.PageObject.ChatScreen;
import keanu.iOS_Automation.PageObject.PushScreen;
import keanu.iOS_Automation.PageObject.SignInScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;


public class ChatCreateEditTest extends TestBase {

	public Logger logger;
	SignInScreen signIn1;
	WelcomeScreen wc1;
	ChatScreen cs1;
	AppFooters footer1;
	AccountSelectionScreen ac1;

	SignInScreen signIn2;
	WelcomeScreen wc2;
	ChatScreen cs2;
	AppFooters footer2;
	AccountSelectionScreen ac2;
	Integer randInt;
	String roomName;
	String editroomName;
	String sendMsg;
	String sendMsg1;

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("Chat Create Edit Test 1");
		appLibrary1 = new AppLibrary("Chat Create Edit Test 2");
		logger = Logger.getLogger("Chat Create Edit Test");
		PropertyConfigurator.configure("Log4j.properties");

		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance("device1", "4723");
		driver1 = (AppiumDriver<MobileElement>) appLibrary1.getDriverInstance("device2", "5000");	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		signIn1 = new SignInScreen(appLibrary);
		wc1 = new WelcomeScreen(appLibrary);
		cs1 = new ChatScreen(appLibrary);
		footer1 = new AppFooters(appLibrary);
		ac1 = new AccountSelectionScreen(appLibrary);

		signIn2 = new SignInScreen(appLibrary1);
		wc2 = new WelcomeScreen(appLibrary1);
		cs2 = new ChatScreen(appLibrary1);
		footer2 = new AppFooters(appLibrary1);
		ac2 = new AccountSelectionScreen(appLibrary1);

		randInt = AppLibrary.randIntDigits(9999, 999999);
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void userLogin() throws Exception {
		wc1.skipToSignIn();
		ac1.accountSelection(true);
		signIn1.signIn("testuser463650", "pass@123456", "https://neo.keanu.im");
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs1.closeScreen();
		footer1.navigateToChat();
		
		
		wc2.skipToSignIn();
		ac2.accountSelection(true);
		signIn2.signIn("testuser751258", "pass@123456", "https://neo.keanu.im");
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs2.closeScreen();
		footer2.navigateToChat();
	}

	@Test(dependsOnMethods="userLogin")
	public void createChatRoom() throws Exception {
		roomName = "New Test Chat room " + randInt;
		editroomName = "Chat room edited " + randInt;

//		cs1.verifyfindFriendUI("testuser129");
//		cs1.createChatRoomBySearchingFriend("testuser2", roomName);
//		cs1.verifyChatRoom(roomName);
//
//		cs2.joinChatGroups();
//		cs2.verifyChatRoom(roomName);
//
//		cs1.editChatRoomName(roomName, editroomName);
//		cs1.verifyChatRoom(editroomName);
//		header1.navigateToDiscover();
//		header1.navigateToChat();
//		cs1.verifyChatRoom(editroomName);
//
//		header2.navigateToDiscover();
//		header2.navigateToChat();
//		cs2.verifyChatRoom(editroomName);
	}
}
