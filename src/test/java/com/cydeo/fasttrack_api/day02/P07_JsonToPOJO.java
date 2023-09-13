package com.cydeo.fasttrack_api.day02;

import com.cydeo.POJO.Search;
import com.cydeo.POJO.Spartan;
import com.cydeo.fasttrack_api.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P07_JsonToPOJO extends SpartanTestBase {

    @Test
    public void getSingleSpartan() {
        Response response = given().log().all()
                .accept(ContentType.JSON)
                .pathParam("id", 10)
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().response();
        //First approach -->Response
        System.out.println("--------First approach -->Response.as()----------");
        Spartan spartan = response.as(Spartan.class);
        System.out.println("spartan = " + spartan);
        System.out.println(spartan.getId());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getName());
        System.out.println(spartan.getPhone());

        //Second approach -->JsonPath
        System.out.println("--------Second approach -->JsonPath----------");
        JsonPath jsonPath = response.jsonPath();
        Spartan sparta = jsonPath.getObject("", Spartan.class);
        System.out.println("sparta = " + sparta);
        System.out.println(sparta.getId());
        System.out.println(sparta.getName());
        System.out.println(sparta.getGender());
        System.out.println(sparta.getPhone());

    }

    @Test
    public void searchSpartan() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("nameContains", "f")
                .queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        //First approach -->Response
        System.out.println("--------Get me first spartan -->Response.as()----------");

        //response.as("") We are not gonna use response.as() method to get partial of response as POJO class
        //It doesn't have path parameter to do it


        //Second approach -->JsonPath
        System.out.println("--------SGet me first spartan -->JsonPath----------");
        JsonPath jsonPath = response.jsonPath();
        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println(spartan);
        System.out.println(spartan.getPhone());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getName());
        System.out.println(spartan.getId());


    }

    @Test
    public void searchSpartanPOJO() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("nameContains", "f")
                .queryParam("gender", "Female")
                .when().get("/spartans/search")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON.toString())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Search search = jsonPath.getObject("", Search.class);

        //print out total Element
        System.out.println(search.getTotalElement());
/*
        //print amount of spartans we have
        List<Spartan> content = search.getContent();
        System.out.println(search.getContent().size());

        //print the first spartan info
        System.out.println("First Spartan = " + content.get(0));

        //print the first spartan name
        System.out.println(content.get(0).getName());*/

        //print amount of spartans we have
        List<Spartan> allSpartans = search.getAllSpartans();
        for (Spartan eachSpartan : allSpartans) {
            System.out.println(eachSpartan);

        }

        //print the first spartan info
        System.out.println("First Spartan " + allSpartans.get(0));
    }
}
