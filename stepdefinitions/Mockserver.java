package stepdefinitions;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

public class Mockserver extends BaseClass {
    static WireMockServer wireMockServer;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("This is before all hook.....in MockServerStepDef.. ");

        /*
         * create instance of wiremock server class
         * define on which port this mock server should run
         * This instance will create baseUri as http://localhost:808
         */
        wireMockServer= new WireMockServer(8082);

        //configure wiremock server on 8082 port
        WireMock.configureFor(8082);

        //start wiremock server
        wireMockServer.start();




        String responseBody = "{\n" +
                "  \n" +
                "  \"countries\": [\n" +
                "\t{\n" +
                "\t  \"id\": 1,\n" +
                "\t  \"name\" : \"India\",\n" +
                "\t  \"population\": \"1.4B\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t  \"id\": 2,\n" +
                "\t  \"name\" : \"China\",\n" +
                "\t  \"population\": \"1.3B\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t  \"id\": 3,\n" +
                "\t  \"name\" : \"USA\",\n" +
                "\t  \"population\": \"33B\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t  \"id\": 4,\n" +
                "\t  \"name\" : \"Japan\",\n" +
                "\t  \"population\": \"70B\"\n" +
                "\t} \n" +
                "  ]\n" +
                "}";

        WireMock.stubFor(get("/countries")
                        .willReturn(aResponse()
                                .withStatus(200)
//                        .withHeader("Content-Type",ContentType.JSON.toString())
                                .withBody(responseBody))
        );

    }

    @AfterAll
    public static void tearDown(){
        wireMockServer.shutdown();
    }
    @Given("I hit an api to get countries from wire mock server")
    public void getCountries(){
        RestAssured.useRelaxedHTTPSValidation();
        response = given()
                .baseUri("http://localhost:8082")
                .log().all()
                .header("Accept", ContentType.JSON)
                .when().get("/countries");
        response.prettyPrint();
    }

    @Then("I verify the response for mock server")
    public void iVerifyTheResponseForMockServer() {
        System.out.println("Mock Server verification is successful.");
    }
}
/*

static MockServer mockserver;

@BeforeAll
public void beforeAll(){

mockserver = new MockServer();

mockServer.start();

String responsebody = "----";

WireMock.stubFor(get"/countries").willReturn(aResponse().withStatus(200).withBody(responseBody));
}
@AfterAll
public void teardown(){
wireMockServer.shutdown();
}

@Given("I hit api to get response")

.when.get("/countries")

WireMockServer wireMockServer;



@BeforeAll
public static void before(){
wireMockServer = new WireMockServer();

wireMockServer.start();

wireMockServer.stubFor(get(url).willContain(body))


 */