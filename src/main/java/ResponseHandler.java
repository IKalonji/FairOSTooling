import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.HashMap;

public class ResponseHandler {

    public static HashMap<String, String> handleResponse(HttpResponse<String> response) throws ParseException {
        String cookie = response.headers().firstValue("Set-Cookie").toString();
        JSONParser parser = new JSONParser();
        HashMap<String, String> returnMap = new HashMap<>();
        JSONObject responseBody = (JSONObject) parser.parse(response.body());
        returnMap.put("cookie", cookie);
        responseBody.forEach((key,value) -> {
                    returnMap.put(key.toString(),value.toString());
                }
        );
        return returnMap;
    }

    public static HashMap<String, String> handleFileResponse(HttpResponse<Path> response) throws ParseException, IOException {
        String cookie = response.headers().firstValue("Set-Cookie").toString();
        JSONParser parser = new JSONParser();
        HashMap<String, String> returnMap = new HashMap<>();
        JSONObject responseBody = (JSONObject) parser.parse((Reader) response.body());
        returnMap.put("cookie", cookie);
        responseBody.forEach((key,value) -> {
                    returnMap.put(key.toString(),value.toString());
                }
        );
        return returnMap;
    }
}
