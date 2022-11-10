package class01_get_http_request_method;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
       Given
            https://jsonplaceholder.typicode.com/todos
        When
             URl'e GET Request gonder
         Then
             Status code  200 olmali
             1) 190'dan buyuk Id'leri yazdir
               190'dan buyuk 10 Id'nin oldugunu Assert et
             2) userIds 5'ten kucuk olanlari yazdir
               UserId'si 5'ten kucuk en buyuk degerin 4 oldugunu assert et
             3) id'si 5'ten kucuk olan butun title'lari yazdir
               "delectus aut autem"un 5'ten kucuk title'lardan bir tanesi oldugunu assert et.
 */
    //   1.adım  url yi set et
    //   2. adım beklenen datayı set et
    //   3.adım: get request gonder ve get response al
    //   4.adım assertion  yap

    @Test

    public void get07(){
        //   1.adım  url yi set et
        spec.pathParam("first","todos");

        //   2. adım beklenen datayı set et


        //   3.adım: get request gonder ve get response al
      Response response  = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //   4.adım assertion  yap
        response.then().assertThat().statusCode(200);
        // 1-1   190 dan buyuk olanları yazdır
        JsonPath json = response.jsonPath();

       List<Integer> idList = json.getList("findAll{it.id>190}.id"); // groovy language
        /*
        it -> içinde bulunduğumuz Json datadaki id yi temsil ediyor
        this.name = içinde bulunduğumuz sınıfı değişkeni (name) temsil eder

         */

        System.out.println(idList); // (191,192,193,194,195,196,197,198,199,200)


        // 1-2  190'dan buyuk 10 Id'nin oldugunu Assert et
        assertEquals("id listesi beklenen ile uyusmuyor",10,idList.size());

        //2) userIds 5'ten kucuk olanlari yazdir

        List<Integer> userIdList = json.getList("findAll{it.userId<5}.userId");
        System.out.println(userIdList);

        //2-2  UserId'si 5'ten kucuk en buyuk degerin 4 oldugunu assert et

        Collections.sort(userIdList);
        assertEquals((Integer) 4,userIdList.get(userIdList.size()-1));

        //   3-1) id'si 5'ten kucuk olan butun title'lari yazdir

        List<String> titleList =  json.getList("findAll{it.userId<5}.title");
        System.out.println(titleList);

        // 3-2 "delectus aut autem"un 5'ten kucuk title'lardan bir tanesi oldugunu assert et.

             //1.yol
        assertTrue(titleList.contains("delectus aut autem"));

            //2.yol
        assertTrue(titleList.stream().anyMatch(t->t.equals("delectus aut autem")));


    }



}
