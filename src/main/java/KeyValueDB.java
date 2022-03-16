import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class KeyValueDB {

    String dfs_cookie;
    HttpClient client;
    HttpRequest.Builder requestBuilder;

    public KeyValueDB(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

    public HashMap<String, String> newTable(String podName, String table_name, String indexType)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", table_name);
        body.put("indexType", indexType);

        String uri = "http://localhost:9090/v1/kv/new";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> listTables(String podName)
        throws IOException, InterruptedException, ParseException {

            String uri = "http://localhost:9090/v1/kv/ls?pod_name="+podName;
            HttpRequest request = requestBuilder
                    .uri(URI.create(uri))
                    .header("Cookie", this.dfs_cookie)
                    .GET()
                    .build();

            HttpResponse<String> response = this.client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> openTable(String podName, String tableName)
            throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);

        String uri = "http://localhost:9090/v1/kv/open";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> countTable(String podName, String tableName)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);

        String uri = "http://localhost:9090/v1/kv/count";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> deleteTable(String podName, String tableName)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", tableName);

        String uri = "http://localhost:9090/v1/kv/delete";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> putKeyValue(String podName, String table_name, String key, String value)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", table_name);
        body.put("key", key);
        body.put("value", value);

        String uri = "http://localhost:9090/v1/kv/put";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> getValue(String podName, String table_name, String key)
        throws IOException, InterruptedException, ParseException {

        String uri = "http://localhost:9090/v1/kv/get?pod_name="+
                podName+"?table_name="+table_name+"?key="+key;
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> deleteValue(String podName, String table_name, String key)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", table_name);
        body.put("key", key);

        String uri = "http://localhost:9090/v1/kv/entry/del";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> seekKey(String podName, String table_name, String start, String end, int limit)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", table_name);
        body.put("start", start);
        body.put("end", end);
        body.put("limit", limit);

        String uri = "http://localhost:9090/v1/kv/seek";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> getNext(String podName, String table_name)
        throws IOException, InterruptedException, ParseException {

        String uri = "http://localhost:9090/v1/kv/seek/next"+
                "?pod_name="+podName+"?table_name="+table_name;
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> loadCSV(String podName, String table_name)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("table_name", table_name);

        String uri = "http://localhost:9090/v1/kv/loadcsv";
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

    public HashMap<String, String> isKeyPresent(String podName, String table_name, String key)
            throws IOException, InterruptedException, ParseException {

        String uri = "http://localhost:9090/v1/kv/present"+
                "?pod_name="+podName+"?table_name="+table_name+"?key="+key;
        HttpRequest request = requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        return ResponseHandler.handleResponse(response);
    }

}
