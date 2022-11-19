package Utils;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {

    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
    }

    //1.method : Json data yı Java Object cevirmek için kullanılır --> De-serization

    public static <T> T jsoniJavayaCevir(String json, Class<T> cls) {
        T javaSonuc = null;

        try {
            javaSonuc = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.out.println("json formatını java object formatına dousturemedi" + e.getMessage());
        }
        return javaSonuc;
    }
    //2. method: java object json data ya cevirmek için kullanırız --> Serilazitaion
    public static String javayiJsonaCevir(Object obje){
       String  jsonSonuc = null;

       try {
           jsonSonuc = mapper.writeValueAsString(obje);
       } catch (IOException e) {
           System.out.println(" java object json  formatına dousturemedi" + e.getMessage());
       }
       return jsonSonuc;

    }

}
