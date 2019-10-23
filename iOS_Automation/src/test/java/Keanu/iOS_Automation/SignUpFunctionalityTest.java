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
import keanu.iOS_Automation.PageObject.SignUpScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;

public class SignUpFunctionalityTest extends TestBase {

	public Logger logger;
	SignUpScreen signUp;
	WelcomeScreen wc;
	AccountSelectionScreen as;
	Integer randInt;
	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("Sign-Up Test");
		logger = Logger.getLogger("Sign-Up Test");
		PropertyConfigurator.configure("Log4j.properties");
		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance();
		signUp = new SignUpScreen(appLibrary);
		wc = new WelcomeScreen(appLibrary);
		as = new AccountSelectionScreen(appLibrary);
		randInt = AppLibrary.randIntDigits(9999, 999999);
		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void userSignUp() throws Exception {
		String User = "TestUser" + randInt;
		wc.skipToSignIn();
		as.accountSelection(false);
		signUp.signUp(User, "pass@123456", "https://neo.keanu.im");

	}
}
