package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TypeOfWork extends BaseClass{
    @Given("I set the request structure to get all typeOfWork")
    public void getAll(){
        requestSpecification= RestAssured.given();

        response=given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .header("Content-type","application/json")
                .log()
                .all()
                .when().get("/typesOfWork")
                .then().extract().response();
        response.prettyPrint();
    }

    @Then("I verify getAll typeOfWork api in the response with {int} statusCode.")
    public void iVerifyGetAllTypeOfWorkApiInTheResponseWithStatusCode(int expectedStatusCode) {

        //verify the statusCode
        Assert.assertEquals(expectedStatusCode,response.getStatusCode());
/*
        List<Map<String,Object>> itemsList = response.jsonPath().getList("items");

        System.out.println(itemsList.size());
 */
        int expectedId = 1;

        String expectedName = "engineering";

        // verify the id of Object at 0th index .
        Assert.assertEquals(expectedId,response.jsonPath().getInt("id"));

        // verify the name of Object at 0th index
        Assert.assertEquals(expectedName,response.jsonPath().getString("name"));

        int id=response.jsonPath().getInt("id");

        String name=response.jsonPath().getString("name");

        System.out.println("id of 0th index object is : " + id + "name of 0th index object is : " + name);

        System.out.println("successful response verification.");
    }

    @Given("I set get default typeOfWork request structure")
    public void iSetGetDefaultTypeOfWorkRequestStructure() {

        requestSpecification = RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("accept", "application/json")
                .header("Content-type", "application/json")
                .log()
                .all()
                .when().get("/typesOfWork/default")
                .then().extract().response();
        response.prettyPrint();

    }

    @Given("I set up request structure for get by id {int} typeOfWork api")
    public void iSetUpRequestStructureForGetByIdTypeOfWorkApi(int expectedID) {

        requestSpecification= RestAssured.given();

        response=given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .header("Content-type","application/json")
                .log()
                .all()
                .pathParam("id",expectedID)
                .when().get("/typesOfWork/" + "{id}")
                .then().extract().response();
        response.prettyPrint();
    }
}
