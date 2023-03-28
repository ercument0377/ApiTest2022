package class02_post_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import class06_pojos.JsonPlaceHolderPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class PostVePojo01 extends JsonPlaceHolderBaseUrl {
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
         response body is like asagıdaki gibi olmnalı
         {
              "userId": 55,
              "title": "Tidy your room",
              "completed": false,
              "id": 201
         }
 */
    @Test
    public void postVePojo01(){
        //1.adım
        spec.pathParam("first","todos");

        //2.adım
        JsonPlaceHolderPojo requestBodyPojo = new JsonPlaceHolderPojo(55,"Tidy your room",false);


        //3.adım
       Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyPojo).post("/{first}");
    //   response.prettyPrint();    // respose objesi bu yontemle yazdırılır

        //4.adım
        response.then().assertThat().statusCode(201).body("userId",equalTo(requestBodyPojo.getUserId()),
        "title",equalTo(requestBodyPojo.getTitle()), "completed", equalTo(requestBodyPojo.getCompleted()));


        //2.yol : De-serization
     JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
    assertEquals(requestBodyPojo.getUserId(), actualData.getUserId());
    assertEquals(requestBodyPojo.getTitle(), actualData.getTitle());
    assertEquals(requestBodyPojo.getCompleted(), actualData.getCompleted());


    }
}
