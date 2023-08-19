package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;


public class CustomerStepDefinition extends BaseClass {

private int customerId;


    @Given("I set up the request structure")
    public void setup(Map<String, Object> queryParams) {
        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification = given();  // initialising the instance of RequestSpecification interface using implemented class RestAssured using static method given.
        // uri https://demo.actitime.com
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("accept", "application/json")
                .header("Authorization", "Basic YWRtaW46bWFuYWdlcg==")
                .log()
                .all();
        //check if query param is not null... if it is not null then add query params in the requestSpecification
        if (Objects.nonNull(queryParams)) {
            requestSpecification.queryParams(queryParams);
        }
    }

    @When("I hit an api")
    public void HitAnApi(DataTable table) {
        // Hit an api and get the response.
        //   response=requestSpecification.get("/customers");
        //hit an api and get the response
        Map<String, String> data = table.asMaps().get(0);
        requestSpecification = RestAssured.given();
        if (data.get("method").equals("GET")) {
            String pathParam = "";
            if (data.get("pathParam") != null) {
                pathParam = data.get("pathParam");
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");
            } else if (Objects.nonNull(customerId)) {
                pathParam = String.valueOf(customerId);
                requestSpecification.pathParam("projectId", pathParam);
                response = requestSpecification.get("/" + data.get("endPoint") + "/" + "{projectId}");
            } else {
                response = requestSpecification.get("/" + data.get("endPoint"));
            }
        } else if (data.get("method").equals("POST")) {
            response = requestSpecification.post("/" + data.get("endPoint"));
        }
        response.prettyPrint(); // print the response in pretty format


    }

    @Then("I verify all customer details in the response")
    public void iVerifyAllCustomerDetailsInTheResponse(String expectedCustomerName) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(200, actualStatusCode);
   /*     String firstCustomerName= response.jsonPath().getString("items[0].name");
        System.out.println(firstCustomerName);
        Assert.assertEquals("Big Bang Company", firstCustomerName);
       */
        List<Map<String, Object>> itemsList = response.jsonPath().getList("items");
        System.out.println(itemsList.size()); //print the size of the item list
        System.out.println(itemsList);
        boolean actualResult = false;
        boolean actualArchived = false;
        for (Map<String, Object> customerDetails : itemsList) {
            // get the customer name from the response
            String customerName = customerDetails.get("name").toString();
            //check if the expected customer name is present in the response
            if (customerName.equals(expectedCustomerName)) {
                actualArchived = (boolean) customerDetails.get("Archived");
                System.out.println("Successfully able to verify the customer name in the response");
                actualResult = true;
                break;
            }
        }
    }

    @Then("I verify customer response is getting sorted in {string} order")
    public void iVerifyCustomerResponseIsGettingSortedInOrder(String order) {

        // write a logic to verify the response is getting sorted in provided order

        Assert.assertEquals(200,response.getStatusCode());

        // printing all the descriptions of the customers
        System.out.println(response.jsonPath().getList("items.description"));

        // printing all allowedAction field values
        List<Map<String,Boolean>> allowedActions = response.jsonPath().getList("items.allowedActions");
        System.out.println(allowedActions);

        // get all names from the response

        List<String>names=response.jsonPath().getList("items.names");
        System.out.println("unsorted List: " + names);

        // creating an object of list and add previous list to it.

        List<String> actualSortedList = new ArrayList<>();
        actualSortedList.addAll(names);

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };
        Comparator<String> comp = (s1, s2) -> {
            return s1.compareToIgnoreCase(s2);
        };
        Comparator<String> comp1 = String::compareToIgnoreCase;

        if (order.equals("desc")){
            // sort the list in descending order
           // Collections.reverse(names);
            Collections.sort(names, Collections.reverseOrder(comparator));
            System.out.println("Desc Order " + names);
        }
        else if (order.equals("asc")){
            // sort the list in ascending order
            Collections.sort(names, comparator);
            names.sort(comparator);
            System.out.println("Asc Order: " + names);
        }
          Assert.assertTrue(names.containsAll(actualSortedList));
    }

    @Given("I set up the request structure to create customer")
    public void iSetUpTheRequestStructureToCreateCustomer(DataTable table) {

        // Get the name of customer from the feature file using datatable.

        String customerName = table.asMaps().get(0).get("name");
        String archived = table.asMaps().get(0).get("Archived");

        String requestBody;

        if(Objects.isNull(customerName)){
            requestBody = "{\n" +
                      "\"name\": null,\n" +
                      "\"archived\": false\n" +
                    "}";
        }
        else if (customerName.equals("emptyString")){
            requestBody= "{\n" +
                    "    \"name\": \"\",\n" +
                    "    \"archived\": false\n" +
                    "}";
        }
       else {
            requestBody= "{\n" +
                    "    \"name\": \""+customerName+"\",\n" +
                    "    \"archived\": false\n" +
                    "}";
        }

       RestAssured.useRelaxedHTTPSValidation();
       requestSpecification= given();

       requestSpecification.baseUri("https://demo.actitime.com")
               .basePath("/api/v1")
               .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
               .header("Accept","application/json")
               .header("Content-type","application/json")
               .body(requestBody)
               .log()
               .all();
    }

    @Then("I verify customer is getting create successfully with name {string}")
    public void iVerifyCustomerIsGettingCreateSuccessfullyWithName(String expectedName) {

        // verifying the status code  200.
        Assert.assertEquals(200,response.getStatusCode());

      // getting the customer's id from the response.
        customerId = response.jsonPath().getInt("id");

        // getting actual name from the response.
        String actualName=response.jsonPath().getString("name");

        // verifying the customer name from the response.
        Assert.assertEquals(expectedName,actualName);

        // verifying the archived field value and it should be false.
        Assert.assertFalse(response.jsonPath().getBoolean("archived"));

        // verifying the description should be null.
         Assert.assertTrue(Objects.isNull(response.jsonPath().get("description")));

    }

    @When("I set up the request structure get customer information")
    public void iSetUpTheRequestStructureGetCustomerInformation() {

        RestAssured.useRelaxedHTTPSValidation();
        requestSpecification= given();
        requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept","application/json")
                .header("Content-type","application/json")
                .log()
                .all();
    }

    @Then("I verify the status code as {int} and error message")
    public void iVerifyTheStatusCodeAsAndErrorMessage(int statusCode, Map<String,String> data ) {

        // verify status code

        Assert.assertEquals(statusCode,response.getStatusCode());

        // verify an error Message
        Assert.assertEquals(data.get("message"),response.jsonPath().getString("message"));

    }

    @Given("I set up the request structure to create customer payload")
    public void iSetUpTheRequestStructureToCreateCustomerPayload(DataTable table) {
        Map<String,String> payload = table.asMaps().get(0);

        response = given()
                .baseUri("https://demo.actitime.com")
                        .basePath("/api/v1/customers")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept","application/json")
                .header("Content-type","application/json")
                .body(payload)
                .log()
                .all()
                        .when().post()
                        .then().extract().response();

        response.prettyPrint();
    }

    @Then("I verify the status code as {int} and archived value in response")
    public void iVerifyTheStatusCodeAsAndArchivedValueInResponse(int statusCode , boolean expectedArchive) {

        // verify statusCode
        Assert.assertEquals(statusCode,response.getStatusCode());

        // verify archive value in the response.
        Assert.assertEquals(expectedArchive,response.jsonPath().getBoolean("archived"));



    }
