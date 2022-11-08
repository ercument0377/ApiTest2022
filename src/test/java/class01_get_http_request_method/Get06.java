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
            https://restful-booker.herokuapp.com/booking/335
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                            "firstname": "James",
                            "lastname": "Bond",
                            "totalprice": 700,
                            "depositpaid": true,
                            "bookingdates": {
                                      "checkin": "2022-11-11",
                                         "checkout": "2023-01-01"
    },
    "additionalneeds": "Breakfast"
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
        spec.pathParams("first","booking","second",335);

        //   2. adım beklenen datayı set et


        //   3.adım: get request gonder ve get response al
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        //   4.adım assertion  yap
           //1.yol

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("James")).
                body("lastname",equalTo("Bond")).
                body("totalprice",equalTo(700)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2022-11-11")).
                body("bookingdates.checkout",equalTo("2023-01-01")).
                body("additionalneeds",equalTo("Breakfast"));

            //2.yol JsonPath kullanarak assertion yaparız
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        JsonPath json = response.jsonPath();
        assertEquals("isimler islesmiyor ","James", json.getString("firstname"));
        assertEquals("soyisimler islesmiyor ","Bond", json.getString("lastname"));
        assertEquals("totalprice islesmiyor ",700, json.getInt("totalprice"));
        assertEquals("depositpaid  eslesmiyor ", true, json.get("depositpaid"));
        assertEquals("checkin date islesmiyor ","2022-11-11", json.getString("bookingdates.checkin"));
        assertEquals("checkout date islesmiyor ","2023-01-01", json.getString("bookingdates.checkout"));

            //3.yol SoftAssert (hepsini çalıştırır ve butun hataları gösterir)
        //i- SoftAssert objesini olusturma
        SoftAssert softAssert = new SoftAssert();

        //ii- Soft Assert objesini kullanarak Assertion yamak
        softAssert.assertEquals(json.getString("firstname"), "James", "isimler eslesmiyor");
        softAssert.assertEquals(json.getString("lastname"), "Bond", "soyisimler eslesmiyor");

        softAssert.assertEquals(json.getInt("totalprice"), 700, "totalprice eslesmiyor");
        softAssert.assertEquals(json.getBoolean("depositpaid"), true, "depositpaid eslesmiyor");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2022-11-11", "checkin date eslesmiyor");
        softAssert.assertEquals(json.getString("bookingdates.checkout"), "2023-01-01", "checkout date eslesmiyor");
        softAssert.assertEquals(json.getString("additionalneeds"), "Breakfast", "additionalneeds eslesmiyor");

        //MUTLAKA EN SONDA asertAll() yapılmalı. Eger assertAll() kullanmazsanız testini gecti görünür fakat bu anlamlı olmayabilir
        softAssert.assertAll();


    }



}

