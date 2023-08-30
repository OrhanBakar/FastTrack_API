package com.cydeo.fasttrack_api.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P01_SimpleGETRequest {
    @Test
/**
 * 1. Send request to HR url and save the response
 * 2. GET/regions
 * 3. Store the response in Response Object that comes from the request
 * 4. Print out followings
 *      -Headers
 *      -Content-Type
 *      -Status Code
 *      -Response
 *      -Date
 *      -Verify response body has "Europe"
 *      -Verify response has Date
 */
    public void test1() {

        Response response = RestAssured.get("http://3.80.111.193:1000/ords/hr/regions");
        //print with prettyPeek
        //response.prettyPeek();-->prints into screen response+headers and returns RESPONSE. It helps us to chain method

        // Response
        //print response-->prints response body into screen
        //response.prettyPrint();
        //Headers
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.headers() = " + response.headers());
        //Content-Type
        System.out.println("response.getContentType() = " + response.getContentType());
        System.out.println("response.contentType() = " + response.contentType());
        // Status Code
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        System.out.println("response.statusCode() = " + response.statusCode());

        // Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        // Verify response body has "Europe"
        System.out.println("response.asString().contains(\"Europe\") = " + response.asString().contains("Europe"));
        Assertions.assertTrue(response.asString().contains("Europe"));
        // Verify response has Date
        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
    }
/**
 * 1. Send request to HR and save the response
 * 2. GET/employees/100
 * 3. Store the response in Response Object that comes from get request
 * 4. Print out the following
 *      - First Name
 *      - Last Name
 *      - Verify status code is 200
 *      - Verify first name is "Steven"
 *      - Verify content-Type is application/json
 */
    @DisplayName("GET/employees/100")
    @Test
    public void getOneEmployee() {

        Response response = RestAssured.get("http://3.80.111.193:1000/ords/hr/employees/100");
        response.prettyPeek();

        //- First Name
        String firstName = response.path("first_name");
        System.out.println("firstName = " + firstName);

        //- Last Name
        String lastName = response.path("last_name");
        System.out.println("lastName = " + lastName);

        //- Verify status code is 200
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());

        //- Verify first name is "Steven"
        Assertions.assertEquals("Steven",firstName);

        //- Verify content-Type is application/json
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());


    }

}
