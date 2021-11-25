package EcommerceAppPractice;

import Configuration.TestingConfig;
import EcommerceAppPractice.PageObjects.CartPage;
import EcommerceAppPractice.PageObjects.LandingPage;
import EcommerceAppPractice.PageObjects.ProductsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ECommerceAppTests extends TestingConfig {

	LandingPage landingPage;
	ProductsPage productsPage;
	CartPage cartPage;

	@BeforeTest
	public void setupTest() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	}

	@Test
	public void totalIsCorrectInCart() throws IOException, InterruptedException {

		AppiumDriverLocalService service = startServer();
		AndroidDriver<AndroidElement> driver = initializeDriver("GeneralStoreApp");

		// Fill out form and click Let's Shop
		landingPage = new LandingPage(driver);

		landingPage.selectCountry("Australia");
		landingPage.enterName("Sarah");
		landingPage.selectGender("Female");
		productsPage = landingPage.clickLetsShopButton();
		Thread.sleep(2000); // Let the page transition.

		// Add the top two items to the cart.
		for(int i = 0; i < productsPage.getProducts().size(); i++) {
			productsPage.getAddToCartButtons().get(i).click();
		}

		// Open the cart
		cartPage = productsPage.goToCart();
		Thread.sleep(2000); // Let the page transition.

		// Verify the sum of the items matches the total in the cart.
		double sum = 0;
		for(int i = 0; i < cartPage.getProductsInCart().size(); i++) {
			String priceString = cartPage.getProductPrices().get(i).getText();
			sum += getAmount(priceString);
		}
		String totalString = cartPage.getCartTotal().getText();
		double total = getAmount(totalString);
		Assert.assertEquals(sum, total);
		service.stop();
	}


	// Helper Methods //
	private Double getAmount(String value) {
		value = value.substring(1);
		return Double.parseDouble(value);
	}
}
