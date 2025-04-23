package apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTests {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getTest(){
        Response response=given().when().get("/posts/1").then().statusCode(200).extract().response();
        response.prettyPrint();

        JSONObject res=new JSONObject(response.asString());
    }

    @Test
    public void postTest(){
        JSONObject res=new JSONObject();
        Response response=given().contentType(ContentType.JSON).body(res).when().get("/posts/1").then().statusCode(200).extract().response();
        response.prettyPrint();

       // JSONObject res=new JSONObject(response.asString());
    }

    @Test
    public void fileupload(){
        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification request=RestAssured.given();
        File file=new File("HiraWahid.pdf");
        JSONObject body=new JSONObject();
        body.put("filename","HiraWahid.pdf");
        body.put("filetype","application/pdf");
        body.put("uploadedBy","HiraWahid");
        Response response=request
                .contentType(ContentType.MULTIPART)
                .multiPart(file)
                .multiPart("data",body.toString())
                .when()
                .post("post")
                .then()
                .statusCode(200)
                .extract()
                .response();
        response.prettyPrint();
    }

    @Test
    public void getResponseHeaders(){
        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification request=RestAssured.given();
        File file=new File("HiraWahid.pdf");
        JSONObject body=new JSONObject();
        body.put("filename","HiraWahid.pdf");
        body.put("filetype","application/pdf");
        body.put("uploadedBy","HiraWahid");
        Response response=request
                .contentType(ContentType.MULTIPART)
                .multiPart(file)
                .multiPart("data",body.toString())
                .when()
                .get("headers")
                .then()
                .statusCode(200)
                .extract()
                .response();
        response.prettyPrint();
    }

    @Test
    public void testGet(){
        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification req=RestAssured.given();
        JSONObject body=new JSONObject();
        body.put("filename","HiraWahid.pdf");
        body.put("filetype","application/pdf");
        body.put("uploadedBy","HiraWahid");
        Response res=req
                .queryParam("key","hira")
                .when()
                .get("get")
                .then()
                .statusCode(200)
                .extract().response();
        res.prettyPrint();
        JSONObject resjson=new JSONObject(res.asString());
        Assert.assertEquals(resjson.getJSONObject("args").get("key"),"hira");

    }

    @Test
    public void testPostJson(){

        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification req=RestAssured.given();
        JSONObject body=new JSONObject();
        body.put("filename","HiraWahid.pdf");
        body.put("filetype","application/pdf");
        body.put("uploadedBy","HiraWahid");
        Response res=req
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("post")
                .then()
                .statusCode(200)
                .extract()
                .response();

        res.prettyPrint();

    }

    @Test
    public void testput(){

       // RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification req=RestAssured.given();
        JSONObject body=new JSONObject();
        body.put("userId",1);
        body.put("id",1);
        body.put("title","sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        body.put("body","my own suscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        Response res=req
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .response();
        res.prettyPrint();

    }

    @Test
    public void testBasicAuth(){

        RestAssured.baseURI="https://postman-echo.com/";

        RequestSpecification req=RestAssured.given();

        Response res=req
                .auth()
                .preemptive()
                .basic("postman","password")
                .when()
                .get("/basic-auth")
                .then()
                .statusCode(200)
                .extract()
                .response();
        res.prettyPrint();

    }

    @Test
    public void getCookies(){
        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification req=RestAssured.given();
        Response res=req
                .when()
                .get("cookies/")
                .then()
                .statusCode(200)
                .extract()
                .response();
        res.prettyPrint();
    }

    @Test
    public void testdelay(){
        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification req=RestAssured.given();
        long time=req
                .get("delay/0")
                .time();

        System.out.println(time);
    }

    @Test
    public void testpatch(){
        RequestSpecification req=RestAssured.given();

        JSONObject body=new JSONObject();
        body.put("body","my patched nnostrum rerum est autem sunt rem eveniet architecto");
        Response response=req
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .extract()
                .response(); // Correct header assertion

        response.prettyPrint();

    }

    @Test
    public void testDelete(){
        RequestSpecification req=RestAssured.given();
        Response res=req.when().delete("/posts/1").then().statusCode(200).extract().response();
        System.out.println(res.headers());
    }

    //file upload

    @Test
    public void uploadTest(){
        File file=new File("HiraWahid.pdf");
        JSONObject type=new JSONObject();
        type.put("documentType","resume");
        RestAssured.baseURI="https://postman-echo.com/";
        RequestSpecification requestSpecification=RestAssured.given().log().all();
        Response res=requestSpecification.contentType(ContentType.MULTIPART)
                .multiPart(file)
                .multiPart("form-data",type.toString())
                .auth()
                .oauth2("747675nf4876355v4y84884hff")
                //.header("Authorization","Bearer 747675nf4876355v4y84884hff")
                .when()
                .post("post")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract().response();


        JSONObject response=new JSONObject(res.asString());
     //   Assert.assertEquals(response.getString("status"),"uploaded");
      //  Assert.assertTrue(response.get("url").toString().endsWith("HiraWahid.pdf"));

    }

    //for params

    @Test
    public void formBasedAuthTest(){
        RestAssured.baseURI="https://opensource-demo.orangehrmlive.com/";
        RequestSpecification requestSpecification=RestAssured.given().log().all();
        Response res=requestSpecification
                .contentType("application/x-www-form-urlencoded")
                .formParam("username","Admin")
                .formParam("password","admin123")
                .when()
                .post("web/index.php/auth/login")
                .then()
                .log()
                .all()
                .statusCode(302)
                .extract()
                .response();

        System.out.println(res.getCookie("orangecrm"));



    }

    @Test
    public void test_orangehrm(){
        RestAssured.baseURI="https://opensource-demo.orangehrmlive.com/web/index.php/api/v2/login";
        JSONObject body=new JSONObject();
        body.put("username","Admin");
        body.put("password","admin123");
        RequestSpecification req=RestAssured.given().log().all();
        Response res=req.contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post()
                .then()
                .log().all()
                .extract().response();
        res.prettyPrint();

    }

    //TODO: form based auth and form parameters
    //TODO: json elements assertions in body
    //TODO: query params
    //TODO: SESSION support
    //given().relaxedHTTPSValidation()

}
