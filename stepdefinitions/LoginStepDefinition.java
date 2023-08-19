package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class LoginStepDefinition {
  WebDriver driver;

 @Given("I login to application with valid credentials")
 public void Login() {

  WebDriverManager.chromedriver().setup();
  driver = new ChromeDriver();
  driver.manage().window().maximize();
  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

  Wait<WebDriver> wait = new FluentWait<>(driver)
          .withTimeout(Duration.ofSeconds(10))
          .pollingEvery(Duration.ofSeconds(1))
          .ignoring(NoSuchElementException.class);

  WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
  username.sendKeys("Admin");

  WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
  password.sendKeys("admin123");

 WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));
 login.click();

  System.out.println("Login is successful.");
 }
/*
 @Then("I verify user is logged in to application successfully")
 public void iVerifyUserIsLoggedInToApplicationSuccessfully() {
  System.out.println("This method verifies login.");
  String actualTitle =driver.getTitle();
  String expectedTitle = "OrangeHRM";
  Assert.assertEquals(expectedTitle,actualTitle);
  System.out.println("Login Verification successful.");
  driver.close();
 }

 */
 @Given("I launch the {string} browser")
 public void iLaunchTheBrowser(String browser) {

  System.out.println("This method launches the browser.");

  if(browser.equals("chrome"))
  {
   WebDriverManager.chromedriver().setup();
   driver= new ChromeDriver();
  }
  else
  {
   WebDriverManager.edgedriver().setup();
   driver= new EdgeDriver();
  }

  driver.manage().window().maximize();
  System.out.println("Browser is : " + browser);
  System.out.println("Browser has been launched successfully.");
 }

 @When("I enter username {string} and password {string} and perform login")
 public void iEnterUsernameAndPasswordAndPerformLogin(String username, String password) {

  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

  Wait<WebDriver> wait = new FluentWait<>(driver)
          .withTimeout(Duration.ofSeconds(10))
          .pollingEvery(Duration.ofSeconds(1))
          .ignoring(NoSuchElementException.class, TimeoutException.class);

  if (username != null && !username.isEmpty()) {

   WebElement username1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
   username1.sendKeys(username);

  }

  if (password != null && !password.isEmpty()) {

   WebElement password1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
   password1.sendKeys(password);

  }

   WebElement login1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));
   login1.click();

  System.out.println("username is : " + username);
  System.out.println("password is : " + password);
  System.out.println("Login Performed Successfully.");
 }
 @Then("I verify user is logged in to application successfully")
 public void iVerifyUserIsLoggedInToApplicationSuccessfully() {
  System.out.println("This method verifies login.");
  String actualTitle =driver.getTitle();
  String expectedTitle = "OrangeHRM";
  Assert.assertEquals(expectedTitle,actualTitle);
  System.out.println("Login Verification successful.");
  driver.close();
 }

 @When("I enter below credentials")
 public void iEnterBelowCredentials(Map<String,String> cred) {

  Wait<WebDriver> wait = new FluentWait<>(driver)
          .withTimeout(Duration.ofSeconds(10))
          .pollingEvery(Duration.ofSeconds(1))
          .ignoring(NoSuchElementException.class, TimeoutException.class);


   WebElement username2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
   username2.sendKeys(cred.get("username"));

   WebElement password2 = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
   password2.sendKeys(cred.get("password"));

   WebElement login1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));
   login1.click();

   driver.quit();
 }
}

