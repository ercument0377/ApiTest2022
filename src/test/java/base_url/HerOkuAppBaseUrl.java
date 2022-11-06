package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppBaseUrl {
    // base url başka bir sınıfta oluştururum ve ıhtıyacım oldugunda gider kullanırım


    // RequestSpecification  data tipinde obje oluşturulur
    protected RequestSpecification spec;

    // Eger methodun önünde @Before anotation kullanırsanız, bu method her bir test methoddan once calısır
    // @Before anotation ne zaman kullanırsınız?
    // Cevap: eger ben bir methodun herbir test methodundan once calısmasını ıstıyorsam @Before anotation kullanırım

    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

    }


}
