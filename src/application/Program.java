package application;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Program {
    private static final String api_key = System.getenv("IMDB_API_KEY");
    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/";

    public static void main(String[] args) {
        /*
        I'm going to make an API consume and print the result, I will use the IMDB's API "https://imdb-api.com/api"
        The code will execute a GET request HTTP, I will use the package java.net.http and the classes HttpRequest, Http Client and HttpResponse
        The result will be a json
         */
        try {
            if (api_key == null || api_key.isEmpty()) {
                throw new IllegalStateException("Api key is empty");
            }
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL + api_key)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        }
        catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
