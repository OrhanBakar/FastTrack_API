package com.cydeo.fasttrack_api.day02;

import com.cydeo.fasttrack_api.utility.HrTestBase;
import com.cydeo.fasttrack_api.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class SpartanTest extends SpartanTestBase {

    /**
     Send a request to get /spartans/search
     Query Parameters values are
                -gender-->Female
                -nameContains -->f
     Log Everything
     Verify followings
                -Status Code is 200
                -Content Type is application/json
                -Total element is 4
                -jsonArray size is 4
                -Names has item "Alfy"
                -Every gender is female
                */
    @Test
    public void searchSpartans() {

given().log().all()
        .queryParam("gender","female")
        .queryParam("nameContains","f")
        .when().get("/spartans/search").prettyPeek()
        .then()
        .log().ifValidationFails()//if any validation fails, it will log detail by using HamCrest
        .statusCode(HttpStatus.SC_OK)
        .contentType(ContentType.JSON.toString())
        .body("totalElement",is(4))
        .body("content",hasSize(4))
        .body("content.name",hasItem("Alfy"))
        .body("content.gender",everyItem(is("Female")));




    }
}
