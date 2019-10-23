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
import keanu.iOS_Automation.PageObject.DiscoverScreen;
import keanu.iOS_Automation.PageObject.PushScreen;
import keanu.iOS_Automation.PageObject.SignInScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;


public class DiscoverScreenChatGroupTest extends TestBase {

	public Logger logger;
	SignInScreen signIn;
	AppFooters footer;
	WelcomeScreen wc;
	ChatScreen cs;
	DiscoverScreen disc;
	AccountSelectionScreen as;
	Integer randInt;

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("Discover Screen Chat Group Test");
		logger = Logger.getLogger("Discover Screen Chat Group Test");
		PropertyConfigurator.configure("Log4j.properties");

		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance();
		signIn = new SignInScreen(appLibrary);
		footer = new AppFooters(appLibrary);
		wc = new WelcomeScreen(appLibrary);
		disc = new DiscoverScreen(appLibrary);
		cs = new ChatScreen(appLibrary);
		as = new AccountSelectionScreen(appLibrary);
		randInt = AppLibrary.randIntDigits(9999, 999999);
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void userLogin() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(true);
		signIn.signIn("testuser463650", "pass@123456", "https://neo.keanu.im");
	}

	@Test(dependsOnMethods="userLogin")
	public void createChatGroup() throws Exception {
		String chatRoom = "New Chat Room " + randInt;
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs.closeScreen();
		footer.navigateToDiscover();
		disc.createAGroup(chatRoom);
		footer.navigateToChat();
		cs.navigateToChatRoom(chatRoom);
		disc.navBack();
	}
}
