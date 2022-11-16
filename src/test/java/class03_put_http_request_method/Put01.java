package class03_put_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    //Put methodu, fully (butun) ,ç,n guncelleme yapar -> butun degiskenleri  gunceller
    //Patch method kısmi guncelleme yapar
    /*
  Given
      https://jsonplaceholder.typicode.com/todos/198

    {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
  When
       URl'e PUT Request gonder
  Then
       Status code is 200
       And response body is like
       {
          "userId": 21,
          "title": "Wash the dishes",
          "completed": false
         }
   */

@Test
    public void put01(){
    //1.adım : url set et
    spec.pathParams("first","todos","second",198);

    // 2.adım : request body veya expected  data set et
    JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
      Map<String, Object> requestBodyMap =  requestBody.expectedDataSetupTumKey(21,"Wash the dishes", false);

      //3.adım: request gonder response al
  Response response =  given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().put("/{first}/{second}");
    response.prettyPrint();

    //4.adım: assertion yap

      //1.yol
    response.then().assertThat().statusCode(200).body("completed",equalTo(requestBodyMap.get("completed")),
            "title",equalTo(requestBodyMap.get("title")),
                "userId",equalTo(requestBodyMap.get("userId")));

    //2.yol
    //Gson kullanarak ==> De-serization yani -- Json ==> Java object formatına donusturduk

     Map<String, Object> gercekDataMap = response.as(HashMap.class);

     assertEquals(requestBodyMap.get("completed"),gercekDataMap.get("completed"));

     // Gson kullanarak Serization yapmak  Java object data ==> Json data Java formatında  olanı Json formatına dönusturuyoruz

    Map<String,Integer> yas = new HashMap<>();
    yas.put("Ali Can",15);
    yas.put("Veli Han",18);
    yas.put("Ayse Kan",21);
    yas.put("Tom Amca",65);
    yas.put("Candy Teyze",88);

    System.out.println(yas); // {Tom Amca=65, CAndy Teyze=88, Ayse Kan=21, Ali Can=15, Veli Han=18}

    // yas => Json formatına donustur
       //1.adım:
    Gson gson = new Gson();

       //2.adım Gson object formatını kullanrak Java object formatını Json formatına dönustur
    String JavadanJsona = gson.toJson(yas);
    System.out.println(JavadanJsona); // {"Tom Amca":65,"CAndy Teyze":88,"Ayse Kan":21,"Ali Can":15,"Veli Han":18}






}

}
