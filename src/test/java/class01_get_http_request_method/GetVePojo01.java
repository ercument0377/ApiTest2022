package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import class06_pojos.BookingDatesPojo;
import class06_pojos.BookingPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class GetVePojo01 extends HerOkuAppBaseUrl {
/*
Given
        https://restful-booker.herokuapp.com/booking/2
When
   Url'e GET Request gonder
Then
    Status code is 200
And response body is like
        {
    "firstname": "Jim",
    "lastname": "Jackson",
    "totalprice": 478,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "22020-07-01",
        "checkout": "2022-08-22"
    },
    "additionalneeds": "Breakfast"
}
 */
    @Test
    public void getVePojo01(){
        //1.yol url set et
        spec.pathParams("ilk","booking","ikinci",2);

        //2.adım expected datayı set et

        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-06-05","2022-11-26");
        BookingPojo expectedData = new BookingPojo("Eric", "Jones",104,true, bookingDates, "Breakfast");
        System.out.println(expectedData);

        //3.adım request gonder response al
       Response response = given().spec(spec).when().get("/{ilk}/{ikinci}");
        response.prettyPrint();




        //4.adım assertion yap
        BookingPojo actualData =  response.as(BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200, response.getStatusCode() );

        assertEquals("isimler eslesmiyor", expectedData.getFirstname(), actualData.getFirstname());
        assertEquals("Toplam ucret eslesmiyor", expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getLastname(), actualData.getLastname());

        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());

    }


}
