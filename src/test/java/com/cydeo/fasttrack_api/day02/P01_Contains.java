package com.cydeo.fasttrack_api.day02;

import com.cydeo.fasttrack_api.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_Contains extends HrTestBase {
    /**
     * CONTAIN METHODU PEK TAVSIYE EDILMEZ
     */

/**

 Given
        Accept-type is application/json

 When
        user get request to /regions/2

 Then
        response status code must be 200
        content type equals to application/json
        response body contains Americas
 */
@Test
    public void getSingleRegion(){

    Response response = given().accept(ContentType.JSON)
            .and()//sytatic sugar just to increase the readability of the code
            .pathParam("id", 2)
            .when().get("/regions/{id}")
            .prettyPeek();

    //Status code is 200
    assertEquals(HttpStatus.SC_OK, response.statusCode());

    //Content type is json
    assertEquals(ContentType.JSON.toString(), response.contentType());

    //response body contains Americas
    assertTrue(response.asString().contains("Americas"));
}

}
