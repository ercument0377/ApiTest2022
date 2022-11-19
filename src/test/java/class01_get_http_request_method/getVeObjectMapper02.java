package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static Utils.JsonUtil.jsoniJavayaCevir;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class getVeObjectMapper02 extends HerOkuAppBaseUrl {

    /*
    Given
         https://restful-booker.herokuapp.com/booking/2
    When
         Url'e GET Request gonder
    Then
         Status code is 200
        {
            "firstname": "Mary",
            "lastname": "Jackson",
            "totalprice": 726,
            "depositpaid": true,
            "bookingdates": {
                    "checkin": "2015-08-07",
                    "checkout": "2020-10-25"
                            },
            "additionalneeds": "Breakfast"
           }
 */

    @Test
    public void getVeObjectMapper02(){
        //1.adım: url set et

        spec.pathParams("first", "booking","second",2);

        //2.adım: beklenen(expected) datayı set et
        //1.yol
 /*       String expectedData = {
                "firstname": "Mary",
                "lastname": "Jackson",
                "totalprice": 726,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2015-08-07",
                    "checkout": "2020-10-25"

        "additionalneeds": "Breakfast"
           };
*/
        Map<String, Object> beklenenDataMap = jsoniJavayaCevir(expectedData, HashMap.class);
        System.out.println(expectedData);

        //3.adım: request gonder, response al
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
       HashMap<String, Object> actualData = JsonUtil.jsoniJavayaCevir(response.asString(), HashMap.class);

        //4.adım: assertion yap
        assertEquals(200, response.getStatusCode());
        assertEquals(beklenenDataMap.get("firstname"), actualData.get("firstname"));
        assertEquals(beklenenDataMap.get("lastname"), actualData.get("lastname"));
        assertEquals(beklenenDataMap.get("totalprice"), actualData.get("totalprice"));
        assertEquals(beklenenDataMap.get("depositpaid"), actualData.get("depositpaid"));

    // eksik    assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"), ((Map)actualData.get("bookingdates")));

    }
