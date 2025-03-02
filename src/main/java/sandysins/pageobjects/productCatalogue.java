package sandysins.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandysins.AbstractCompenonts.AbstractComponent;

public class productCatalogue extends AbstractComponent {
	WebDriver driver;

	public productCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement animating;

	By ProductsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(ProductsBy);
		return products;
	}
	public WebElement getProductByName(String ProductName) {
		WebElement prod = getProductList().stream()
				.filter((product) -> product.findElement(By.cssSelector("b")).getText().equals(ProductName))
				.findFirst().orElse(null);
		return prod;
	}
	public void addProductToCart(String ProductName) {
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(animating);
		
	}
	

}
