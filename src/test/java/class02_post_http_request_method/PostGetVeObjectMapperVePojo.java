package class02_post_http_request_method;

import Utils.JsonUtil;
import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import class06_pojos.HerOkuAppPostResponseBodyPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class PostGetVeObjectMapperVePojo  extends HerOkuAppBaseUrl {
    /*
   Given
       https://restful-booker.herokuapp.com/booking
       {
       "firstname": "James",
       "lastname": "Can",
       "totalprice": 1245,
       "depositpaid": true,
       "bookingdates": {
               "checkin": "2022-09-09",
               "checkout": "2022-09-21"
           },
        "additionalneeds": "Orange juice"
          }
   When
        Url'e POST Request gonderdim
   And
        Url'e Get Request gonderdim
   Then
       Status code is 200
   And
       GET response body asagidaki gibi olmali
               {
                       "bookingid": 40208,
                       "booking": {
                       "firstname": "James",
                       "lastname": "Can",
                       "totalprice": 1245,
                       "depositpaid": true,
                       "bookingdates": {
                               "checkin": "2022-09-09",
                               "checkout": "2020-09-21"
                           },
                       "additionalneeds": "Orange juice"
                       }
                  }
 */

@Test
    public void test01(){
    //1.adım: url set et
    spec.pathParam("first", "booking");

    //2.adım: beklenen(expected) datayı set et
    BookingDatesPojo bookingDate = new BookingDatesPojo("2022-09-09","2020-09-21");
    BookingPojo expectedDAta = new BookingPojo("James","Can",1245,true, bookingDate,"Orange juice");

    //3.adım: POST request gonder, response al
   Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDAta).when().post("/{first}");
    response.prettyPrint();

   HerOkuAppPostResponseBodyPojo postResponsePojo = JsonUtil.jsoniJavayaCevir(response.asString(), HerOkuAppPostResponseBodyPojo.class);
    System.out.println(postResponsePojo);
   Integer bookingId = postResponsePojo.getBookingid();


   //booking id kullanarak GET request göndeririz

    //1.adım: URL yi set et
    spec.pathParams("first","booking","second", bookingId);

    //2.adım: expected datayı set et
    // Expecteddatayuı set etmeye gerek yoktur, cunku post request ile gonderdıgım expected data aynıdır

    //3.adım: get request gonder response al
  Response response1 =  given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
  response1.prettyPrint();
  //ObjectMapper kullanarak GET request body i JAva object e donusturdum
 BookingPojo getResponsePojo = JsonUtil.jsoniJavayaCevir(response1.asString(), BookingPojo.class);
    System.out.println(postResponsePojo);

 //4.adım: assertion yap
    response1.then().statusCode(200);

    assertEquals(postResponsePojo.getBooking().getFirstname(), getResponsePojo.getFirstname());
    assertEquals(postResponsePojo.getBooking().getLastname(), getResponsePojo.getLastname());
    assertEquals(postResponsePojo.getBooking().getBookingdates().getCheckout(), getResponsePojo.getBookingdates().getCheckout());
    //veya
    assertEquals(expectedDAta.getTotalprice(), getResponsePojo.getAdditionalneeds());
    assertEquals(expectedDAta.getAdditionalneeds(), getResponsePojo.getAdditionalneeds());
    assertEquals(expectedDAta.getBookingdates().getCheckin(), getResponsePojo.getBookingdates().getCheckin());




}


}
