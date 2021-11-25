package EcommerceAppPractice.PageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

	AndroidDriver<AndroidElement> driver;

	public CartPage(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); // Appium requires the casting of driver
	}

	public List<WebElement> getProductsInCart() {
		return productsInCart;
	}

	public List<WebElement> getProductPrices() {
		return productPrice;
	}

	public WebElement getCartTotal() {
		return total;
	}

	private @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
		List<WebElement> productsInCart;

	private @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
			List<WebElement> productPrice;

	private @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
			WebElement total;
}
