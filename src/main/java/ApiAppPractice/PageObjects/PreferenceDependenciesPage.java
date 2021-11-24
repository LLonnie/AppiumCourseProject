package ApiAppPractice.PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PreferenceDependenciesPage {

	AndroidDriver<AndroidElement> driver;

	public PreferenceDependenciesPage(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public WebElement enableWifi() {
		return WiFiCheckbox;
	}

	public WebElement openWifiSettingsMenu() {
		return WiFiSettings;
	}

	public WebElement WiFiNameInput() {
		return WiFISettingsNameInput;
	}

	public List<WebElement> WiFiSettingsButtons() {
		return WiFiSettingsButtons;
	}

	private @AndroidFindBy(id = "android:id/checkbox")
	WebElement WiFiCheckbox;

	private @AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
	WebElement WiFiSettings;

	private @AndroidFindBy(className = "android.widget.EditText")
	WebElement WiFISettingsNameInput;

	private @AndroidFindBy(className = "android.widget.Button")
	List<WebElement> WiFiSettingsButtons;
}
