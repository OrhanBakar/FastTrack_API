package com.cydeo.fasttrack_api.day01;

import com.cydeo.fasttrack_api.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanTest extends SpartanTestBase {

    /**
     * Send request to spartan url and save the response
     * Accept application/json
     * GET/spartans
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *      - response
     *      - Content-Type
     *      - Status Code
     *      - Get me first spartan gender
     *      - Get me first spartan name
     *      - Get me all spartan name
     */
    @Test
    public void getAllSpartans(){

        Response response = given().log().all().accept(ContentType.JSON)
                .when().get("/spartans");

        //- response
        response.prettyPrint();


        //- Content-Type
        assertEquals(ContentType.JSON.toString(),response.contentType());

        //- Status Code
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        //- Get me first spartan gender
        assertEquals("Male",response.path("[0].gender"));
        assertEquals("Male",response.path("gender[0]"));
        //- Get me first spartan name
        assertEquals("Meade",response.path("[0].name"));
        assertEquals("Meade",response.path("name[0]"));

        //- Get me all spartan name
        List<String> allNames = response.path("name");
        System.out.println("allNames = " + allNames);

    }

}
