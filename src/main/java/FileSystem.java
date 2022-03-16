/**
 * FileSystem class handles all aspects relating to the management of the File System.
 * Class should be instantiated with
 * the DFS cookie which is returned on signup for them to work.
 *
 * Methods return a hashmap with the result
 *
 * instantiation:
 *
 * FileSystem fs = new FileSystem("cookie");
 *
 * Usage:
 *
 * HashMap newDir = fs.fsMakeDirectory("podName", "dirPath")
 */


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileSystem {

    private String dfs_cookie;
    private HttpClient client;
    private HttpRequest.Builder requestBuilder;

    /**
     * FileSystem is instantiated with cookie
     * @param dfs_cookie
     */
    public FileSystem(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

    /**
     * Create new directory in the pod
     * @param podName
     * @param dirPath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsMakeDirectory(String podName, String dirPath)
        throws IOException, InterruptedException, ParseException {

        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/mkdir";

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
     * Remove a directory
     * @param podName
     * @param dirPath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsRemoveDirectory(String podName, String dirPath)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/rmdir";

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
     * Directory list
     * @param podName
     * @param dirPath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsListDirectory(String podName, String dirPath)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/ls?pod_name="+podName+"?dir_path="+dirPath;

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
     * Directory status
     * @param podName
     * @param dirPath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsStatDirectory(String podName, String dirPath)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/stat?pod_name="+podName+"?dir_path="+dirPath;

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
     * Check if directory is present in file system
     * @param podName
     * @param dirPath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsIsDirectoryPresent(String podName, String dirPath)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/present?pod_name="+podName+"?dir_path="+dirPath;

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
     * Upload file
     * @param podName
     * @param dirName
     * @param blockSize
     * @param filePath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsUploadFile(String podName, String dirName, String blockSize, Path filePath)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("pod_dir", dirName);
        body.put("block_size", blockSize);
        String uri="http://localhost:9090/v1/file/upload";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .header("fairOS-dfs-Compression", "gzip")
                .setHeader("Content-Type","multipart/form-data")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString())).POST(HttpRequest.BodyPublishers.ofFile(filePath))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofString());

        return ResponseHandler.handleResponse(response);
    }

    /**
     * Download a file
     * @param podName
     * @param dirPath
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsDownloadFile(String podName, String dirPath)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/download";

        HttpRequest request = this.requestBuilder
                .uri(URI.create(uri))
                .header("Cookie", this.dfs_cookie)
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        Path localFile = Paths.get("new_file");

        HttpResponse<Path> response = client.send(request,
                HttpResponse
                .BodyHandlers
                .ofFile(localFile));

        return ResponseHandler.handleFileResponse(response);
    }

    /**
     * Share a file with an address
     * @param podName
     * @param dirPath
     * @param receiverAddr
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsShareFile(String podName, String dirPath, String receiverAddr)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        body.put("dest_user", receiverAddr);
        String uri="http://localhost:9090/v1/dir/share";

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
     * Receive a shared file
     * @param podName
     * @param dirPath
     * @param sharingRef
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws ParseException
     */
    public HashMap<String, String> fsReceiveFile(String podName, String dirPath, String sharingRef)
        throws IOException, InterruptedException, ParseException {
        JSONObject body = new JSONObject();
        body.put("pod_name", podName);
        body.put("dir_path", dirPath);
        String uri="http://localhost:9090/v1/dir/share/receive?pod_name="+
                podName+"?sharing_ref="+sharingRef+"?dir_path="+dirPath;

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
