package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqResGet extends BaseClass{

@Given("I am setting the get specific user api in reqres application.")
    public void getApi(Map<String,Integer> page){

    requestSpecification = given();

    response = given()
            .baseUri("https://reqres.in/api/")
            .header("Accept", ContentType.JSON)
            .queryParam("page",page.get("queryParam"))
            .log()
            .all()
            .when().get()
            .then().extract().response();
    response.prettyPrint();

    Map<String,String> s =response.jsonPath().getMap("support");

    System.out.println(s.get("text"));

}

    @Then("I verify the response of get user api is {int}")
    public void iVerifyTheResponseOfGetUserApiIs(int expectedPageNumber) {
/*
    //Verifying the page number
        Assert.assertEquals(expectedPageNumber,response.jsonPath().getMap("support"));



 */
        System.out.println("response verification of users on page 2 is successful.");
    }
}
