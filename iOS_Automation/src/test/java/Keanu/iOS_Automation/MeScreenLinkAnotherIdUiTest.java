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
import keanu.iOS_Automation.PageObject.MeScreen;
import keanu.iOS_Automation.PageObject.PushScreen;
import keanu.iOS_Automation.PageObject.SignInScreen;
import keanu.iOS_Automation.PageObject.SignUpScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;

public class MeScreenLinkAnotherIdUiTest extends TestBase {

	public Logger logger;
	SignInScreen signIn;
	MeScreen me;
	AppFooters footer;
	WelcomeScreen wc;
	AccountSelectionScreen ac;
	ChatScreen cs;
	SignUpScreen signUp;
	AccountSelectionScreen as;

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("linkId Ui Test");
		logger = Logger.getLogger("linkId Ui Test");
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
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void userLogin() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(true);
		signIn.signIn("testuser463650", "pass@123456", "https://neo.keanu.im");
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs.closeScreen();
		AppLibrary.verifyMobileElement(driver, SignInScreen.chatsLabel);
	}

	@Test(dependsOnMethods = "userLogin")
	public void linkIdUi() throws Exception {
		footer.navigateToMeTab();
		me.linkAnotherIdUi();
		System.out.println("Verified Successfully ");
	}
}
