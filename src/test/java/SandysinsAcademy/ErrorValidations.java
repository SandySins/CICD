package SandysinsAcademy;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SandysinsAcademy.TestComponent.BaseTests;
import sandysins.pageobjects.CartPage;
import sandysins.pageobjects.productCatalogue;



public class ErrorValidations extends BaseTests{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = SandysinsAcademy.TestComponent.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

	
		LP.loginApplication("sunny938650@gmail.com", "Kratos@1235");
		Assert.assertEquals("Incorrect email or password.", LP.getErrorMessage());

	}
	

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String productName = "ZARA COAT 3";
		productCatalogue productCatalogue = LP.loginApplication("sunny938650@gmail.com", "Kratos@1234");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	

	}


	
}
