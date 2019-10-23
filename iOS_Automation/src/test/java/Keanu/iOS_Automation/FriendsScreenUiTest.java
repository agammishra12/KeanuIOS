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
import keanu.iOS_Automation.PageObject.ChatScreen;
import keanu.iOS_Automation.PageObject.FriendsScreen;
import keanu.iOS_Automation.PageObject.PushScreen;
import keanu.iOS_Automation.PageObject.SignInScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;

public class FriendsScreenUiTest extends TestBase {

	public Logger logger;
	WelcomeScreen wc;
	SignInScreen signIn;
	AccountSelectionScreen as;
	FriendsScreen fs;
	ChatScreen cs;

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("friendUiTest");
		logger = Logger.getLogger("friendUiTest");
		PropertyConfigurator.configure("Log4j.properties");
		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance();
		wc = new WelcomeScreen(appLibrary);
		signIn = new SignInScreen(appLibrary);
		as = new AccountSelectionScreen(appLibrary);
		fs = new FriendsScreen(appLibrary);
		cs = new ChatScreen(appLibrary);

		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void signIn() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(true);
		signIn.signIn("testuser2", "pass123", "https://neo.keanu.im");
	}

	@Test(dependsOnMethods = "signIn")
	public void friendUiTest() throws Exception {
		AppLibrary.clickMobileElement(driver,PushScreen.skipButton );
		cs.closeScreen();
		fs.friendsScreenUi();
		System.out.println("Verified Successfully ");

	}

}
