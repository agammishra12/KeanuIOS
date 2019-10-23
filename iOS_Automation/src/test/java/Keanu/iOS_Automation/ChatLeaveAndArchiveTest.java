package Keanu.iOS_Automation;

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
import keanu.iOS_Automation.PageObject.SignInScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;


public class ChatLeaveAndArchiveTest extends TestBase {

	public Logger logger;
	SignInScreen signIn1;
	WelcomeScreen wc1;
	ChatScreen cs1;
	AppFooters footer1;
	AccountSelectionScreen as1;

	SignInScreen signIn2;
	WelcomeScreen wc2;
	ChatScreen cs2;
	AppFooters footer2;
	Integer randInt;
	String roomName;
	String sendMsg;
	String sendMsg1;
	AccountSelectionScreen as2;
	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("Chat Leave And Archive Test 1");
		appLibrary1 = new AppLibrary("Chat Leave And Archive Test 2");
		logger = Logger.getLogger("Chat Leave And Archive Test");
		PropertyConfigurator.configure("Log4j.properties");

		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance("device1", "4723");
		driver1 = (AppiumDriver<MobileElement>) appLibrary1.getDriverInstance("device2", "5000");

		signIn1 = new SignInScreen(appLibrary);
		wc1 = new WelcomeScreen(appLibrary);
		cs1 = new ChatScreen(appLibrary);
		footer1 = new AppFooters(appLibrary);
		as1 = new AccountSelectionScreen(appLibrary);

		signIn2 = new SignInScreen(appLibrary1);
		wc2 = new WelcomeScreen(appLibrary1);
		cs2 = new ChatScreen(appLibrary1);
		footer2 = new AppFooters(appLibrary1);
		as2 = new AccountSelectionScreen(appLibrary1);

		randInt = AppLibrary.randIntDigits(9999, 999999);
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test(priority = 1)
	public void userLogin() throws Exception {
		wc1.skipToSignIn();
		as1.accountSelection(true);
		signIn1.signIn("testuser1", "pass123", "neo.keanu.im");

		wc2.skipToSignIn();
		as2.accountSelection(true);
		signIn2.signIn("testuser2", "pass123", "neo.keanu.im");
	}

	@Test(priority = 2)
	public void createChatRoom() throws Exception {
		roomName = "New Test Chat room " + randInt;

//		cs1.verifyfindFriendUI("testuser1");
//		cs1.createChatRoomBySearchingFriend("testuser2", roomName);
//
//		cs1.verifyChatRoom(roomName);
//
//		cs2.joinChatGroups();
//		cs2.verifyChatRoom(roomName);
	}

	@Test(priority = 3)
	public void archiveRoomFromGroupInfo() throws Exception {
//		cs1.archiveChatRoomFromChatInfo(roomName);
//		cs1.verifyChatRoomAbsence(roomName);
//
//		header2.navigateToActiveChats();
//		cs2.verifyChatRoom(roomName);
//
//		header1.navigateToArchiveChats();
//		cs1.unArchiveChatRoom(roomName);
//		
//		header2.navigateToActiveChats();
//		cs2.verifyChatRoom(roomName);
//
//		cs1.verifyChatRoomAbsence(roomName);
//		header1.navigateToActiveChats();
//		header1.navigateToDiscover();
//		header1.navigateToActiveChats();
//		cs1.verifyChatRoom(roomName);
//		
//		header2.navigateToActiveChats();
//		cs2.verifyChatRoom(roomName);
	}

	@Test(priority = 4)
	public void leaveRoomFromGroupInfo() throws Exception {
//		cs1.leaveRoom(roomName);
//		cs1.verifyChatRoomAbsence(roomName);
//		header1.navigateToArchiveChats();
//		cs1.verifyChatRoomAbsence(roomName);
//
//		cs2.leaveRoom(roomName);
//		cs2.verifyChatRoomAbsence(roomName);
//		header2.navigateToArchiveChats();
//		cs2.verifyChatRoomAbsence(roomName);
	}
}
