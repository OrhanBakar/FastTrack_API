package com.cydeo.fasttrack_api.day02;

import com.cydeo.fasttrack_api.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HamcrestMatcher extends HrTestBase {
    /**
     * Given
     * accept-type is application/json
     * When
     * user sends get request to /regions
     * Then
     * response status code must be 200
     * verify Date has values
     * first region name is Europe
     * Regions name should be in same order as "Europe","Americas","Asia","Middle East and Africa"
     * region ID is need to be 1,2,3,4
     */
    @Test
    public void getAllRegions() {

        //accept-type is application/json
        //user sends get request to /regions
        given().accept(ContentType.JSON)
                .when().get("regions").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .header("Date", notNullValue())
                .body("items[0].region_name", is("Europe"))
                .body("items.region_name", containsInRelativeOrder("Europe", "Americas", "Asia", "Middle East and Africa"))
                .body("items.region_id", containsInRelativeOrder(1, 2, 3, 4));


    }

}
