package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProjectStepDefinition extends BaseClass {


    @Given("I will set up the request structure")
    public void iWillSetUpTheRequestStructure( ) {
        requestSpecification = RestAssured.given();
        response= requestSpecification.given()
                 .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .log()
                .all()
                .when().get("/projects");

        response.prettyPrint();
    }


    @Then("I verify the status code as {int}")
    public void verifyStatusCode(int expectedStatusCode){

        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);

    }

    @And("I verify the project information in the response")
    public void iVerifyTheProjectInformationInTheResponse(DataTable table) {

        // Datatable representation
        Map<String,String> data = table.asMaps().get(0);

        //getting response of first object of get project api.
        Map<String,Object> itemsList = response.jsonPath().getMap("items[0]");

        System.out.println(itemsList);

        System.out.println((itemsList.size()));
        // Verify customerId is 6
        Integer actualId = Integer.parseInt(itemsList.get("customerId").toString());
        Integer expectedId =Integer.parseInt(data.get("customerId"));
        Assert.assertEquals(expectedId,actualId);


        // verify project name

        String expectedName = data.get("name");
        String actualName = itemsList.get("name").toString();
        Assert.assertEquals(expectedName,actualName);

        //verify archived

        boolean expectedArchived = Boolean.parseBoolean(data.get("archived"));
        boolean actualArchived = Boolean.parseBoolean(itemsList.get("archived").toString());
    }

}

