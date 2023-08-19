package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class FileDownLoad extends BaseClass {

    FileOutputStream outputStream;

    byte[] input;
    @Given("I download the file")
        public void downloadFile(){

        requestSpecification = RestAssured.given();

        response=given()
                .baseUri("https://reqres.in")
                .basePath("/img/faces/")
                .header("accept",ContentType.ANY)
                .log()
                .all()
                .when().get("/7-image.jpg");
        response.prettyPrint();


    }


    @Then("I verify file is getting downloaded")
    public void iVerifyFileIsGettingDownloaded() throws IOException {

        // converting the response into the byte array.
        input = response.asByteArray();

        //saving the downloaded file in a location.

        outputStream = new FileOutputStream("D://Users//ADMIN//img.jpg");
        outputStream.write(input);
        outputStream.flush();
        outputStream.close();
    }
}

/*
 response = given()
                .baseUri("https://reqres.in")
                .basePath("/img/faces")
                .header("accept", ContentType.ANY)
                .log()
                .all()
                .when()
                .get("/7-image.jpg");

        response.prettyPrint();
 */

/*

          byte[] input = response.asByteArray();

        FileOutputStream fileOutputStream = new FileOutputStream("D://Users//ADMIN//img.jpg");
        fileOutputStream.write(input);
        fileOutputStream.flush();
        fileOutputStream.close();
 */