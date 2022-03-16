/**
 * KeyValueDB class handles all aspects relating to the management of the Key-Value store.
 * Class should be instantiated with
 * the DFS cookie which is returned on signup for them to work.
 *
 * Methods return a hashmap with the result
 *
 * instantiation:
 *
 * KeyValueDB kvDB = new KeyValueDB("cookie");
 *
 * Usage:
 *
 * HashMap newKvStore = kvDB.newTable("podName", "tablename", "indexType")
 */

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

    /**
     * KeyValueDB is instantiated with cookie
     * @param dfs_cookie
     */
    public KeyValueDB(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

    /**
     * Create new table
     * @param podName
     * @param table_name
     * @param indexType
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * List all tables
     * @param podName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Open the required table
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Count tables
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Delete the table
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Put the key value pair into the table
     * @param podName
     * @param table_name
     * @param key
     * @param value
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Get value form teh key
     * @param podName
     * @param table_name
     * @param key
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Delete value based on the key
     * @param podName
     * @param table_name
     * @param key
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Seek the required key
     * @param podName
     * @param table_name
     * @param start
     * @param end
     * @param limit
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Get the next value in the table
     * @param podName
     * @param table_name
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Load CSV from the table
     * @param podName
     * @param table_name
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Check if the said key is present
     * @param podName
     * @param table_name
     * @param key
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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
