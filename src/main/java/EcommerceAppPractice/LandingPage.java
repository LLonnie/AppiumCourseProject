package EcommerceAppPractice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	AndroidDriver<AndroidElement> driver;

	public LandingPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void selectCountry(String country) {
		countryDropdown.click();
		Utilities utilities = new Utilities(driver);
		utilities.scrollToText(country);
		driver.findElementByXPath("//*[@text='" + country + "']").click();
	}

	public void enterName(String name) {
		nameInputField.sendKeys(name);
	}

	public void selectGender(String gender) {
		if (gender.equals("Male")) {
			maleRadioButton.click();
		} else if (gender.equals("Female")) {
			femaleRadioButton.click();
		} else {
			throw new InvalidArgumentException("You entered an invalid gender");
		}
	}

	public ProductsPage clickLetsShopButton() {
		letsShopButton.click();
		return new ProductsPage(driver);
	}

	private @AndroidFindBy(className = "android.widget.Spinner")
		WebElement countryDropdown;

	private @AndroidFindBy(className = "android.widget.EditText")
			WebElement nameInputField;

	private @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
		WebElement maleRadioButton;

	private @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
			WebElement femaleRadioButton;

	private @AndroidFindBy(className = "android.widget.Button")
		WebElement letsShopButton;
}
