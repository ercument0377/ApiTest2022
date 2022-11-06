package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import base_url.HerOkuAppBaseUrl;
public class Get05 {
    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Dane&lastname=Dominguez
        When
            Kullanici GET requesti URL'e gonderir
        Then
            Status code : 200
      And
          Data'lar arasinda ismi (firstname) “Dane” ve soyismi (lastname) “Dominguez” olan biri olmali
     */
// Query Params spesifik parametreleri seçmek için kullanılır(orn: ?firstname=Dane&lastname=Dominguez
    // Path Params ise resource (kaynagı) kucultmek için daraltmak için kullanırız
    @Test
    public void get05(){

        spec.pathParam("")
    }
}

