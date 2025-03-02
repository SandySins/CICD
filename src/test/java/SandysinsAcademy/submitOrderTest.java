package SandysinsAcademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SandysinsAcademy.TestComponent.BaseTests;
import sandysins.pageobjects.CartPage;
import sandysins.pageobjects.CheckoutPage;
import sandysins.pageobjects.ConfirmationPage;
import sandysins.pageobjects.OrderPage;
import sandysins.pageobjects.productCatalogue;

public class submitOrderTest extends BaseTests{
	public String ProductName = "ZARA COAT 3";
	public String CountryName = "India";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
	
		productCatalogue ProductCatalogue = LP.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = ProductCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		Assert.assertTrue(match);
		checkoutPage.selectCountry(CountryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String ThanksPage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(ThanksPage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		productCatalogue ProductCatalogue = LP.loginApplication("sunny938650@gmail.com", "Kratos@1234");
		OrderPage ordersPage = ProductCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay("ADIDAS ORIGINAL"));
		
}
	
	
	
	
	
	
	//Extent Reports - 
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SandysinsAcademy//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
	
	
	
//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//	    
//	  }
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	
}
