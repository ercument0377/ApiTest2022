package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
    /*
    API testte en buyuk zorluk yada handikap data type leridir
    1- java object ( non-primitive), Maps ve Primitive data  typeleri kullanır
        API ise XML, Json gibi formatları kullanır.

        Java  ve API farklı data type leri kullanır dolayısıyla bunların birbirleriyle iletişim kurmaları için
        birbirleriyle anlayacak formata getirilmesi lazım. Aksi halde iletişim olmaz.

        --birbirlerini anlamaları için 2 seçenek var
        1- Data type i Json formatında Java object formatına ceviririz --> De-Serialization
        ii- Data type i Java object'ten Json formatına ceviririz --> Serialization

        De-Serialization ve Serialization  icin 2 tane secenek vardır
        a) Gson  -- Google olusturur
        b) object Mapper -daha populer

        Json --> JAvaScript object notation
        Gson --> :GoogleScript object notation


     */
/*
    Given
        https://jsonplaceholder.typicode.com/todos/2
    When
            Url'e GET Request gonder
    Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
         {
            "userId": 1,
            "id": 2,
            "title": "quis ut nam facilis et officia qui",
            "completed": false
         }
         //   1.adım  url yi set et
    //   2. adım beklenen datayı set et
    //   3.adım: get request gonder ve get response al
    //   4.adım assertion  yap
 */

    @Test
    public void get08() {
        //   1.adım  url yi set et
        spec.pathParams("first","todos","second",2);

        //   2. adım beklenen datayı set et
       Map<String,Object> expectedData = new HashMap<>();  // HashMap<>() kullandık cünkü Maplarde en hızlısı HasMap<>() tir
        expectedData.put("userId",1);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        expectedData.put("Status Code",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        System.out.println(expectedData); // {Server=cloudflare, Status Code=200, completed=false, title=quis ut nam facilis et officia qui, userId=1, Via=1.1 vegur}




        //   3.adım: get request gonder ve get response al
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Gson kullanarak, API den gelen Json datayı Java object formatına ceviriyoruz

        Map<String, Object> actualData = response.as(HashMap.class); // de-serilazation denilir
        System.out.println(actualData);

        //   4.adım assertion  yap
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("Status Code"),response.getStatusCode());
        assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        assertEquals(expectedData.get("Server"),response.getHeader("Server"));


    }
}