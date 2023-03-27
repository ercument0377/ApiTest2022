package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/337
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                            "firstname": "Josh",
                            "lastname": "Allen",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                      "checkin": "2018-01-01",
                                         "checkout": "2019-01-01"
    },
    "additionalneeds": "super bowls"
            }"

            }

         //   1.adım  url yi set et
         //   2. adım beklenen datayı set et
         //   3.adım: get request gonder ve get response al
         //   4.adım assertion  yap
     */

    @Test
    public void get06(){

        //   1.adım  url yi set et
        spec.pathParams("first","booking","second",337);

        //   2. adım beklenen datayı set et


        //   3.adım: get request gonder ve get response al
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        //   4.adım assertion  yap
           //1.yol

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("Josh")).
                body("lastname",equalTo("Allen")).
                body("totalprice",equalTo(111)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2018-01-01")).
                body("bookingdates.checkout",equalTo("2019-01-01")).
                body("additionalneeds",equalTo("super bowls"));

            //2.yol JsonPath kullanarak assertion yaparız
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        assertEquals("isimler islesmiyor ","Josh", json.getString("firstname"));
        assertEquals("soyisimler islesmiyor ","Allen", json.getString("lastname"));
        assertEquals("totalprice islesmiyor ",111, json.getInt("totalprice"));
        assertEquals("depositpaid  eslesmiyor ", true, json.get("depositpaid"));
        assertEquals("checkin date islesmiyor ","2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("checkout date islesmiyor ","2019-01-01", json.getString("bookingdates.checkout"));


            //3.yol SoftAssert (hepsini çalıştırır ve butun hataları gösterir)
        //i- SoftAssert objesini olusturma
        SoftAssert softAssert = new SoftAssert();

        //ii- Soft Assert objesini kullanarak Assertion yapmak
        softAssert.assertEquals(json.getString("firstname"), "Josh", "isimler eslesmiyor");
        softAssert.assertEquals(json.getString("lastname"), "Allen", "soyisimler eslesmiyor");

        softAssert.assertEquals(json.getInt("totalprice"), 111, "totalprice eslesmiyor");
        softAssert.assertEquals(json.getBoolean("depositpaid"), true, "depositpaid eslesmiyor");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2018-01-01", "checkin date eslesmiyor");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2019-01-01", "checkout date eslesmiyor");
        softAssert.assertEquals(json.getString("additionalneeds"), "super bowls", "additionalneeds eslesmiyor");

        //MUTLAKA EN SONDA asertAll() yapılmalı. Eger assertAll() kullanmazsanız testini gecti görünür fakat bu anlamlı olmayabilir
        softAssert.assertAll();


    }



}