/*
    @Then("I verify customer is getting created successfully")
    public void iVerifyCustomerIsGettingCreatedSuccessfully(String expectedCustName) {

        int id = response.getStatusCode();
        System.out.println(id);




        // Verify customer is getting created successfully.
        Assert.assertEquals(expectedCustName,actualCustName);

    }

 */

    @Then("I delete the customer")
    public void iDeleteTheCustomer() {

        // I will hit an delete api to delete the customer.

        requestSpecification = RestAssured.given();
             requestSpecification.baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept","applicaton/json")
                .header("Content-type","application/json")

                .log()
                .all()
                .when().delete("/customers/" + customerId )
                .then().statusCode(204);
    }

    @When("I verify customer is getting created successfully with name {string}")
    public void iVerifyCustomerIsGettingCreatedSuccessfullyWithName(String expectedCustomerName) {

        //verify status code
        Assert.assertEquals(200,response.getStatusCode());

        customerId=response.jsonPath().getInt("id");

        String actualCustomerName = response.jsonPath().getString("name");


        // verify customer is getting created with the expected name
        Assert.assertEquals(expectedCustomerName,actualCustomerName);

    }

    @And("I verify customer is getting deleted from the system by hitting get api.")
    public void iVerifyCustomerIsGettingDeletedFromTheSystemByHittingGetApi() {
        requestSpecification = RestAssured.given();

        response = given()
                .baseUri("https://demo.actitime.com")
                .basePath("/api/v1")
                .header("Authorization","Basic YWRtaW46bWFuYWdlcg==")
                .header("Accept","application/json")
                .header("Content-type","application/json")
                .log()
                .all()
                .when().get("/customers/" + customerId)
                .then().extract().response();

        //verify status code
        Assert.assertEquals(404,response.getStatusCode());
    }
}
