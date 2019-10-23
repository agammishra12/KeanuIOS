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

public class SignUpUiTest extends TestBase {

	public Logger logger;
	WelcomeScreen wc;
	SignUpScreen signUp;
	AccountSelectionScreen as;

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("Sign-Up ui Test");
		logger = Logger.getLogger("Sign-Up ui Test");
		PropertyConfigurator.configure("Log4j.properties");
		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance();
		wc = new WelcomeScreen(appLibrary);
		signUp = new SignUpScreen(appLibrary);
		as = new AccountSelectionScreen(appLibrary);

		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test
	public void SignInUi() throws Exception {
		wc.skipToSignIn();
		as.accountSelection(false);
		signUp.signUpUi();
		System.out.println("Verified Successfully ");

	}
}
