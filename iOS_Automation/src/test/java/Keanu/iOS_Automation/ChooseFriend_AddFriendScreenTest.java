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
import keanu.iOS_Automation.PageObject.FriendsScreen;
import keanu.iOS_Automation.PageObject.MeScreen;
import keanu.iOS_Automation.PageObject.PushScreen;
import keanu.iOS_Automation.PageObject.SignInScreen;
import keanu.iOS_Automation.PageObject.SignUpScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;

public class ChooseFriend_AddFriendScreenTest extends TestBase {

	public Logger logger;
	SignInScreen signIn;
	MeScreen me;
	AppFooters footer;
	WelcomeScreen wc;
	AccountSelectionScreen ac;
	ChatScreen cs;
	SignUpScreen signUp;
	AccountSelectionScreen as;
	FriendsScreen fs;
	String User="testuser463650";
	String server = "https://neo.keanu.im";

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("change Pass Test");
		logger = Logger.getLogger("change Pass Test");
		PropertyConfigurator.configure("Log4j.properties");
		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance();
		signIn = new SignInScreen(appLibrary);
		footer = new AppFooters(appLibrary);
		wc = new WelcomeScreen(appLibrary);
		ac = new AccountSelectionScreen(appLibrary);
		cs = new ChatScreen(appLibrary);
		me = new MeScreen(appLibrary);
		signUp = new SignUpScreen(appLibrary);
		as = new AccountSelectionScreen(appLibrary);
		fs = new FriendsScreen(appLibrary);
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	
	// Blocked After Login “Choose Friends and add friends” screens are automatically opened

	@Test
	public void verifyChooseFriAddFriScreenAfterLogin() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(true);
		signIn.signIn(User, "pass@123456", server);
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs.VerifyChooseFriAndAddFriendScreen();
		AppLibrary.verifyMobileElement(driver, SignInScreen.chatsLabel);
	}
	
	@Test
	public void AddFriendsAndChats() throws Exception {
		fs.InviteFriend("@testuser2:neo.keanu.im");
		AppLibrary.verifyText(driver,ChatScreen.chatRoomUserLabel,"testuser2");
		
		
	}
	
	
	@Test
	public void verifyChoose_AddFriScreenAbsent() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(true);
		signIn.signIn(User, "pass@123456", server);
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs.VerifyChooseFriAndAddFriendScreen();
		AppLibrary.verifyMobileElement(driver, SignInScreen.chatsLabel);
	}
	

}
