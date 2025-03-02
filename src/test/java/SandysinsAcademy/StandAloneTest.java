package SandysinsAcademy;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import sandysins.pageobjects.landingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		landingPage LP = new landingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("sunny938650@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kratos@1234");
		driver.findElement(By.id("login")).click();
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter((product)->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match =cartProducts.stream().anyMatch(cp -> cp.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String ThanksPage = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		Assert.assertTrue(ThanksPage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
