package stepdefinitions;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;
public class Demo_Get_Request {

    @Test
    public void getWhetherDetails(){
        given()
                .when()
                .get("https://demo.actitime.com/api/v1/customers")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Content-Type","application/json");

    }

}
