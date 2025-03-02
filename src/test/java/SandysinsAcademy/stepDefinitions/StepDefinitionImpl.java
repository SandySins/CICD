package SandysinsAcademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SandysinsAcademy.TestComponent.BaseTests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sandysins.pageobjects.CartPage;
import sandysins.pageobjects.CheckoutPage;
import sandysins.pageobjects.ConfirmationPage;
import sandysins.pageobjects.landingPage;
import sandysins.pageobjects.productCatalogue;

public class StepDefinitionImpl extends BaseTests {
	public landingPage LP;
	public productCatalogue ProductCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		LP = launchApplication();
	}

	@Given("^I Logged in with Username (.+) and Password (.+)$")
	public void logged_in_username_and_password(String userName, String password) {
		ProductCatalogue = LP.loginApplication(userName, password);
	}

	@When("^I add the product (.+) to cart$")
	public void I_add_the_product_to_cart(String productName) {
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit order$")
	public void checkout_And_Submit(String productName) {
		CartPage cartPage = ProductCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		Assert.assertTrue(match);
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmation page")
	public void verify_Message(String strArgs1) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(strArgs1));
		driver.close();
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, LP.getErrorMessage());
		driver.close();
	}

}
