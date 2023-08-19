package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import pojo.ProjectPojo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectsStep extends BaseClass {

    String projectName;
    Map<String, Object> createBody;
    ProjectPojo projectPojo;
    Map<String, String> project;
    Map<String,String> projectRequestBody;

    @Given("I set the create new project request structure")
    public void createProject(DataTable table) {

        createBody = new HashMap<>();
        projectName = table.asMaps().get(0).get("name");
        createBody.put("name", projectName);
        createBody.put("customerId", 6);
        createBody.put("description", "this is sample description.");

        requestSpecification = given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", ContentType.JSON)
                .body(createBody.toString())
                .log()
                .all()
                .when().post("/project")
                .then().assertThat().statusCode(200).extract().response();

        response.prettyPrint();
    }

    @Then("I verify newly created project with name {string}")
    public void iVerifyNewlyCreatedProjectWithName(String expectedName) {

        //verify the customer name
        Assert.assertEquals(expectedName, response.jsonPath().getString("name"));

        //verify customer Id
        Assert.assertEquals(6, response.jsonPath().getInt("id"));
    }

    @Given("I set up the request structure to create project under Galaxy Corporation")
    public void iSetUpTheRequestStructureToCreateProjectUnderGalaxyCorporation(DataTable table) {

        // Creating the request body.
        project = table.asMaps().get(0);

        projectRequestBody= new LinkedHashMap<>();

        projectRequestBody.putAll(project);


    }

    @When("I will then hit this api")
    public void iWillThenHitThisApi() {

        requestSpecification = RestAssured.given();
        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", ContentType.JSON)
                .body(projectRequestBody.toString())
                .log()
                .all()
                .when().post("/project")
                .then().assertThat().statusCode(200).extract().response();

    }

    @Then("I will verify the response")
    public void iWillVerifyTheResponse() {

        // deserialization
        projectPojo = response.as(ProjectPojo.class);

        // verify the id
        Assert.assertEquals(projectPojo.getId(),response.jsonPath().getInt("id"));

        // verify the customerId
        Assert.assertEquals(projectPojo.getCustomerId(),response.jsonPath().getInt("customerId"));

        // verify the project name
        Assert.assertEquals(projectPojo.getName(),response.jsonPath().getString("name"));

        // verify the created field
        Assert.assertEquals(projectPojo.getCreated(),response.jsonPath().getLong("created"));

        // verify the allowedActions field can modify
        Assert.assertTrue(projectPojo.getAllowedActions().get("canModify"));

        // verify the allowedActions field can delete
        Assert.assertTrue(projectPojo.getAllowedActions().get("canDelete"));

        //verify the description field.
        Assert.assertEquals(projectPojo.getDescription(),response.jsonPath().getString("descriptions"));

        System.out.println("All the verifications are successful hence project is created under the customer Galaxy Corporation.");
    }
}