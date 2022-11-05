package class01_get_http_request_method;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
// Jumıt bir framework tur (cercevedir) ve test yapmamızı kolaylaştırır

public class Get01 {
    /*
    API testing de Gherkin dilini kullanırız
    Gherkin GHERKIN dilinde bazı anahtar kavramlar kullanacağız: Given, when, Then, And
    (size bir kitap verildi(given), bu kitabı okuduğumuzda (when), başarılı olursunuz (then)
    Given :  on koşulları bidirir.(sartlar, başlangıc)
    When : hareketleri (yapacağınız işi) bildirmek için kullanılır
    Then : sonuc için kullanılır
    And :  çoklu 'given, when , then' için kullanırız.
    */


    /*    Task --->     test case olusturma

   Given
       https://restful-booker.herokuapp.com/booking/3
   When
       Kullanici GET Request'i Url'e (APi) gonderir
       User send a GET Request to the url (API)
   Then
        HTTP Statu Kodu 200 olmali
       HTTP Status Code should be 200
   And
       Content Type'i JSON olmali
       Content Type should be JSON
   And
       Statu Line(duzeyi) HTTP/1.1 200 OK olmali
       Status Line should be HTTP/1.1 200 OK
*/
@Test
    public void get01(){
    //1.adım : set the url
    String url = "https://restful-booker.herokuapp.com/booking/3";

    //2. adım: beklenen datayı (expected data) set et.

    //3. adım: Get request gonderilir ve Get Respanse alınır.

   Response response = given().when().get(url);
   response.prettyPrint();



   //4.adım: assertion yap

    /*
    eger assertion da çoklıu hata varsa kodun calışması ilk hata durur sonraki kodlar
    calısmaz
    yeni ikinci ucuncu gibi hatalar hakkında bilgi alamayız. bu aslında ıyı birşey değildir.
    bu nedenle bu tip Assertiona "hard assertion" denir
    diğer tip assertion ise "Soft Assertion" dur
    Soft assertion (verification)da butun kodlar calısır/kosar ve butun assertionlar icin bir hata raporu alirsiniz
     */

    response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

    System.out.println("Status code : "+ response.getStatusCode());
    System.out.println("Status code : "+ response.getContentType());
    System.out.println("Time : "+ response.getStatusLine());

    System.out.println("Headers : \n "+ response.getHeaders());
    System.out.println("Via :  "+ response.getHeader("Via"));

}



}
