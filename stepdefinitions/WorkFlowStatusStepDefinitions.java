package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import pojo.CreateWorkFlowResponsePojo;
import pojo.WorkFlowStatusPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorkFlowStatusStepDefinitions extends BaseClass {

    /*
        // Initialising the object of WorkFlowStatusPojo class
        statusPojo = new WorkFlowStatusPojo();

        // using the object of WorkFlowStatusPojo class setting the variable value's of private variable name of WorkFlowStatusPojo class.
    //    statusPojo.setName(new Faker().name().firstName());

        statusPojo.setName(new Faker().name().firstName());
        // using the object of WorkFlowStatusPojo class setting the variable value's of private variable type of WorkFlowStatusPojo class.
        statusPojo.setType(datatable.get("type"));

        // setting the workFlowStatus request structure.
        RestAssured.baseURI="https://demo.actitime.com";
        RestAssured.given()
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Content-type","application/json")
                .header("accept","application/json")
                .body(statusPojo)
                .when().post("/workflowStatuses")
                .then().statusCode(201)
                .log()
                .all();

    }


    @Then("I verify workflow status is getting created.")
    public void iVerifyWorkflowStatusIsGettingCreated() {

        // To print the response in the pretty format.
        response.prettyPrint();

        // Deserialization : It is the mechanism of converting the response to object of the class, i.e. CreateWorkFlowResponsePojo.
        workFlowResponsePojo = response.as(CreateWorkFlowResponsePojo.class);

        //getting id from response
        System.out.println(workFlowResponsePojo.getId());

        // Verify the name from response
        Assert.assertEquals(statusPojo.getName(),workFlowResponsePojo.getName());

        //verify the type from response
        Assert.assertEquals(statusPojo.getType(),workFlowResponsePojo.getType());

        //verify allowedActions field details
        Assert.assertTrue(workFlowResponsePojo.getAllowedActions().get("canModify"));

    }

    @When("I get all workflow statuses")
    public void iGetAllWorkflowStatuses() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.given()
                   .baseUri("https://demo.actitime.com")
                   .basePath("/api/v1")
                   .header("Authorizatioln","Basic YWRtaW46bWFuYWdlcg==")
                   .header("Content-type","application/json")
                   .header("accept","application/json")
                   .when().get("/workflowStatuses")
                   .then().statusCode(200)
                   .log()
                   .all();

    }

    @Then("I verify newly created workflow status in the response.")
    public void iVerifyNewlyCreatedWorkflowStatusInTheResponse() {
        CreateWorkFlowResponsePojo[] obj = response.jsonPath().getObject("items", CreateWorkFlowResponsePojo[].class);

        // printing the length of the CreateWorkFlowResponsePojo[] object
        System.out.println("length of object is " + obj.length);

        boolean flag=false;

        // verify newly created workflow in the response
        for(CreateWorkFlowResponsePojo responsePojo:obj)

            // check if the get response id is equals with id of create workflow status response
            if(responsePojo.getId() == workFlowResponsePojo.getId()){
                flag = true;
            }
    }
    List<Long> ids = new ArrayList<>();

        for(CreateWorkFlowResponsePojo responsePojo : obj){
        // check if the get response id is equals with the id of create workflow status response
        ids.add(responsePojo.getId());

    WorkFlowStatusPojo statusPojo;
    CreateWorkFlowResponsePojo workFlowResponsePojo;
    @Given("I create a workflow status")
    public void createWorkFlow(Map<String,String> datatable) {

        statusPojo = new WorkFlowStatusPojo();

        //Setting the name to customer Pojo name variable.

        String name = new Faker().name().firstName();
        statusPojo.setName(name);

        //Setting the type value to type variable
        statusPojo.setType(datatable.get("type"));

        // creating the request structure & hitting an API to create workflowStatuses.
        requestSpecification = RestAssured.given();


        https://demo.actitime.com")
                   .basePath("/api/v1")
                   .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                   .header("Content-type","application/json")
                   .header("accept","application/json")
                   .when().get("/workflowStatuses")
                   .then().statusCode(200)
                   .log()
                   .all();


        response=given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Content-Type","application/json")
                .header("accept","application/json")
                .body(statusPojo)
                .log()
                .all()
                .when().post("/workflowStatuses");
        response.prettyPrint();

    }


    @Then("I verify the CanModify Value in the response")
    public void iVerifyTheCanModifyValueInTheResponse() {

        Map<String,Boolean> resp = response.jsonPath().getMap("allowedActions");

        Boolean canModify = resp.get("canModify");

        System.out.println(canModify);

        Assert.assertTrue(canModify);
    }

    @Then("I verify workflow status is getting created.")
    public void iVerifyWorkflowStatusIsGettingCreated() {

        // Verify response code of create workFlowStatus
        Assert.assertEquals(200,response.getStatusCode());

        //Deserialization : It is mechanism of converting byte Stream into class Object.
        workFlowResponsePojo = response.as(CreateWorkFlowResponsePojo.class);

        // Verify the name from customer pojo and workFlowStatusPojo
        Assert.assertEquals(statusPojo.getName(),workFlowResponsePojo.getName());

        //Verify the type
        Assert.assertEquals(statusPojo.getType(),workFlowResponsePojo.getType());

        Map<String,Boolean> allowedActions = workFlowResponsePojo.getAllowedActions();
        System.out.println(allowedActions);


    }
    */
    WorkFlowStatusPojo statusPojo;
    CreateWorkFlowResponsePojo workFlowResponsePojo;

    int id;
    @Given("I create a workflow status")
    public void createUser(Map<String,String> map){

        statusPojo = new WorkFlowStatusPojo();

        String name = new Faker().name().firstName();

        statusPojo.setName(name);

        statusPojo.setType(map.get("type"));

        requestSpecification=RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("api/v1/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .body(statusPojo)
                .log()
                .all()
                .when().post("workflowStatuses")
                .then().statusCode(200).extract().response();
        response.prettyPrint();

    }


    @Then("I verify the CanModify Value in the response")
    public void iVerifyTheCanModifyValueInTheResponse(Boolean canModify) {

        // verify canModify value in the response
        Map<String,Object> map = response.jsonPath().getMap("allowedActions");
        Assert.assertEquals(canModify,(Boolean)map.get("canModify"));
    }

    @Then("I verify workflow status is getting created.")
    public void iVerifyWorkflowStatusIsGettingCreated() {
        workFlowResponsePojo = response.as(CreateWorkFlowResponsePojo.class);

        // verify the name from the response
        Assert.assertEquals(workFlowResponsePojo.getName(),response.jsonPath().getString("name"));

        //verify the type from the response
        Assert.assertEquals(workFlowResponsePojo.getType(),response.jsonPath().getString("type"));
    }

    @When("I get all workflow statuses")
    public void iGetAllWorkflowStatuses() {

        requestSpecification=RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("api/v1/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all()
                .when().get("workflowStatuses")
                .then().statusCode(200).extract().response();
        response.prettyPrint();

    }

    @Then("I verify newly created workflow status in the response.")
    public void iVerifyNewlyCreatedWorkflowStatusInTheResponse() {

        // verify the newly created workFlowStatus by id
        CreateWorkFlowResponsePojo[] obj = response.jsonPath().getObject("items",CreateWorkFlowResponsePojo[].class);

        System.out.println(obj.length);

        boolean flag = false;

        // verify newly created workflow in the response
        for (CreateWorkFlowResponsePojo responsePojo : obj){
            if(responsePojo.getId()==workFlowResponsePojo.getId()){
                flag=true;
                if (flag==true){
                    System.out.println("successful verification of newly created.");
                }
                break;
            }


        }
        /*

        boolean flag=false;

        // verify newly created workflow in the response
        for(CreateWorkFlowResponsePojo responsePojo:obj)

            // check if the get response id is equals with id of create workflow status response
            if(responsePojo.getId() == workFlowResponsePojo.getId()){
                flag = true;
            }
    }
    List<Long> ids = new ArrayList<>();

        for(CreateWorkFlowResponsePojo responsePojo : obj){
        // check if the get response id is equals with the id of create workflow status response
        ids.add(responsePojo.getId());
         */
    }

    @Given("I set up the the request structure to update the workFlow Status")
    public void iSetUpTheTheRequestStructureToUpdateTheWorkFlowStatus(DataTable table) {

        // setting the value to pojo object from the datatable.
        Map<String,String> patch = table.asMaps().get(0);

        statusPojo = new WorkFlowStatusPojo();
        statusPojo.setName(patch.get("name"));
        statusPojo.setType(patch.get("type"));

        // creating the request structure to update the created workFlow of id 5.
        requestSpecification=RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("api/v1/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .pathParam("id",Integer.parseInt(patch.get("id")))
                .log()
                .all()
                .body(statusPojo)
                .when().patch("workflowStatuses" + "/" + "{id}")
                .then().statusCode(200).extract().response();
        response.prettyPrint();

    }
    @Then("I verify workflow status of id  is getting updated using get api.")
    public void iVerifyWorkflowStatusOfIdIsGettingUpdatedUsingGetApi(DataTable table) {

        Map<String,String> patch = table.asMaps().get(0);

        // verify status code.
        Assert.assertEquals(200,response.getStatusCode());

        // verify the id of updated workFlowStatuses from the response.
        Assert.assertEquals(Integer.parseInt(patch.get("id")),response.jsonPath().getInt("id"));

        // verify the name of updated workFlowStatuses from the response.
        Assert.assertEquals(patch.get("name"),response.jsonPath().getString("name"));

        System.out.println("The workFlowStatus of id 5th is successfully updated. ");
    }

    @Given("I wil set up the request structure to create the workFlowStatus")
    public void iWilSetUpTheRequestStructureToCreateTheWorkFlowStatus(DataTable table) {

        //get the value from the datatable and set the value's to the pojo class object.
        Map<String,String> post=table.asMaps().get(0);

        statusPojo = new WorkFlowStatusPojo();

        statusPojo.setName(post.get("name"));

        statusPojo.setType(post.get("type"));

        // set the request structure to crete the workFLowStatus.
        requestSpecification=RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("api/v1/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all()
                .body(statusPojo)
                .when().post("workflowStatuses")
                .then().extract().response();
        response.prettyPrint();

        id = response.jsonPath().getInt("id");

    }

    @When("I delete the newly created workFlowStatus")
    public void iDeleteTheNewlyCreatedWorkFlowStatus() {

        // set the request structure to crete the workFLowStatus.
        requestSpecification=RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("api/v1/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all()
                .when().delete("workflowStatuses/" + id)
                .then().statusCode(204).extract().response();
        response.prettyPrint();

        System.out.println("successfully deleted.");
    }

    @Then("I verify the response with get all workflowStatus.")
    public void iVerifyTheResponseWithGetAllWorkflowStatus() {

        requestSpecification=RestAssured.given();

        response = given()
             //   .baseUri("https://demo.actitime.com")
              //  .basePath("api/v1/")
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON)
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all()
                .when().get("https://demo.actitime.com/api/v1/workflowStatuses"+"/" +id )
                .then().statusCode(404).extract().response();
        response.prettyPrint();

        System.out.println("successfully verified.");
    }
}


