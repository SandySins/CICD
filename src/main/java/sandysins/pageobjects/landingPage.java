package sandysins.pageobjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sandysins.AbstractCompenonts.AbstractComponent;

public class landingPage extends AbstractComponent {
	WebDriver driver;
	
	public landingPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory design pattern to reducing the syntax for creating an web element
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	
//	WebElement password = driver.findElement(By.id("userPassword"));
	@FindBy(id="userPassword")
	WebElement password;
	
//	WebElement loginButton = driver.findElement(By.id("login"));
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productCatalogue loginApplication(String email, String pass) {
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		loginButton.click();
		productCatalogue ProductCatalogue = new productCatalogue(driver);
		return ProductCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
