package org.example;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.*;

import static org.testng.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class AppTest {

    @Test(priority=1)
    public void getRequest() {
        System.out.println("testCase 1 - GET request");
        RestAssured.baseURI = "https://dummyjson.com/products";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get();
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
       assertEquals(statusCode, 200, "API call was unsuccessful");
       JsonPath jsonPath = response.jsonPath();
       assertThat(jsonPath.getList("products"), is(not(empty())));
    }
    @Test (priority=2)
    public void getRequestWithParam() {
        System.out.println("testCase 2 - GET request with param");
        RestAssured.baseURI = "https://dummyjson.com/products";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.when().get("/1");
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "API call was unsuccessful");
        System.out.println("Status Code: " + statusCode);
        System.out.println("Response Body: " + response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getInt("id"), 1, "Product ID does not match");
        assertNotNull(jsonPath.getString("title"), "Product should have a title");
  
    
}
@Test (priority=3)
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
    assertEquals(statusCode, 200, "API call was unsuccessful");
    System.out.println("Status Code: " + statusCode);
    System.out.println("Response Body: " + response.getBody().asString());
    JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("title"), "BMW Pencil", "Title does not match");
}

@Test (priority=4)
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
    assertEquals(statusCode, 200, "API call was unsuccessful");
  
    System.out.println("Status Code: " + statusCode);
    System.out.println("Response Body: " + response.getBody().asString());
    JsonPath jsonPath = response.jsonPath();
    assertEquals(jsonPath.getString("title"), "iPhone Galaxy +1", "Title was not updated");
  
  
}
@Test(priority=5)
public void DeleteRequest(){
    System.out.println("testCase 5 - POST request");
    RestAssured.baseURI = "https://dummyjson.com/products";
    RequestSpecification httpRequest = RestAssured.given();
    Response response = httpRequest.when().delete("/1");
    int statusCode = response.getStatusCode();
    System.out.println("Status Code: " + statusCode);
    System.out.println("Response Body: " + response.getBody().asString());
    assertEquals(statusCode, 200, "API call was unsuccessful");
}
}
