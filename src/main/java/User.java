/**
 * User class handles all aspects relating to the user.
 * Class should be instantiated and user is to be signed up
 * or logged in thereafter. All other classes depend on
 * the DFS cookie which is returned on signup for them to work.
 *
 * Methods return a hashmap with the result
 *
 * instantiation:
 *
 * User user = new User();
 *
 * Usage:
 *
 * HashMap signedIn = user.signupUser("username", "strongpassword", "mnemonic")
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class User {

    HttpClient client;
    HttpRequest.Builder requestBuilder;

    public User(){
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

    /**
     * Signup user with mnemonic
     * @param username
     * @param password
     * @param mnemonic
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> signupUserWithMnemonic(String username, String password, String mnemonic)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        body.put("mnemonic", mnemonic);
        String uri="http://localhost:9090/v1/user/signup";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Signup user without mnemonic
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> signupUser(String username, String password)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        String uri="http://localhost:9090/v1/user/signup";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());
        return ResponseHandler.handleResponse(response);
    }

    /**
     * Login user
     * @param username
     * @param password
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> loginUser(String username, String password)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        String uri="http://localhost:9090/v1/user/login";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Import user with eth address
     * @param username
     * @param password
     * @param address
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> importUserWithAddress(String username, String password, String address)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        body.put("address", address);
        String uri="http://localhost:9090/v1/user/import";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Import user with mnemonic
     * @param username
     * @param password
     * @param mnemonic
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> importUserWithMnemonic(String username, String password, String mnemonic)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("username", username);
        body.put("password", password);
        body.put("address", mnemonic);
        String uri="http://localhost:9090/v1/user/import";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Check if user is signed in
     * @param username
     * @param dfs_cookie
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> isUserLoggedIn(String username, String dfs_cookie)
        throws IOException, InterruptedException, ParseException {

        String uri="http://localhost:9090/v1/user/isloggedin?user_name="+username;

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Logout the current user
     * @param dfs_cookie
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> logoutUser(String dfs_cookie)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("cookie", dfs_cookie);
        String uri="http://localhost:9090/v1/user/logout";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Export the user
     * @param dfs_cookie
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> exportUser(String dfs_cookie)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("cookie", dfs_cookie);
        String uri="http://localhost:9090/v1/user/export";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Delete user
     * @param password
     * @param dfs_cookie
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> deleteUser(String password, String dfs_cookie)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("password", password);
        String uri="http://localhost:9090/v1/user/delete";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", dfs_cookie)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * User Stats
     * @param dfs_cookie
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> userStat(String dfs_cookie)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("cookie", dfs_cookie);
        String uri="http://localhost:9090/v1/user/stat";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }

}
