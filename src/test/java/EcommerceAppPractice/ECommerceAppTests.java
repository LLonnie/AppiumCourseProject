package EcommerceAppPractice;

import Configuration.TestingConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ECommerceAppTests extends TestingConfig {

	LandingPage landingPage;
	ProductsPage productsPage;
	CartPage cartPage;

	@Test
	public void totalIsCorrectInCart() throws IOException, InterruptedException {
		AndroidDriver<AndroidElement> driver = initializeDriver("GeneralStoreApp");

		// Fill out form and click Let's Shop
		landingPage = new LandingPage(driver);
		landingPage.selectCountry();
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
	}


	// Helper Methods //
	private Double getAmount(String value) {
		value = value.substring(1);
		return Double.parseDouble(value);
	}
}
