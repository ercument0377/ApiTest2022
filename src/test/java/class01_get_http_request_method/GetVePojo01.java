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
    "firstname": "Eric",
    "lastname": "Smith",
    "totalprice": 773,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2017-12-15",
        "checkout": "2020-01-01"
    },
    "additionalneeds": "Breakfast"
}
 */
    @Test
    public void getVePojo01(){
        //1.yol url set et
        spec.pathParams("ilk","booking","ikinci",2);

        //2.adım expected datayı set et

        BookingDatesPojo bookingDates = new BookingDatesPojo("2017-12-15","2020-01-01");
        BookingPojo expectedData = new BookingPojo("Eric", "Smith",773,true, bookingDates, "Breakfast");
        System.out.println(expectedData);

        //3.adım request gonder response al
       Response response = given().spec(spec).when().get("/{ilk}/{ikinci}");
        response.prettyPrint();




        //4.adım assertion yap
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.getStatusCode());

        assertEquals("isimler eslesmiyor",expectedData.getFirstname(), actualData.getFirstname());
        assertEquals("Toplam ucret eslesmiyor",expectedData.getTotalprice(), actualData.getTotalprice());

        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());



    }


}
