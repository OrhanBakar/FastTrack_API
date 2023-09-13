package com.cydeo.fasttrack_api.day02;

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
public class P06_JsonToJava extends SpartanTestBase {

    /**
     Given
            -accept type is application/json
     And
            -path param id=10
     When
            -I send GET requst to /api/spartans
     Then
            -status code is 200
            -content type is json
            -spartan data matching:
                    -id>10
                    -name>Lorenza
                    -gender>Female
                    -phone>3312820936
     * */
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
        System.out.println("--------First approach -->Response.path()----------");
        //Map<String,Object> spartanMap = response.as(Map.class);
        //as() method do deserialization and if we want to convert JSON to MAP
        //by using as() method, then JACKSON or GSON(Databind / Objectmapper libraries) is required
        Map<String,Object> spartanMap = response.path("");
        //response.path() de deserialization a gerek yoktur
        System.out.println("spartanMap = " + spartanMap);
        int id = (int) spartanMap.get("id");
        String name = (String) spartanMap.get("name");
        String gender = (String) spartanMap.get("gender");
        Long phone = (Long) spartanMap.get("phone");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

//Second approach -->JsonPath
        System.out.println("--------Second approach -->JsonPath----------");
        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> spMap = jsonPath.getMap("");
        int id1 = (int) spMap.get("id");
        System.out.println("id1 = " + id1);
        String name1 = (String) spMap.get("name");
        System.out.println("name1 = " + name1);
        String gender1 = (String) spMap.get("gender");
        System.out.println("gender1 = " + gender1);
        Long phone1 = (Long) spMap.get("phone");
        System.out.println("phone1 = " + phone1);
    }

    @Test
    public void getAllSpartans() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/spartans")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .extract().response();
//First approach -->Response
        System.out.println("--------First approach -->Response.path()----------");
        List<Map<String,Object>> allSpartans = response.path("");

        //print out spartans
        for (Map<String,Object>eachSpartan:allSpartans){
            System.out.println(eachSpartan);
        }

        //find me the first spartan info
        System.out.println("aFirst Spartan = " + allSpartans.get(0));

        //find me the first spartan name
        Map<String, Object> firstSpartan = allSpartans.get(0);
        String name = (String) firstSpartan.get("name");
        System.out.println("First Spartan Name = " + name);

        System.out.println("First Spartan Name = " + allSpartans.get(0).get("name"));

        //Second approach -->JsonPath
        System.out.println("--------Second approach -->JsonPath----------");
        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> spartansList = jsonPath.getList("");

        //print out spartans
        for (Map<String, Object> eachSpartan : spartansList) {
            System.out.println(eachSpartan);

            //find me the first spartan info
            System.out.println(spartansList.get(0));
            //find me the first spartan name
            System.out.println(spartansList.get(0).get("name"));

        }

    }
}
