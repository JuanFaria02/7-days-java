package model.entities;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ImdbApiClient {
    private String json;
    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/";

    public ImdbApiClient() {
    }

    public ImdbApiClient(String json) {
        this.json = json;
    }


    public static String getBody(String api_key) {
        if (api_key == null || api_key.isEmpty()) {
            throw new DomainException("Api key is empty");
        }
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL + api_key)).GET().build();
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

    public List<Movie> parse() {

        String[] moviesArray = Movie.parseJsonMovies(json);
        List<String> titles = Movie.parseTitles(moviesArray);
        List<String> urlImages = Movie.parseUrlImage(moviesArray);
        List<String> years = Movie.parseYears(moviesArray);
        List<String> rating = Movie.parseRating(moviesArray);


        List<Movie> movieList= new ArrayList<>();
        for (int i = 0; i < moviesArray.length; i++) {
            movieList.add(new Movie(titles.get(i), urlImages.get(i), rating.get(i), years.get(i)));
        }

        return movieList;
    }
}
