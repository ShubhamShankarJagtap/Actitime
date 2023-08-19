package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import pojo.UserGoRestPojo;
import pojo.UserTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UsersStepDefinitions extends BaseClass {

    Response response;
    File file;
    byte[] payload;
    JSONObject createUserPayload;
    UserTypes userTypes;
    UserGoRestPojo goRestPojo;
    @Given("I setup the request structure to create user.")
    public void iSetupTheRequestStructureToCreateUser() {
        // Preparing the JSONObject which is request body for POST request.
        createUserPayload = new JSONObject();
        String firstName = new Faker().name().firstName();
        createUserPayload.put("email",firstName +"@gmail.com");
        createUserPayload.put("firstName",firstName);
        createUserPayload.put("lastName",new Faker().name().lastName());
        createUserPayload.put("username",firstName);
        createUserPayload.put("password","12345!@#$%");
    }
    @When("I hit an create user API.")
    public void iHitAnCreateUserAPI() {
        requestSpecification = RestAssured.given();

        response= given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .header("Content type","application/json")
                .log()
                .all()
                .body(createUserPayload.toString())
                .when().post("/users");

        response.prettyPrint();

    }

    @Then("I verify user is getting created successfully.")
    public void iVerifyUserIsGettingCreatedSuccessfully() {

        //verify response status code
        Assert.assertEquals(200,response.getStatusCode());

        //verify the firstname
        Assert.assertEquals(createUserPayload.get("name"),response.jsonPath().getString("name"));
    }

    @Given("I set up the request structure to create user by using the Json file.")
    public void iSetUpTheRequestStructureToCreateUserByUsingTheJsonFile() throws FileNotFoundException {

        //Access the JsonFile path
        String filePath = "src/test/Resources/CreateUserPayload.json";

        // Read the file through the fileReader.
        file = new File(filePath);
        FileReader fileReader = new FileReader(file);


        // Accessing the content from the Json File and converting it into byte array.


        try {
            payload = Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I hit an create user with json file api")
    public void iHitAnCreateUserWithJsonFileApi() {

        requestSpecification = RestAssured.given();
       response = given()
                 .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .header("Content type","application/json")
                .log()
                .all()
                .body(Arrays.toString(payload))
                .when().post("/users")
                .then().extract().response();
       response.prettyPrint();

        Assert.assertEquals(200,response.getStatusCode());
    }

    @Then("I verify user is getting created successfully with Json file.")
    public void iVerifyUserIsGettingCreatedSuccessfullyWithJsonFile() {
        System.out.println("USER IS CREATED USING JSON FILE SUCCESSFULLY.");
    }
/*
    @Given("I setup the request structure  to create user using class object")
    public void iSetupTheRequestStructureToCreateUserUsingClassObject() {

        // Setting the values to variables of UserTypes.
        String firstName = new Faker().name().firstName();
        userTypes = new UserTypes();
        userTypes.setEmail(firstName + "@gmail.com");
        userTypes.setFirstName(firstName);
        userTypes.setLastName(new Faker().name().lastName());
        userTypes.setUserName(firstName);
        userTypes.setPassword("S12345@R");
    }

    @When("I hit an create user API using class object.")
    public void iHitAnCreateUserAPIUsingClassObject() {
        requestSpecification = RestAssured.given();

        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1/users")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .header("Content type","application/json")
                .body(userTypes.toString())
                .log()
                .all()
                .when().post().prettyPrint();

    }

    @Then("I verify user is getting created using class object")
    public void iVerifyUserIsGettingCreatedUsingClassObject() {
        System.out.println("USER IS GETTING CREATED USING CLASS OBJECT.");
    }

 */

    @Given("I setup the request structure to create user in go rest")
    public void iSetupTheRequestStructureToCreateUserInGoRest(DataTable table) {

        Map<String,String> datatable = table.asMaps().get(0);

        String name = new Faker().name().fullName();

        goRestPojo = new UserGoRestPojo();

        goRestPojo.setName(name);

        goRestPojo.setEmail(name.replace("",",")+"gmail.com");

        goRestPojo.setGender(datatable.get("gender"));

        goRestPojo.setStatus(datatable.get("status"));

        requestSpecification = RestAssured.given();

        response= given()
                .baseUri("https://gorest.co.in")
                .basePath("/public/v2")
                .header("Authorization", "Bearer 526f4b67bf691be98e94abe1df982518410da2dd4e2250823f06bff490e19f12")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(goRestPojo)
                .log()
                .all()
                .when().post("/post");


        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(goRestPojo.getName(), response.jsonPath().getString("name"));
        Assert.assertEquals(goRestPojo.getEmail(), response.jsonPath().getString("email"));
        Assert.assertEquals(goRestPojo.getStatus(), response.jsonPath().getString("status"));
        Assert.assertEquals(goRestPojo.getGender(), response.jsonPath().getString("gender"));
        userId= response.jsonPath().getInt("id");
    }

    @Then("I verify user api response with status code {int}")
    public void iVerifyUserApiResponseWithStatusCode(int expectedStatusCode) {
        System.out.println("verification successful" + expectedStatusCode);
    }

    @When("I update the newly created user")
    public void iUpdateTheNewlyCreatedUser(DataTable table) {

        Map<String,String> dataTable = table.asMaps().get(0);
        goRestPojo.setStatus(dataTable.get("status"));
        goRestPojo.setGender(dataTable.get("gender"));
        if (dataTable.get("name").equals("random")) {
            goRestPojo.setName(new Faker().name().fullName());
        } else if (dataTable.get("email").equals("random")) {
            goRestPojo.setEmail(new Faker().name().firstName()+ "@gmail.com");
        }
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://gorest.co.in")
                .basePath("/public/v2")
                .header("Authorization", "Bearer 526f4b67bf691be98e94abe1df982518410da2dd4e2250823f06bff490e19f12")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .body(goRestPojo)
                .log()
                .all();

        if (dataTable.get("pathParam").equals("userId")){
            requestSpecification.pathParam("goRestUserId",userId);
        }
    }

    @Given("I setup the request structure  to create user using class object")
    public void iSetupTheRequestStructureToCreateUserUsingClassObject() {
        userTypes = new UserTypes();

       String firstName = new Faker().name().firstName();

       userTypes.setUserName(firstName);
        userTypes.setLastName(new Faker().name().lastName());
        userTypes.setEmail(firstName + "@gmail.com");
        userTypes.setUserName(firstName);
        userTypes.setPassword("232@s4ft");
    }

    @When("I hit an create user API using class object.")
    public void iHitAnCreateUserAPIUsingClassObject() {
        requestSpecification = RestAssured.given();

        response=given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept",ContentType.JSON)
                .header("Content-Type", ContentType.JSON)
                .body(userTypes)
                .log()
                .all()
                .when().post("/users")
                .then().extract().response();

        response.prettyPrint();
    }

    @Then("I verify user is getting created using class object")
    public void iVerifyUserIsGettingCreatedUsingClassObject() {

        //Verify all user's fields
        Assert.assertEquals(userTypes.getLastName(),response.jsonPath().getString("lastName"));
    }
}

/*

Create user body using json object

JsonObject createUser;

createUser = new JsonObject();

createUser.put()

Create user using json file

String file = "";

File file = new File(file);

FileReader reader = new FileReader(file);

JsonParser parser = new JasonParser();



 */
