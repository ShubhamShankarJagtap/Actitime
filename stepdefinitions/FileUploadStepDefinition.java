package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;

import java.io.File;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class FileUploadStepDefinition extends BaseClass{
    File file;

   @Given("I prepare the request structure to upload the file")
    public void fileUpload(){

       // mention the filepath
        String filePath = "C://Users//Admin//OneDrive//Documents//CustomerStepDefinition.txt";

        // convert string file path into readable file format
       file = new File(filePath);

       //Creating the request structure

       requestSpecification= RestAssured.given();

       response=given()
               .baseUri("https://postman-echo.com")
               .header("content-Type",ContentType.MULTIPART.toString())
               .multiPart("file",file)
               .log()
               .all()
               .when().post("/post");
       response.prettyPrint();



    }

    @Then("I verify file is getting uploaded successfully")
    public void iVerifyFileIsGettingUploadedSuccessfully() {

       // verify the file name from the response
        Map<String,String> filesObject = response.jsonPath().getMap("files");

        //filename is the key,so we have to get the key from the above map.
        Set<String> keys = filesObject.keySet();

        //we get the name in the object format so convert it into object
        Object filename = keys.toArray()[0];

        //Convert into the string
        String fileName = String.valueOf(filename);

        // printing the actual filename using File class non-static method getName().
        System.out.println(file.getName());

        //verify the filename
        Assert.assertEquals(file.getName(),fileName);

   }
}



/*

        String filePath = "C://Users//Admin//OneDrive//Documents//CustomerStepDefinition.txt";

        file = new File(filePath);

        response=given()
                .baseUri("https://postman-echo.com")
                .header("Content-type", ContentType.MULTIPART.toString())
                .multiPart("file",file)
                .log()
                .all()
                .when().post("/post");
        response.prettyPrint();

 */

/*
 // verify status code
        Assert.assertEquals(200,response.getStatusCode());

        // get files field data
        Map<String,String> filesObject = response.jsonPath().getMap("files");

        // get all the keys from the files object
        Set<String> keys = filesObject.keySet();

        Object filename = keys.toArray()[0];

        String actualFileName = String.valueOf(filename);

        System.out.println(file.getName());

        Assert.assertEquals(file.getName(),actualFileName);

 */

/*
 requestspecification = RestAssured.given();

 response= given()
          .baseUri("--")
          .basePath("--")
          .header("Content-Type",Content-Type.MULTIPART.toString())
          .multipart("file",file)
          .when().post("/post");
  response.prettyPrint();

Map<String,String> filesObject = response.jasonPath.getMap("files");

// get the keys
Set<String> keys = filesObject.keySet();

//get the file name which is in Object

Object Filename = keys.toArray()[0];

String fileName = String.valueOf(Filename);
 */
/*

String filePath = "--";
File file = new File(filePath);
 */