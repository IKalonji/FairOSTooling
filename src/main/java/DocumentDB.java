/**
 * DocumentDB class handles all aspects relating to the management of the Document store.
 * Class should be instantiated with
 * the DFS cookie which is returned on signup for them to work.
 *
 * Methods return a hashmap with the result
 *
 * instantiation:
 *
 * DocumentDB docDB = new DocumentDB("cookie");
 *
 * Usage e.g:
 *
 * HashMap newDocDB = DocumentDB.createDB("podName", "tablename", "DocFieldIndex")
 */

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

    /**
     * DocumentDB is instantiated with cookie
     * @param dfs_cookie
     */
    public DocumentDB(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

    /**
     * Create new database
     * @param podName
     * @param tableName
     * @param si
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * List all database
     * @param podName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Open database
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Count Database
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Delete Database
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Find documents
     * @param podName
     * @param tableName
     * @param expr
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Load JSON from table
     * @param podName
     * @param tableName
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Index JSON
     * @param podName
     * @param tableName
     * @param file
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Insert document to DB
     * @param podName
     * @param tableName
     * @param doc
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Get Document
     * @param podName
     * @param tableName
     * @param id
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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

    /**
     * Delete document
     * @param podName
     * @param tableName
     * @param id
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
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
