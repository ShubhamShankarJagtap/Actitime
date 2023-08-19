package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class NewTasks extends BaseClass {
    @Then("I verify all tasks information in the response.")
    public void iVerifyAllTasksInformationInTheResponse() {
        // Verify the status code of the response.

        Assert.assertEquals(200,response.statusCode());
    }

    @Given("I set the structure")
    public void iSetTheStructure() {

        RestAssured.given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1/tasks")
                .queryParam("projectId",23)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Content-type","application/json")
                .header("accept","application/json")
                .when().get()
                .then().statusCode(200)
                .log()
                .all();
        response.prettyPrint();
    }
    @When("I setup the request structure to create task")
    public void iSetupTheRequestStructureToCreateTask(DataTable table) {
       Map<String,String> createTaskBody =  table.asMaps().get(0);
       Map<String,String> payload = new HashMap<>();
       payload.putAll(createTaskBody);
       payload.put("projectId",String.valueOf(projectId));

       requestSpecification=RestAssured.given();
       requestSpecification.baseUri("https://demo.actitime.com")
               .basePath("/api/v1")
               .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
               .header("Accept", "application/json")
               .header("Content-Type", "application/json")
               .body(payload)
               .log()
               .all();

    }

    @Then("I verify task is created successfully")
    public void iVerifyTaskIsCreatedSuccessfully(DataTable table) {
        Map<String,String> createTaskBody = table.asMaps().get(0);
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(createTaskBody.get("name"),response.jsonPath().getString("name"));
        Assert.assertEquals(createTaskBody.get("status"),response.jsonPath().getString("status"));
        Assert.assertEquals(Integer.parseInt(createTaskBody.get("typeOfWorkId")),response.jsonPath().getInt("typeOfWorkId"));
        Assert.assertEquals(customerId,response.jsonPath().getInt("customerId"));
        Assert.assertEquals(projectId,response.jsonPath().getInt("projectId"));
        Assert.assertEquals(Integer.parseInt(createTaskBody.get("estimatedTime")),response.jsonPath().getInt("estimatedTime"));

    }
}
