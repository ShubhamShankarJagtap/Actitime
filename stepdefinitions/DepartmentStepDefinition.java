package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DepartmentStepDefinition extends BaseClass {

    @Given("I set up the request structure to get all department")
    public void setup(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .log()
                .all();
    }

    @Then("I verify all details in get all department response")
    public void iVerifyAllDetailsInGetAllDepartmentResponse() {

        List<Map<String,Object>> itemsList = response.jsonPath().getList("items");

        List<Integer> ids = response.jsonPath().getList("items.id");

        Collections.sort(ids);

        System.out.println(ids);

        ids.forEach(id ->{
            itemsList.forEach(map->{
                if(map.get("id").equals(id)){
                    System.out.println("Id : " + id + "name : " + map.get("name"));
                }
            });
        });
    }
}
