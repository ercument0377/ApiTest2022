package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> expectedDataSetup() {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        return expectedData;

        // System.out.println(expectedDataSetup());
    }

    public Map<String, Object> expectedDataSetupTumKey(Integer userId, String title, Boolean completed ) {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);
        return expectedData;
    }
    public Map<String, Object> expectedDataSetupKismiKey(Integer userId, String title, Boolean completed ) {

        Map<String, Object> expectedData = new HashMap<>();
        if(userId==null){
            expectedData.put("title", title);

        }else if (completed==null)

            expectedData.put("title", title);

        return expectedData;
    }





}