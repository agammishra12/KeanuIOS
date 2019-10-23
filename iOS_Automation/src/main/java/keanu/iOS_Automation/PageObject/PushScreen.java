package keanu.iOS_Automation.PageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import keanu.iOS_Automation.Library.AppLibrary;

public class PushScreen {

	private AppLibrary appLibrary;
	AppiumDriver<MobileElement> driver;
	
	/// enable push screen

		public static String signInButton = "id:Sign In";
		public static String enablePushLabel = "xpath://XCUIElementTypeOther[@name='Enable Push']";
		public static String messageImg = "id:onboarding_push";
		public static String permissionLabel = "xpath://XCUIElementTypeTextView[contains(@value,'Keanu needs permission')]";
		public static String enablePushButton = "xpath://XCUIElementTypeButton[@name='Enable Push']";
		public static String skipButton = "id:Skip";
	

		public PushScreen(AppLibrary appLibrary) {
			this.appLibrary = appLibrary;
			this.driver = (AppiumDriver<MobileElement>) appLibrary.getCurrentDriverInstance();
		}
		
		
		
		
		
		
		
		
		
		
		
}
