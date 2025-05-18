package apiTests;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
        Response response=given()
               // .contentType(ContentType.JSON)
                .body(res).when().get("/posts/1").then().statusCode(200).extract().response();
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

    @Test
    public void getTest1(){
        RequestSpecification requestSpecification=new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setBasePath("/posts/1").setContentType(ContentType.JSON).build();

        ResponseSpecification resSpec=new ResponseSpecBuilder().expectContentType("application/json").expectStatusCode(200)
                .expectResponseTime(lessThan(1000L)).build();

        int id= given()
                .spec(requestSpecification)
                .get().then()
                .spec(resSpec)
//                .statusCode(200)
//                .header("Content-Type",containsString("application/json"))
//                .body("userId",equalTo(1))
                .extract()
                .path("userId");
       System.out.println(id);
    }

    @Test
    public void TestPathParam(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Response res=given().log().all()
               .contentType(ContentType.JSON)
                .basePath("posts/{posts}")
                .pathParam("posts","1")
                .queryParam("ncr","1")
                .when()
                .get()
                .then()
                .time(lessThan(1000L))
                .statusCode(200)
                .extract()
                .response();
        System.out.println(res.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println(res.headers());
        System.out.println(res.contentType());
        System.out.println(res.cookies());
        System.out.println(res.statusLine());
        System.out.println(res.sessionId());
    }

    @Test
    public void testPost1(){
        RequestSpecification req= new RequestSpecBuilder()
                .setBaseUri("https://run.mocky.io/")
                .setBasePath("v3/")
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification res=new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(lessThan(1000L))
                .build();

        JSONObject body=new JSONObject();
        body.put("email","eve.holt@reqres.in");
        body.put("password","cityslicka");

        String token=RestAssured.given()
                .spec(req).log().all()
                .body(body.toString())
                .when()
                .post("2fdf0f97-174e-4ad3-9fec-c99cad6bf005")
                .then()
                .spec(res)
                .extract()
                .path("token");
        System.out.println(token);

    }

    @Test
    public void deteleuser(){
        RestAssured.baseURI="https://68279d5f6b7628c52910f090.mockapi.io";
        given()
                .pathParam("userId","1")
                .when()
                .delete("/users/{userId}")
                .then()
                .statusCode(200);

        given().contentType(ContentType.JSON).when().get("/users/1").then().statusCode(404);
    }

    @Test
    public void putuser(){
        RestAssured.baseURI="https://68279d5f6b7628c52910f090.mockapi.io";
        JSONObject body=new JSONObject();
        body.put("gender123","male");
//        body.put("createdAt","2025-05-16T00:48:02.753Z");
//        body.put("avatar","https://avatars.githubusercontent.com/u/62641508");

        given()
                .contentType(ContentType.JSON)
              //  .pathParam("userId","1")
                .when()
                .body(body.toString())
                .patch("/users/22")
                .then()
                .statusCode(200);

      //  given().contentType(ContentType.JSON).when().get("/users/1").then().statusCode(404);
    }

    @Test
    public void sendFile(){

        RestAssured.baseURI="https://postman-echo.com/";
        JSONObject type=new JSONObject();
        type.put("documentType","resume");
        File toSend=new File("HiraWahid.pdf");
        given().contentType(ContentType.MULTIPART)
                .multiPart(toSend)
                .multiPart("form-data",type.toString())
                .when()
                .post("post")
                .then()
                .statusCode(200).log().all();

    }

}
