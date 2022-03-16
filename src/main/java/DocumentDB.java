import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class DocumentDB {

    private String dfs_cookie;
    private HttpClient client;
    private HttpRequest.Builder requestBuilder;

    public DocumentDB(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }


    public HashMap<String, String> createDB(String podName, String tableName, String si)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);
        body.put("si", si);

        String uri = "http://localhost:9090/v1/doc/new";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> listAllDB(String podName)
            throws IOException, InterruptedException, ParseException {

        String uri = "http://localhost:9090/v1/doc/ls?pod_name="+podName;
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> openDB(String podName, String tableName)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);

        String uri = "http://localhost:9090/v1/doc/open";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> countDB(String podName, String tableName)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);

        String uri = "http://localhost:9090/v1/doc/count";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> deleteDB(String podName, String tableName)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);

        String uri = "http://localhost:9090/v1/doc/delete";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> findDocuments(String podName, String tableName, String expr)
            throws IOException, InterruptedException, ParseException {

        String uri = "http://localhost:9090/v1/doc/find"+
                "?pod_name="+podName+
                "?table_name"+tableName+
                "?expr"+expr;
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> loadJson(String podName, String tableName)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);


        String uri = "http://localhost:9090/v1/doc/loadjson";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> indexJson(String podName, String tableName, String file)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);
        body.put("file", file);


        String uri = "http://localhost:9090/v1/doc/indexjson";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> putDocument(String podName, String tableName, String doc)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);
        body.put("doc", doc);

        String uri = "http://localhost:9090/v1/doc/entry/put";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> getDocument(String podName, String tableName, String id)
            throws IOException, InterruptedException, ParseException {

        String uri = "http://localhost:9090/v1/doc/entry/get"+
                "?pod_name="+podName+
                "?table_name="+tableName+
                "?id="+id;
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> deleteDocument(String podName, String tableName, String id)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);
        body.put("id", id);

        String uri = "http://localhost:9090/v1/doc/entry/del";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

}
