package com.cydeo.fasttrack_api.utility;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        baseURI="http://3.80.111.193:8000";
        basePath="/api";

    }

    @AfterAll
    public static void destroy(){

        reset();
    }

}
