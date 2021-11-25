package ApiAppPractice;

import ApiAppPractice.PageObjects.HomePage;
import ApiAppPractice.PageObjects.PreferenceDependenciesPage;
import ApiAppPractice.PageObjects.PreferencesPage;
import Configuration.TestingConfig;
import TestData.ApiAppData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.Test;

import java.io.IOException;

public class ApiAppTests extends TestingConfig {

	@Test(dataProvider = "EmailNames", dataProviderClass = ApiAppData.class)
	public void canEnableWifi(String emailName) throws IOException, InterruptedException {

		AppiumDriverLocalService service = startServer();
		AndroidDriver<AndroidElement> driver = initializeDriver("ApiDemos");

		HomePage homePage = new HomePage(driver);
		homePage.openPreferences().click();

		PreferencesPage preferencesPage = new PreferencesPage(driver);
		preferencesPage.openPreferenceDependencies().click();

		PreferenceDependenciesPage preferenceDependenciesPage = new PreferenceDependenciesPage(driver);
		preferenceDependenciesPage.enableWifi().click();
		preferenceDependenciesPage.openWifiSettingsMenu().click();
		preferenceDependenciesPage.WiFiNameInput().sendKeys(emailName);
		preferenceDependenciesPage.WiFiSettingsButtons().get(1).click();
		service.stop();
	}
}
