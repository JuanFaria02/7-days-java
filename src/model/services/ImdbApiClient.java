package model.services;
import model.entities.DomainException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {
    private String apiKey;
    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/";


    public ImdbApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBody() {
        if (getApiKey() == null || getApiKey().isEmpty()) {
            throw new DomainException("Api key is empty");
        }
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL + getApiKey())).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = (String) response.body();

            return json;
        }
        catch (URISyntaxException e) {
            return e.getMessage();
        }
        catch (IOException e) {
            return e.getMessage();
        }
        catch (InterruptedException e) {
            return e.getMessage();
        }
    }
}
