package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/560
        When
            Kullanici GET request gonderir => URL
        Then
            HTTP Status Code: 200
        And
            Response content type : “application/json”
        And
            Response body asagidaki gibi olmali;
            {
                            "firstname": "akifoo",
                              "lastname": "okutucu",
                              "totalprice": 335,
                             "depositpaid": true,
                             "bookingdates": {
                                            "checkin": "2018-01-01",
                                              "checkout": "2019-01-01"
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
        spec.pathParams("first","booking","second",560);

        //   2. adım beklenen datayı set et


        //   3.adım: get request gonder ve get response al
       Response response = given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        //   4.adım assertion  yap
        //1.yol

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname", equalTo("akifoo")).
                body("lastname",equalTo("okutucu")).
                body("totalprice",equalTo(335)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2018-01-01")).
                body("bookingdates.checkout",equalTo("2019-01-01")).
                body("additionalneeds",equalTo("Breakfast"));

        //2.yol


        ////*///*///


    }



}

