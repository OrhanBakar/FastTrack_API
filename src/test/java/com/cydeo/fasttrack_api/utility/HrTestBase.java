package com.cydeo.fasttrack_api.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){
        baseURI="http://3.80.111.193:1000";
        basePath="/ords/hr";

    }

    @AfterAll
    public static void destroy(){

        reset();
    }

}
