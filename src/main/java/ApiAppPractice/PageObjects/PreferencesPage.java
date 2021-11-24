package ApiAppPractice.PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PreferencesPage {

	AndroidDriver<AndroidElement> driver;

	public PreferencesPage(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public WebElement openPreferenceDependencies() {
		return PreferenceDependenciesMenuItem;
	}

	private @AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
	WebElement PreferenceDependenciesMenuItem;
}
