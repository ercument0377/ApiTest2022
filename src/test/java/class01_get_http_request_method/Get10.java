package class01_get_http_request_method;

import Utils.JsonUtil;
import base_url.GoRestApiBaseUrl;
import class06_pojos.GoRestDataPojo;
import class06_pojos.GoRestPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
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
    "name": "Dhruv Pilla",
    "email": "dhruv_pilla@rohan.com",
    "gender": "male",
    "status": "active"
    }
        }
*/
    @Test
    public void get10() {
        //1.adım: url set et

        spec.pathParams("first", "users", "second", 2619);

        //2.adım: beklenen(expected) datayı set et
        GoRestDataPojo dataPojo = new GoRestDataPojo("Dhruv Pilla","dhruv_pilla@rohan.com","male","active");
        GoRestPojo expectedDataPojo = new GoRestPojo(null, dataPojo);
        System.out.println(expectedDataPojo);

        //3.adım : request gonder response al
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestPojo actualDataPojo =   JsonUtil.jsoniJavayaCevir(response.asString(), GoRestPojo.class);
        System.out.println(actualDataPojo);

        //4.adım assertion yap

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataPojo.getData().getName(), actualDataPojo.getData().getName());
        assertEquals(expectedDataPojo.get);








    }
}
