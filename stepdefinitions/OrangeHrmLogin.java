package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.lang.System.*;
public class OrangeHrmLogin {
    WebDriver driver;


    @Given("I will Launch the browser.")
    public void iWillLaunchTheBrowser() {

            // Launch the Chrome Browser.
            WebDriverManager.chromedriver().setup();
            driver= new ChromeDriver();

            // Maximizing the window.
            driver.manage().window().maximize();

        out.println("The Chrome browser has been launched successfully.");

        }

    @When("I enter valid login credential's.")
    public void iEnterValidLoginCredentialS() {

        // Applying the implicit wait to all the webElements.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigating to the website.
        driver.get("opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Locating the username and passing the username.
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");

        // Locating the password element and passing the values.
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        // Locating the login button and click on it.
        WebElement login = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        login.submit();

        out.println("Entering to the validation step.");
    }
    @Then("I verify the login.")
    public void iVerifyTheLogin() {

        // Verify the login successfully.
         String actualText = driver.findElement(By.xpath("//h6[text()=\"Dashboard\"]")).getText();
        Assert.assertEquals("Dashboard",actualText);

        out.println("Verification has been successful.");

        driver.close();
    }
}
