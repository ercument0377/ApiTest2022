package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get03 extends JsonPlaceHolderBaseUrl {
    /*
   Given
       https://jsonplaceholder.typicode.com/todos/23
   When
       Kullanici GET Request'i Url'e (APi) gonderir
   Then
       HTTP Status Code 200 olmali
   And
       Response formati "application/json" olmali
   And
       "title", "et itaque necessitatibus maxime molestiae qui quas velit" olmali,
   And
       "completed" is false
   And
       "userId"  2 olmali
*/

    @Test
    public void get03();
    //1.adım url set et
    spec.pathParams("ilk","todos","ikinci",23);

    //2.adım beklenen (expected)data set et

    //3.adım  get request yapılır ve get response alırız

   Response response = given().spec(spec).when().get("/{ilk}/{ikinci}");
    response.print();


    //4.adım Assertion yap
    //1.yol
    response.then().
    assertThat().
    statusCode(200).
    contentType(ContentType.JSON).
    body("Litle",equalTo(      )).
    body("completed".equalTo(false)).
    body("userId",equalTo(2));




}
