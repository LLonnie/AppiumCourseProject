package EcommerceAppPractice;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

	AndroidDriver<AndroidElement> driver;

	public ProductsPage(AndroidDriver<AndroidElement> driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); // Appium requires the casting of driver
	}

	public List<WebElement> getProducts() {
		return products;
	}

	public List<WebElement> getAddToCartButtons() {
		return addToCartButton;
	}

	public CartPage goToCart() {
		cartButton.click();
		return new CartPage(driver);
	}


	private @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
		List<WebElement> products;

	private @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
		List<WebElement> addToCartButton;

	private @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
		WebElement cartButton;
}
