package stepdefinitions;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

    static public Response response;

    static public RequestSpecification requestSpecification;
    static public int customerId;
    static public int projectId;
    static public int userId;

}
