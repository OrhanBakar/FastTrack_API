package com.cydeo.fasttrack_api.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class PO4_ParametersTest {

    /**
     * 1- Given accept type is json
     * 2- Path Parameters values are
     * - id -> 5
     * 3- When user sends GET request to/spartans/{id}
     * 4- Verify followings
     * - Status code should be 200
     * - Content Type is application/json
     * - ID is 5
     * - Name is "Blythe"
     * - gender is "Female"
     * - phone is 3677539542
     */
    @DisplayName("GET/spartans/{id} with Path-Param example")
    @Test
    public void pathParam() {


        Response response = given().log().all().accept(ContentType.JSON)
                .pathParam("id", 5)
                .when().get("/spartans/{id}");
        response.prettyPrint();

        //Status code is 200
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        //Given accept-type is json
        assertEquals(ContentType.JSON.toString(), response.contentType());

        //id -> 5
        int id = response.path("id");
        System.out.println("id = " + id);
        assertEquals(5, id);

        //Name is "Blythe"
        String name = response.path("name");
        System.out.println("name = " + name);
        assertEquals("Blythe", name);

        //gender is "Female"
        String gender = response.path("gender");
        System.out.println("gender = " + gender);
        assertEquals("Female", gender);

        //phone is 3677539542
        long phone = response.path("phone");
        System.out.println("phone = " + phone);
        assertEquals(3677539542l, phone);

    }

    /**
     * 1. Given accept-type is json
     * 2. Query Parameters' value are
     * - gender --> Female
     * - nameContains --> e
     * 3. When user sends GET request to /spartans/search
     * 4. Print out the followings
     * - Total Element Number
     * - Get me firts spartan name
     * - Get me second spartan id
     * - Get me last spartan name
     * - Get me all spartans names
     */
    @DisplayName("GET/spartans/search")
    @Test
    public void queryParam() {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        //1. Given accept-type is json
        //2. Query Parameters' value are
        //   gender --> Female
        //   nameContains --> e
        //3. When user sends GET request to /spartans/search
        Response response = given().accept(ContentType.JSON)
                .queryParams(queryMap)
                //.queryParam("nameContains", "e")
                //.queryParam("gender", "Female")
                .when().get("spartans/search")
                .prettyPeek();

        //Total Element Number
        System.out.println("response.path(\"totalElement\") = " + response.path("totalElement"));

        //Get me first spartan name
        System.out.println("response.path(\"content[0].name\") = " + response.path("content[0].name"));

        //Get me second spartan id
        System.out.println("response.path(\"content[1].id\") = " + response.path("content[1].id"));

        //Get me last spartan name
        System.out.println("response.path(\"content[length-1].name\") = " + response.path("content[-1].name"));

        //Get me all spartans names
        List<String> allNames = response.path("content.name");
        System.out.println(allNames);

        //Verify that status code is 200
        assertEquals(200, response.statusCode());

    }

    /**
     * TASK
     * Given accept-type is json
     * And Id parameter value is 500
     * When user sends GET request to /api/spartans/{id}
     * Then response status should be 404
     * And response content-type: application/json
     * And "Not found" message should be in  response payload
     */
    @DisplayName("Negative Test")
    @Test
    public void negativeTest() {
        // path params key needs to match with http method
        Response response = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("id", "500")
                .when().get("spartans/{id}").prettyPeek();

        //Then response status should be 404
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Not found" message should be in  response payload (Bu daha garantidir)
        assertEquals("Not Found", response.path("error"));
        //or
        assertTrue(response.asString().contains("Not Found"));


    }
}
