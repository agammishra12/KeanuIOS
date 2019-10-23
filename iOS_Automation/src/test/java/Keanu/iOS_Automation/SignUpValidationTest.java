package Keanu.iOS_Automation;

import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;
import keanu.iOS_Automation.Library.TestBase;
import keanu.iOS_Automation.PageObject.AccountSelectionScreen;
import keanu.iOS_Automation.PageObject.SignUpScreen;
import keanu.iOS_Automation.PageObject.WelcomeScreen;

public class SignUpValidationTest extends TestBase {

	public Logger logger;
	WelcomeScreen wc;
	SignUpScreen signUp;
	AccountSelectionScreen as;

	@DataProvider(name = "Registration")
	public String[][] getRegistrationDataFromExcelOne() throws Exception {

		String str[][] = AppLibrary.readExcel(
				"/Users/Apple/Documents/Keanu_Project/keanu_automation_Code/keanu-appium-testing-iOS/iOS_Automation/Resources/SignupValidation1.xls",
				0);
		return str;
	}

	@BeforeClass
	public void setUp() throws Exception {
		appLibrary = new AppLibrary("Sign-Up validation Test");
		logger = Logger.getLogger("Sign-Up validation Test");
		PropertyConfigurator.configure("Log4j.properties");
		driver = (AppiumDriver<MobileElement>) appLibrary.getDriverInstance();
		wc = new WelcomeScreen(appLibrary);
		signUp = new SignUpScreen(appLibrary);
		as = new AccountSelectionScreen(appLibrary);

		Reporter.log(
				"<h1><Center><Font face=\"arial\" color=\"Orange\">Log Summary</font></Center><h1><table border=\"1\" bgcolor=\"lightgray\">");
	}

	@Test(dataProvider = "Registration")
	public void SignUpWithExistingUser(String username, String password, String validation, String relaunchApp,
			String exeIndicator) throws Exception {
		if (exeIndicator.equalsIgnoreCase("Yes")) {
			wc.skipToSignIn();
			as.accountSelection(false);
			signUp.signUpValidation(username, password, validation);
			if (relaunchApp.equalsIgnoreCase("Yes")) {
				appLibrary.killApp();
				appLibrary.reopenApp();
			}
			System.out.println("Verified Successfully ");
		}
	}

}
