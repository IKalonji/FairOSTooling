/**
 * ResponseHandler class handles all aspects relating to the hashmap response.
 * Class is static and called as the return value of all requests.
 *
 * Methods return a hashmap with the result
 *
 * instantiation:
 *
 * Usage:
 *
 * ResponseHandler.handleResponse(responseObject)
 */
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.Reader;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.HashMap;

public class ResponseHandler {
    /**
     * Handles string responses.
     * @param response
     * @return
     * @throws ParseException
     */
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

    /**
     * Handles file responses
     * @param response
     * @return
     * @throws ParseException
     * @throws IOException
     */
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
