package class01_get_http_request_method;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
/*
    Given
        https://restful-booker.herokuapp.com/booking/5450
    When
        Url'e GET Request gonder
    Then
        Response body asagidaki gibi olmali;
        {
                "firstname": "James",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates":
                {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                  },
    "additionalneeds": "Breakfast"
        }
 */

    @Test
    public void get09(){
        //   1.adım  url yi set et
        spec.pathParams("first", "booking", "second",74);

        //   2. adım beklenen datayı set et
        Map<String,String> expectedBookingDates = new HashMap<>();
        expectedBookingDates.put("checkin","2018-01-01" );
        expectedBookingDates.put("checkout","2019-01-01");

        Map<String,Object> expectedDate = new HashMap<>();
        expectedDate.put("firstname","James");
        expectedDate.put("lastname","Brown");
        expectedDate.put("totalprice",111);
        expectedDate.put("depositpaid", true);
        expectedDate.put("additionalneeds","Breakfast");

        System.out.println(expectedDate);

        //   3.adım: get request gonder ve get response al
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();
        response.as(HashMap.class);

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);


        //   4.adım assertion  yap
        assertEquals(expectedDate.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedDate.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedDate.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedDate.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(expectedDate.get("additionalneeds"),actualData.get("additionalneeds"));

       assertEquals(expectedBookingDates.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingDates.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));




    }



}
