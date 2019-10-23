package keanu.iOS_Automation.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private String appdir;
	private String appname;
	private String browserName;
	private String deviceName1;
	private String deviceName2;
	private String deviceVersion;
	private String appActivity;
	private String bundleId;
	private Boolean isBrowserStackExecution;
	private String browserStackUserName;
	private String browserStackAuthKey;
	private String browserStackBrowserVersion;
	private String browserStackOS;
	private String browserStackOSVersion1;
	private String browserStackOSVersion2;
	private String browserStackPlatform;
	private String browserStackDevice1;
	private String browserStackDevice2;
	private String isEmulator;
	private String browserVersion;
	private String os;
	private String app_Url;

	public Configuration() {
		final Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("config.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		isBrowserStackExecution = Boolean.parseBoolean(prop.getProperty("isbrowserstack.execution", "false").trim());
		browserStackUserName = prop.getProperty("browserstack.username");
		browserStackAuthKey = prop.getProperty("browserstack.authkey");
		browserStackBrowserVersion = prop.getProperty("browserstack.browserversion");
		browserStackOS = prop.getProperty("browserstack.os");
		browserStackOSVersion1 = prop.getProperty("browserstack.osversion1");
		browserStackOSVersion2 = prop.getProperty("browserstack.osversion2");
		browserStackPlatform = prop.getProperty("browserstack.platform");
		browserStackDevice1 = prop.getProperty("browserstack.devicename1");
		browserStackDevice2 = prop.getProperty("browserstack.devicename2");
		isEmulator = prop.getProperty("browserstack.isEmulator");
		browserVersion = prop.getProperty("browser.version");
		browserName = prop.getProperty("os.name");
		os = prop.getProperty("os");
		appdir = prop.getProperty("app.dir");
		appname = prop.getProperty("app.name");
		deviceName1 = prop.getProperty("device.name1");
		deviceName2 = prop.getProperty("device.name2");
		bundleId = prop.getProperty("app.bundleId");
		
		app_Url = prop.getProperty("app_URL");
	}

	public boolean isBrowserStackExecution() {
		return isBrowserStackExecution;
	}

	public String getBrowserStackUserName() {
		return browserStackUserName;
	}

	public String getBrowserStackAuthKey() {
		return browserStackAuthKey;
	}

	public String getBrowserStackBrowserVersion() {
		return browserStackBrowserVersion;
	}

	public String getBrowserStackOS() {
		return browserStackOS;
	}

	public String getBrowserStackOSVersion1() {
		return browserStackOSVersion1;
	}
	
	public String getBrowserStackOSVersion2() {
		return browserStackOSVersion2;
	}

	public String getBrowserStackPlatform() {
		return browserStackPlatform;
	}

	public String getBrowserStackDevice1() {
		return browserStackDevice1;
	}
	
	public String getBrowserStackDevice2() {
		return browserStackDevice2;
	}
	
	public String getBrowserStackAppURL() {
		return app_Url;
	}

	public String getBrowserStackIsEmulator() {
		return isEmulator;
	}

	public String getOS() {
		return os;
	}

	public String getappDir() {
		return appdir;
	}

	public String getappName() {
		return appname;
	}

	public String getBrowserName() {
		return browserName;
	}

	public String getDeviceName1() {
		return deviceName1;
	}

	public String getDeviceName2() {
		return deviceName2;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public String getAppBundleId() {
		return bundleId;
	}

	public String getAppActivity() {
		return appActivity;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}
}