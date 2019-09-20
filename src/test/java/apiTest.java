import com.alibaba.fastjson.JSONObject;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class apiTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="http://172.18.89.11";
        RestAssured.port = 11125;
    }

    @Test
    public void test1(){

        JSONObject body=new JSONObject();
        body.put("msgId","123");
        body.put("data","2018年，习近平都参加郭哪些活动");

        given().log().all().contentType("application/json").body(body).when().post("/ontoqa").
                then().
                body("statusCode",equalTo(200)).log().all().body("data.conditions.people[0].name",equalTo("习近平"));
        //System.out.println(content.getBody().prettyPrint());
    }




}
