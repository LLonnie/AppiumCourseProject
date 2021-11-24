package ApiAppPractice.PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	AndroidDriver<AndroidElement> driver;

	public HomePage(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); // Appium requires the casting of driver
	}

	public WebElement openPreferences() {
		return PreferencesMenuItem;
	}

	private @AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	WebElement PreferencesMenuItem;
}
