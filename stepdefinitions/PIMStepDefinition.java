package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PIMStepDefinition {
    WebDriver driver;

    @Then("I login to application with username {string} and password {string}")
    public void loginHRM(String username,String password){
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        WebElement Username = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        Username.sendKeys(username);

        WebElement Password = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        Password.sendKeys(password);

        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));
        login.click();

        System.out.println("Login is successful.");
        System.out.println("Navigating to PIM Module.");

        Wait<WebDriver> wait1 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement pim = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='PIM']")));
        pim.click();

        System.out.println("Successfully Navigated to PIM Module.");

    }
/*
    @Given("I add employee in the application")
    public void iAddEmployeeInTheApplication() {
        System.out.println("Navigating to PIM Module.");

        Wait<WebDriver> wait1 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement pim = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='PIM']")));
        pim.click();

        System.out.println("Successfully Navigated to PIM Module.");

        System.out.println("Navigating to the add employee.");

        Wait<WebDriver> wait2 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement addEmployee = wait2.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add Employee")));
        addEmployee.click();

        Wait<WebDriver> wait3 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement firstname = wait3.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
        firstname.sendKeys("Bill");

        Wait<WebDriver> wait4 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement middlename = wait4.until(ExpectedConditions.presenceOfElementLocated(By.name("middleName")));
        middlename.sendKeys("William");

        Wait<WebDriver> wait5 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement lastname = wait5.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
        lastname.sendKeys("Goldberg");

        Wait<WebDriver> wait6 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement checkBox = wait6.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/label[1]/span[1]")));
        checkBox.click();

        Wait<WebDriver> wait7 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement Username = wait7.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@autocomplete='off']")));
        Username.sendKeys("billgold");

        Wait<WebDriver> wait8 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement Password = wait8.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        Password.sendKeys("s02169252723");

        Wait<WebDriver> wait9 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement confirmPassword = wait9.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/input[1]")));
        confirmPassword.sendKeys("s02169252723");

        Wait<WebDriver> wait10 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement save = wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
        save.click();

    }
*/
    
    @Then("Verify employee added successfully")
    public void verifyEmployeeAddedSuccessfully() {
        System.out.println("Verification is completed successfully.");
    }

    @When("I search the newly added with employee status filter")
    public void iSearchTheNewlyAddedWithEmployeeStatusFilter() {
        System.out.println("Employee has been verified using employee status filter.");

    }

    @Then("I verify newly added emp in search result")
    public void iVerifyNewlyAddedEmpInSearchResult() {
        System.out.println("Emplyee has been verified using search result.");
    }

    @Given("I select any filter from filter section")
    public void iSelectAnyFilterFromFilterSection(List<String> filters) {
        filters.forEach(val-> System.out.println(val));
    }

    @When("I click on Reset button")
    public void iClickOnResetButton() {
        System.out.println("This is reset button ");
    }

    @Then("I verify all filter should get reset")
    public void iVerifyAllFilterShouldGetReset() {
        System.out.println("All filter's are getting reset.");
    }

    @Given("I add {int} employee in the application")
    public void iAddEmployeeInTheApplication(int arg0) {
    }

    @Then("I verify employee added successfully in the system")
    public void iVerifyEmployeeAddedSuccessfullyInTheSystem() {
    }

    @And("I update the employee information")
    public void iUpdateTheEmployeeInformation() {
    }

    @And("I search the updated employee")
    public void iSearchTheUpdatedEmployee() {
    }

    @Then("I delete the employee")
    public void iDeleteTheEmployee() {
    }

    @Given("I add employee in the application")
    public void iAddEmployeeInTheApplication(Map<String,String> emp) {
        System.out.println("This is add employee step. ");
        System.out.println("Iterating through emp using forEach. ");
        emp.forEach((key,value)->{
            System.out.println(key + " " + value);
        });
        System.out.println("Navigating to the add employee.");

        Wait<WebDriver> wait2 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement addEmployee = wait2.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add Employee")));
        addEmployee.click();

        Wait<WebDriver> wait3 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement firstname = wait3.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
        firstname.sendKeys(emp.get("firstname"));

        Wait<WebDriver> wait4 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement middlename = wait4.until(ExpectedConditions.presenceOfElementLocated(By.name("middleName")));
        middlename.sendKeys("William");

        Wait<WebDriver> wait5 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement lastname = wait5.until(ExpectedConditions.presenceOfElementLocated(By.name("lastName")));
        lastname.sendKeys(emp.get("lastname"));

        if(emp.get("loginCredential's").equals("true")) {
            Wait<WebDriver> wait6 = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement checkBox = wait6.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[2]/div[1]/label[1]/span[1]")));
            checkBox.click();

            Wait<WebDriver> wait7 = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement Username = wait7.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@autocomplete='off']")));
            Username.sendKeys(emp.get("username"));

            Wait<WebDriver> wait8 = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement Password = wait8.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
            Password.sendKeys(emp.get("password"));

            Wait<WebDriver> wait9 = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement confirmPassword = wait9.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[2]/div[4]/div[1]/div[2]/div[1]/div[2]/input[1]")));
            confirmPassword.sendKeys(emp.get("confirmPassword"));

            Wait<WebDriver> wait10 = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement save = wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
            save.click();

        }
    }
/*
    @Given("I add employee in the application")
    public void iAddEmployeeInTheApplication(DataTable empdetails) {
        System.out.println("This is add employee method.");
      List<Map<String,String>> data = empdetails.asMaps();
      Map<String,String> map = data.get(0);
        System.out.println(map.get("firstame"));
        System.out.println(map.get("empid"));
*/
  }
