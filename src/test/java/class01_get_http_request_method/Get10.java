package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.GoRestApiBaseUrl;
import class06_pojos.GoRestDataPojo;
import class06_pojos.GoRestPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Get10 extends GoRestApiBaseUrl {

    /*
Given
    https://gorest.co.in/public/v1/users/2619
When
    Url'e Get Request gonder
Then
    Status Code 200 olmali
And
    Response body should be like
        {
            " "meta": null,
            "data": {
    "id": 2619,
    "name": "Bhargavi Menon",
    "email": "dhruv_pilla@rohan.com",
    "gender": "female",
    "status": "inactive"
    }
        }
*/
    @Test
    public void get10() {
        //1.adım: url set et

        spec.pathParams("first", "users", "second", 2619);

        //2.adım: beklenen(expected) datayı set et
        GoRestDataPojo dataPojo = new GoRestDataPojo("Bhargavi Menon","dhruv_pilla@rohan.com","female","inactive");
        GoRestPojo expectedDataPojo = new GoRestPojo(null, dataPojo);
        System.out.println(expectedDataPojo);

        //3.adım : request gonder response al
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestPojo actualDataPojo =   JsonUtil.jsoniJavayaCevir(response.asString(), GoRestPojo.class);
        System.out.println(actualDataPojo);

        //4.adım assertion yap

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getMeta(), actualDataPojo.getMeta());
        assertEquals(expectedDataPojo.getData().getName(), actualDataPojo.getData().getName());
        assertEquals(expectedDataPojo.getData().getGender(), actualDataPojo.getData().getGender());
        assertEquals(expectedDataPojo.getData().getStatus(), actualDataPojo.getData().getStatus());








    }
}
