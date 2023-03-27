package class02_post_http_request_method;

import base_url.AgroMonitoringBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.AgroMonitoringTestData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Post03 extends AgroMonitoringBaseUrl {
    /*
       Given
        "http://api.agromonitoring.com/agro/1.0/polygons?appid=f6f34736da064d30fa1c2cab6e31972e"


    When
         I send POST Request to the Url
    Then
        Assert Status Code (201)
        And Response Body should be like {
        "id": "5fd8c383714b523b2ce1f154",
        "geo_json": {
            "geometry": {
                "coordinates":
                   [ [ [-121.1958, 37.6683],
                        [-121.1779, 37.6687],
                        [-121.1773, 37.6792],
                        [-121.1958, 37.6792],
                        [-121.1958, 37.6683] ] ],
                "type": "Polygon"
                        },
            "type": "Feature",
            "properties": {}
                    },
        "name": "Polygon Sample",
        "center": [-121.1867, 37.67385],
        "area": 190.9484,
        "user_id": "5fd8c02a3da20c000759e0f8",
        "created_at": 1608041347
    }
     */

    //   1.adım  url yi set et
    //   2. adım beklenen datayı set et
    //   3.adım: get request gonder ve get response al
    //   4.adım assertion  yap

    @Test
    public void post03(){
        //   1.adım:  url yi set et
        spec.pathParams("first","agro","second",1.0, "final","polygons").
                queryParams("appid","f6f34736da064d30fa1c2cab6e31972e","duplicated",true);


        //   2. adım beklenen datayı set et
        AgroMonitoringTestData requestBody = new AgroMonitoringTestData();
       Map<String, Object>  requestBodyMap = requestBody.requestBodySetUp();


        //   3.adım: get request gonder ve get response al
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().post("/{first}/{second}/{final}");
        response.prettyPrint();

    // request body'ye yeni data eklenebilir.
        requestBodyMap.put("area",190.9484);
        System.out.println(requestBodyMap);


        //   4.adım assertion  yap
        //Gson response deki (json) java object ==> De-Serialization a ceviriyoruz

       Map<String,Object> responseBody = response.as(HashMap.class);
        System.out.println(responseBody);

        assertEquals(requestBodyMap.get("area"),responseBody.get("area"));
        response.then().assertThat().statusCode(201);
        assertEquals(requestBodyMap.get("name"),responseBody.get("name"));


        assertEquals(requestBody.geometrySetUp().get("type"),((Map) ((Map)responseBody.get("geo_json")).get("geometry")).get("type"));

        System.out.println(requestBody.coordinates[0][1][0]);
        assertEquals(String.valueOf(requestBody.coordinates[0][1][0]), ((Map)((Map)responseBody.get("geo_json")).get("geometry")).get("coordinates").toString().substring(25,34));


    }


}
