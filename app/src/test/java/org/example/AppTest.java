package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.restassured.RestAssured;
import org.json.JSONObject;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class AppTest {

    // @Test 
    // public void appHasAGreeting() {
    //     App classUnderTest = new App();
    //     assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    // }

    @Test
    public void getRequest() {
        System.out.println("testCase 1 - GET request");


        // Set Base URI
        RestAssured.baseURI = "https://dummyjson.com/products";
        
        // Create RequestSpecification object
        RequestSpecification httpRequest = RestAssured.given();
        
        // Perform GET request
        Response response = httpRequest.get();
        
        // Extract status code
        int statusCode = response.getStatusCode();
        
        // Log the status code and response
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());

      //  Verify the status code
       assertEquals(statusCode, 200, "API call was unsuccessful");
    }
    @Test 
    public void getRequestWithParam() {
        System.out.println("testCase 2 - GET request with param");
        RestAssured.baseURI = "https://dummyjson.com/products";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.when().get("/1");
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
    
}
@Test 
public void PostRequest(){
    System.out.println("testCase 3 - POST request");
    RestAssured.baseURI = "https://dummyjson.com/products";
    RequestSpecification httpRequest = RestAssured.given();
    httpRequest.header("Content-Type", "application/json");
    JSONObject json=new JSONObject();
    json.put( "title","BMW Pencil");
    httpRequest.body(json.toString());
    Response response = httpRequest.when().post("/add");
    int statusCode = response.getStatusCode();
    System.out.println("Status Code: " + statusCode);
    System.out.println("Response Body: " + response.getBody().asString());
}

@Test 
public void PutRequest(){
    System.out.println("testCase 4- POST request");
    RestAssured.baseURI = "https://dummyjson.com/products";
    RequestSpecification httpRequest = RestAssured.given();
    httpRequest.header("Content-Type", "application/json");
    JSONObject json=new JSONObject();
    json.put( "title","iPhone Galaxy +1");
    httpRequest.body(json.toString());
    Response response = httpRequest.when().put("/1");
    int statusCode = response.getStatusCode();
    System.out.println("Status Code: " + statusCode);
    System.out.println("Response Body: " + response.getBody().asString());
}
@Test
public void DeleteRequest(){
    System.out.println("testCase 5 - POST request");
    RestAssured.baseURI = "https://dummyjson.com/products";
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.when().delete("/1");
    int statusCode = response.getStatusCode();
    System.out.println("Status Code: " + statusCode);
    System.out.println("Response Body: " + response.getBody().asString());
}
}
