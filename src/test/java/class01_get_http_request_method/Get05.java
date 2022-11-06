package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

import base_url.HerOkuAppBaseUrl;
public class Get05 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Dane&lastname=Dominguez
        When
            Kullanici GET requesti URL'e gonderir
        Then
            Status code : 200
      And
          Data'lar arasinda ismi (firstname) “Dane” ve soyismi (lastname) “Dominguez” olan biri olmali

     //   1.adım  url yi set et
     //   2. adım beklenen datayı set et
     //   3.adım: get request gonder ve get response al
     //   4.adım assertion  yap

     */
    // Query Params spesifik parametreleri seçmek için kullanılır(orn: ?firstname=Dane&lastname=Dominguez
    // Path Params ise resource (kaynagı) kucultmek için daraltmak için kullanırız ( /booking  kısmıdır)
    @Test
    public void get05(){
        //   1.adım  url yi set et
        spec.pathParam("first","booking").queryParams("firstname","Dane","lastname","Dominguez");



        //   2. adım beklenen datayı set et


        //   3.adım: get request gonder ve get response al
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //   4.adım assertion  yap
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).body("bookingid",hasItem(38));

        response.then().assertThat().statusCode(200);
        assertTrue(response.asString().contains("booking"));










    }
}

