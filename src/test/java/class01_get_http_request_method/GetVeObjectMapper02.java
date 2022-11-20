package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static Utils.JsonUtil.jsoniJavayaCevir;
import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
public class GetVeObjectMapper02 extends HerOkuAppBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/2
    When
         Url'e GET Request gonder
    Then
         Status code is 200
        {
            "firstname": "Jim",
            "lastname": "Jackson",
            "totalprice": 478,
            "depositpaid": true,
            "bookingdates": {
                    "checkin": "2020-07-01",
                    "checkout": "2022-08-22"
                            },
            "additionalneeds": "Breakfast"
           }
 */
    @Test
    public void getVeObjectMapper02() {
        //1.adım: url set et
        spec.pathParams("first", "booking", "second", 2);

        //2.adım: beklenen(expected) datayı set et
        String expectedData = " {\n" +
                " \"firstname\": \"Jim\",\n" +
                " \"lastname\": \"Jackson\",\n" +
                "\"totalprice\": 478,\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2020-07-01\",\n" +
                "\"checkout\": \"2022-08-22\"\n" +
                " },\n" +
                "\"additionalneeds\": \"Breakfast\"\n" +
                "}";

        HashMap<String, Object> expectedDataMap = JsonUtil.jsoniJavayaCevir(expectedData, HashMap.class);
        System.out.println(expectedDataMap);

        //3.adım: request gonder, response al
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        HashMap<String, Object> actualData = JsonUtil.jsoniJavayaCevir(response.asString(), HashMap.class);
        System.out.println(actualData);

        //4.adım: assertion yap
        assertEquals(200, response.getStatusCode());

        assertEquals(expectedDataMap.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), actualData.get("depositpaid"));

        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));

        assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));

    }

    }
