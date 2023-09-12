package com.cydeo.fasttrack_api.day02;

import com.cydeo.fasttrack_api.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class P02_ResponsePath extends HrTestBase {

/**

 Given
 Accept-type is application/json

 When
 user sends get request to /regions/2

 Then
 response status code must be 200
 region_name is Americas
 region_id is 2
 print out all the links
 */

@Test
   public void getSingleRegion(){

    Response response = given().accept(ContentType.JSON)
            .and()
            .pathParam("id", 2)
            .when().get("/regions/{id}")
            .prettyPeek();

    //response status code must be 200
    assertEquals(HttpStatus.SC_OK,response.statusCode());

    //region_name is Americas
    assertEquals("Americas",response.path("region_name"));

    //region_id is 2
    assertEquals(2, (Integer) response.path("region_id"));

    //print out all the links
    List<Map<String,String>> links = response.path("links");
    System.out.println("----PRINT EACH LINK INFO-----");
    for (Map<String, String> eachLink : links) {
        System.out.println(eachLink.get("rel"));
        System.out.println(eachLink.get("href"));

    }
    //get me all href info
    List<String> hrefLinks = response.path("links");
    System.out.println("hrefLinks = " + hrefLinks);

}

    @ParameterizedTest
    @CsvFileSource(resources = "/regions.csv",numLinesToSkip = 1)
    public void parameterizedTest(int id,String regionName) {
        System.out.println(id+"----->"+regionName);

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", id)
                .when().get("/regions/{id}")
                .prettyPeek();

        //response status code must be 200
        assertEquals(HttpStatus.SC_OK,response.statusCode());

        //region_name is Americas
        assertEquals(regionName,response.path("region_name"));

        //region_id is 2
        assertEquals(id, (Integer) response.path("region_id"));
    }
}
