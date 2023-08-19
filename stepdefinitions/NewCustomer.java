package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.*;

public class NewCustomer extends BaseClass  {


    private int customerId;

    @Given("I will set up the structure")
    public void iWillSetUpTheStructure(Map<String,Object>queryParams) {
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification=RestAssured.given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("accept","application/json")
                .header("Content-type","application/json")
                .log()
                .all();
        //check if the query parameter is not null.... if it is not null then add it in the requestSpecification's.
        if(Objects.nonNull(queryParams)){
            requestSpecification.queryParams(queryParams);
        }
    }


    @When("I wil Hit an API")
    public void iWilHitAnAPI() {

        // Hit an api and get the response.
        response=requestSpecification.get("/customers");

        // Print the response in the pretty format.
        response.prettyPrint();

    }

    @Then("I verify all the customer's in the response")
    public void iVerifyAllTheCustomerSInTheResponse() {

        // Getting the actual response code from the response.
        int actualResponseCode = response.getStatusCode();

        //verify the actual response code with expected,i.e. 200.
        Assert.assertEquals(200,actualResponseCode);

        System.out.println("Verification of get all customer api is successful.");
    }

    @When("I Hit an api")
    public void iHitAnApi(DataTable table) {

        // Hit an api to get the response.
        Map<String,String> data = table.asMaps().get(0);
        if(data.get("method").equals("GET")){
            String pathParam="";
            if(data.get("pathParam")!=null){
                pathParam = data.get("pathParam");
            }
            response=requestSpecification.get("/" + data.get("endPoint") + "/" + pathParam);

            // Pretty Response

            response.prettyPrint();
        }
    }

    @Then("I verify customer in the response")
    public void iVerifyCustomerInTheResponse(String ExpectedCustomerName) {

        // Verify the statusCode.
        int actualStatusCode=response.getStatusCode();
        Assert.assertEquals(200,actualStatusCode);
      /*
        String firstCustomerName = response.jsonPath().getString("items[0].name");
        System.out.println(firstCustomerName);

        // Verify the firstCustomerName
           Assert.assertEquals("Big Bang Company",firstCustomerName);
       */
       // Taking the control of List "items".
        List<Map<String,Object>> itemsList = response.jsonPath().getList("items");

        // Printing the List & List Size
        System.out.println(itemsList);
        System.out.println(itemsList.size());


        // Taking the control of map inside the list "items."

        boolean actualResult=false;
        boolean actualArchived=false;

        for(Map<String,Object> customerDetails : itemsList){

            // Get the customer name from the response.
            String customerName= customerDetails.get("name").toString();


            // Verify the expected customer name from the response.
            if (customerName.equals(ExpectedCustomerName)){

                //get archived value for Joda Consultancy Inc customer.
                actualArchived=(boolean)customerDetails.get("archived");
                System.out.println("successfully able to verify the customer name.");

                actualResult=true;
                break;
            }
        }
        /*
        Assert.assertTrue(actualResult);
        Assert.assertTrue(actualArchived);
         */
    }

    @When("I wil be hitting this API")
    public void iWilBeHittingThisAPI(DataTable table) {

        //hit an api and get the response
        Map<String, String> data = table.asMaps().get(0);
        if (data.get("method").equals("GET")) {
            String pathParam = "";
            if (data.get("pathParam") != null) {
                pathParam = data.get("pathParam");
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");
            }
//            else if (Objects.nonNull(customerId)) {
//                pathParam = String.valueOf(customerId);
//                requestSpecification.pathParam("projectId", pathParam);
//                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");
//
  //          }
            else {
                response = requestSpecification.get("/" + data.get("endPoint"));

            }
        } else if (data.get("method").equals("POST")) {
            response = requestSpecification.post("/" + data.get("endPoint"));

        }
        response.prettyPrint(); // print the response in pretty format

    }

    @Then("I will verify customer response")
    public void iWillVerifyCustomerResponse(int id) {

        // Verify the status code
        Assert.assertEquals(200, response.statusCode());

        // verify the response of get customer with an id 6.
        List<Map<String, Object>> itemsList = response.jsonPath().getList("items");

        //Iterating through the itemsList.
        for (Map<String, Object> custDet : itemsList) {

            // verifying the customer id 6
            Assert.assertEquals(id, (int) custDet.get("id"));
        }
    }

    @Then("I verify customer response is getting sorted in {string} new order")
    public void iVerifyCustomerResponseIsGettingSortedInNewOrder(String order) {
       /* List<String> names= response.jsonPath().getList("items.name");
        System.out.println("unsorted list: "+ names);
        // create an object of list and add previous list into it
        List<String> actualSortedList = new ArrayList<>();
        actualSortedList.addAll(names);

        if(order.equals("desc")) {
            //sort the list in desc order
            Collections.reverse(names);
            System.out.println("Desc Order " + names);
        }
        else if (order.equals("asc")) {
            //sort the list in asc order
            Collections.sort(names);
            System.out.println("Asc Order: "+ names);
        }
        Assert.assertTrue( names.contains(actualSortedList));

        */
    }

    @Given("I set up the request structure to create the customer.")
    public void iSetUpTheRequestStructureToCreateTheCustomer(DataTable table) {

        //Get the name of the customer from the feature file.

        String customerName =table.asMaps().get(0).get("name");

        String requestBody = "{\n" +
                "    \"name\": \"" + customerName + "\",\n" +
                "    \"archived\": false\n" +
                "}";

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification=RestAssured.given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1/")
                .header("Content-type","application/json")
                .header("accept","application/json")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .body(requestBody)
                .log()
                .all();

    }

    @Then("I verify customer is getting created successfully with name {string}.")
    public void iVerifyCustomerIsGettingCreatedSuccessfullyWithName(String expectedName) {

        // StatusCode Verification.
        Assert.assertEquals(200,response.statusCode());

        customerId = response.jsonPath().getInt("id");
        String actualName = response.jsonPath().getString("name");

        // verify the customer name from the response
        Assert.assertEquals(expectedName,actualName);

        // verify archived field value , it should be false
        Assert.assertFalse(response.jsonPath().getBoolean("archived"));

        // verify description should be null
        //Assert.assertTrue(response.jsonPath().get("description")==null);

        Assert.assertTrue(Objects.isNull(response.jsonPath().getList("description")));
    }

    @When("I set up the request structure to get the customer information")
    public void iSetUpTheRequestStructureToGetTheCustomerInformation() {
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification=RestAssured.given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Content-type","application/json")
                .header("accept","application/json")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all();

        customerId = response.jsonPath().getInt("id");
    }
}

