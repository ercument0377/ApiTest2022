package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post02_update extends JsonPlaceHolderBaseUrl {

    /*
   Given
            https://jsonplaceholder.typicode.com/todos
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
              }
    When
           Url'e POST Request gonder
    Then
        Status code is 201
    And
        response body is like {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
         }
 */

        @Test
        public void post02(){

            //   1.adım  url yi set et
            spec.pathParam("first","todos");

            //   2. adım beklenen datayı set et
            JsonPlaceHolderTestData beklenenData = new JsonPlaceHolderTestData();
            Map<String, Object>  beklenenDataMap = beklenenData.expectedDataSetup();


            //   3.adım: get request gonder ve get response al
            Response response = given().
                    spec(spec).auth().basic("admin","1234").
                    contentType(ContentType.JSON).
                    body(beklenenData).when().
                    post("/{first}");
            response.prettyPrint();

            beklenenDataMap.put("Status Code",201);

            //   4.adım assertion  yap

            Map< String, Object> actualData =  response.as(HashMap.class); // De-serialization  map formatına dönüştürdük

            System.out.println(actualData);


            response.then().statusCode(201);

            assertEquals(beklenenDataMap.get("Status Code"), response.getStatusCode());
            assertEquals(beklenenDataMap.get("userId"), actualData.get("userId"));
            assertEquals(beklenenDataMap.get("title"), actualData.get("title"));
            assertEquals(beklenenDataMap.get("completed"), actualData.get("completed"));


        }
    }


