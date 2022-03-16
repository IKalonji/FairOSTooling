/**
 * Pod class handles all aspects relating to the management of Pods.
 * Class should be instantiated with
 * the DFS cookie which is returned on signup for them to work.
 *
 * Methods return a hashmap with the result
 *
 * instantiation:
 *
 * Pod pod = new Pod("cookie");
 *
 * Usage:
 *
 * HashMap newpod = pop.podNew("podName", "strongpassword")
 */

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class Pod {

    private String dfs_cookie;
    private HttpClient client;
    private HttpRequest.Builder requestBuilder;

    /**
     * Pod is instantiated with cookie
     * @param dfs_cookie
     */
    public Pod(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

    /**
     * Create a new pod
     * @param podName
     * @param password
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podNew(String podName, String password)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("password", password);
        String uri="http://localhost:9090/v1/pod/new";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Open pod
     * @param podName
     * @param password
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podOpen(String podName, String password)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("password", password);
        String uri="http://localhost:9090/v1/pod/open";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Close the pod
     * @param podName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podClose(String podName)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        String uri="http://localhost:9090/v1/pod/close";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Sync the pod
     * @param podName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podSync(String podName)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        String uri="http://localhost:9090/v1/pod/sync";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Share pod
     * @param podName
     * @param password
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podShare(String podName, String password)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("password", password);
        String uri="http://localhost:9090/v1/pod/share";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Delete pod
     * @param podName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podDelete(String podName)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        String uri="http://localhost:9090/v1/pod/delete";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * List pods
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podList()
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        String uri="http://localhost:9090/v1/pod/ls";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Check if pod is present
     * @param podName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> podIsPresent(String podName)
        throws IOException, InterruptedException, ParseException {

        String uri="http://localhost:9090/v1/pod/stat?pod_name="+podName;
        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                        .BodyHandlers
                        .ofString());

        return ResponseHandler.handleResponse(response);
    }
}
