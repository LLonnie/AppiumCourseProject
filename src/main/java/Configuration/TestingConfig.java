package Configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestingConfig {

	public static AndroidDriver<AndroidElement> initializeDriver(String appName) throws IOException {

		AndroidDriver<AndroidElement> driver;

		FileInputStream propertiesFile = new FileInputStream("src/main/resources/global.properties");
		Properties properties = new Properties();
		properties.load(propertiesFile);

		File appApk = new File("src/apps", properties.getProperty(appName));

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("Device"));
		desiredCapabilities.setCapability(MobileCapabilityType.APP, appApk.getAbsolutePath());
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
