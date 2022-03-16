/*
Directory APIs

File APIs
POST -F -H "fairOS-dfs-Compression: snappy/gzip" 'pod_dir=\<dir_with_path>' -F 'block_size=\<in_Mb>' -F 'files=@\<filename1>' -F 'files=@\<filename2>' http://localhost:9090/v1/file/upload (compression header optional)
POST -F 'file=\<file_path>' http://localhost:9090/v1/file/download
POST -F 'file=\<file_path>' -F 'to=\<destination_user_address>' http://localhost:9090/v1/file/share
POST -F 'ref=\<sharing_reference>' -F 'dir=\<pod_dir_to_store_file>' http://localhost:9090/v1/file/share/receive
POST -F 'ref=\<sharing_reference>' http://localhost:9090/v1/file/share/receiveinfo
GET -F 'file=\<file_path>' http://localhost:9090/v1/file/stat
DELETE -F 'file=\<file_path>' http://localhost:9090/v1/file/delete
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

    public FileSystem(String dfs_cookie){
        this.dfs_cookie = dfs_cookie;
        this.client = HttpClient.newHttpClient();
        this.requestBuilder = HttpRequest.newBuilder();
    }

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
