package stepdefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.Map;


public class RecruitmentStepDefinition {

        // DataTable is class which represent the data mentioned in the feature file
        WebDriver driver;
        @Before
        public void before(){
            System.out.println("This is before scenario hook");

            //WebDriver driver = new ChromeDriver();

        }
        @After
        public void after(){
            System.out.println("This is after scenario hook");
            //logic to close the browser
        }

//    @BeforeStep
//    public void beforeStep(){
//        System.out.println("This is before step");
//    }
//
//    @AfterStep
//    public void afterStep(){
//        System.out.println("This is after step");
//    }

        @Before("@Tag1")
        public void beforeHook(){
            System.out.println("This is before conditional(Tag) hook");
        }
        @After("@Tag1")
        public void afterTagHook(){
            System.out.println("This is after conditional(Tag) hook");
        }

        @BeforeAll
        public static void beforeAllHook(){
            System.out.println("This is before all hook");
        }

        @AfterAll
        public static void afterAllHook(){
            System.out.println("This is after all hook");
        }

        @Given("I add candidate in the system")
        public void addCandidate(DataTable table) {
            //logic to add candidate
            System.out.println("This is add candidate method");

            // get the data from table variable
            List<Map<String,String>> list = table.asMaps(); // this returns List<Map<String,String>>

            //print the size of list
            System.out.println(list.size());

            //iterate the content from list
            for( Map<String,String> map: list){

                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(20))
                        .pollingEvery(Duration.ofSeconds(2))
                        .ignoring(NoSuchElementException.class)
                        .ignoring(TimeoutException.class)
                        .withMessage("Unable to load element");

                WebElement recruitment_module = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Recruitment']")));
                recruitment_module.click();

                WebElement add_Candidate = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='orangehrm-header-container']/button")));
                add_Candidate.click();

                WebElement firstname = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
                firstname.sendKeys(map.get("firstname"));

                WebElement middlename = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
                middlename.sendKeys("Alex");

                WebElement lastname = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
                lastname.sendKeys(map.get("lastname"));

                WebElement Email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Type here']")));
                Email.sendKeys(map.get("emailId"));

                WebElement contactNumber= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[@id='app']/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[2]/div[1]/div[2]/input[1]")));
                contactNumber.sendKeys(map.get("contactNumber"));

                WebElement savebutton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='submit']")));
                savebutton.click();

                //print the data present in map
                for(Map.Entry<String,String> data:map.entrySet()){
                    System.out.println("Key: "+ data.getKey()+ " value: " + data.getValue());
                }
            }
        }

        @Then("I verify candidate added successfully by searching name of candidate in filter")
        public void verifyAddCandidate() {
            System.out.println("This is verify candidate method");
        }

        @When("I update the interview status of newly added candidate")
        public void iUpdateTheInterviewStatusOfNewlyAddedCandidate(String status) {
            System.out.println("this is update interview status method");

        }

        @Then("I verify the interview status getting updated successfully")
        public void iVerifyTheInterviewStatusGettingUpdatedSuccessfully() {
            System.out.println("This is verify interview status method");
        }
}
