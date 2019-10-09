package allureDemo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class XibenTiTest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="http://172.18.89.11";
    }


    @DisplayName("我是标题 junit5 ALLURE")
    @Test()
    public void testShiTiCi(){
        JSONObject body=new JSONObject();
        body.put("pageNo",1);
        body.put("pageSize",1000);
        body.put("queryStr","");
        body.put("superClassId","思想概念");

        String content=given().contentType("application/json").body(body).when().post("/refining/rest/ont/pageIndividualByClass").
                then().extract().response().body().asString();
       // System.out.println(content);
               // body("statusCode",equalTo(200)).log().all().body("data.totalHits",equalTo(518));
        List<String> nameList = (List<String>) JSONPath.read(content, "$.data.source[*].individualName");


    }
}
