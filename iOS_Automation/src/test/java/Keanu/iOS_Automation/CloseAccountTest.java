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

public class CloseAccountTest extends TestBase {

	public Logger logger;
	SignInScreen signIn;
	MeScreen me;
	AppFooters footer;
	WelcomeScreen wc;
	AccountSelectionScreen ac;
	ChatScreen cs;
	SignUpScreen signUp;
	AccountSelectionScreen as;
	String User;
	String Pass = "pass@123456";
	String server="https://neo.keanu.im";

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
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void userSignUp() throws Exception {
		Integer randInt = AppLibrary.randIntDigits(9999, 999999);
		User = "testuser" + randInt;
		System.out.println(User);
		wc.skipToSignIn();
		as.accountSelection(false);
		signUp.signUp(User, Pass, server);
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs.closeScreen();
		footer.navigateToMeTab();
		AppLibrary.verifyText(driver, MeScreen.userName, User);
		appLibrary.resetApp();
	}
	
	@Test(dependsOnMethods = "userSignUp")
	public void verifyUserLogin() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(true);
		signIn.signIn(User, Pass, server);
		AppLibrary.clickMobileElement(driver, PushScreen.skipButton);
		cs.closeScreen();
		AppLibrary.verifyMobileElement(driver, SignInScreen.chatsLabel);

	}
	
	@Test(dependsOnMethods = "verifyUserLogin")
	public void closeAccount() throws Exception {
		footer.navigateToMeTab();
		me.closeAccount(Pass);
		appLibrary.killApp();

	}

	@Test(dependsOnMethods = "closeAccount")
	public void verifyLoginWithDeletedUser() throws Exception {
		appLibrary.reopenApp();
		wc.skipToSignIn();
		ac.accountSelection(true);
		//unsubscribed user
		signIn.signIn(User, Pass, server);
		//Validation
		AppLibrary.verifyMobileElement(driver, SignInScreen.signInValidation);
	}

}
