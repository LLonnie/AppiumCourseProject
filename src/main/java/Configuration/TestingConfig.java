package Configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestingConfig {

	public static AppiumDriverLocalService service;

	public static AndroidDriver<AndroidElement> initializeDriver(String appName) throws IOException, InterruptedException {

		AndroidDriver<AndroidElement> driver;

		FileInputStream propertiesFile = new FileInputStream("src/main/resources/global.properties");
		Properties properties = new Properties();
		properties.load(propertiesFile);

		File appApk = new File("src/apps", properties.getProperty(appName));

		// Start Emulator


		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		if(properties.getProperty("Device").contains("Emulator")) {
			startEmulator();
		}
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("Device"));
		desiredCapabilities.setCapability(MobileCapabilityType.APP, appApk.getAbsolutePath());
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static AppiumDriverLocalService startServer() {
		boolean isServerRunning = checkIfServerIsRunning(4723);
		if (!isServerRunning) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}

	public static void stopServer() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.stop();
	}

	private static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here then it means that the port is in use.
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	private static void startEmulator() throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", System.getProperty("user.dir") + "/src/main/resources/startEmulator.bat.lnk");
		Process process = processBuilder.start();
//		Runtime.getRuntime().exec("src/main/resources/startEmulator.bat.lnk");
		Thread.sleep(7500); // Wait for Emulator to open.
	}
}
