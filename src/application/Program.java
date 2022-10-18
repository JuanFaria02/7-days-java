package application;

import exceptions.DomainException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    private static final String api_key = "k_q848jpl0";
    private static final String URL = "https://imdb-api.com/en/API/Top250Movies/";

    public static void main(String[] args) {
        /*
        I'm going to make an API consume and print the result, I will use the IMDB's API "https://imdb-api.com/api"
        The code will execute a GET request HTTP, I will use the package java.net.http and the classes HttpRequest, Http Client and HttpResponse
        The result will be a json
         */
        try {
            if (api_key == null || api_key.isEmpty()) {
                throw new DomainException("Api key is empty");
            }

            HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL + api_key)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = (String) response.body();
            System.out.println(json);

            String[] moviesArray = parseJsonMovies(json);

            List<String> titles = parseTitles(moviesArray);
            titles.forEach(System.out::println);

            List<String> urlImages = parseUrlImage(moviesArray);
            urlImages.forEach(System.out::println);

            List<String> years = parseYears(moviesArray);
            years.forEach(System.out::println);

            List<String> rating = parseRating(moviesArray);
            rating.forEach(System.out::println);
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
        catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    private static  String[] parseJsonMovies(String json) {

        String[] moviesArray = json.substring(11).split("\\},\\{");
        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, 370);
        for (int i = 0; i < moviesArray.length; i++) {
            System.out.println(moviesArray[i]);
        }
        return moviesArray;
    }

    private static List<String> parseTitles(String[] moviesArray) {
        return parseAttribute(moviesArray, 3);
    }
    private static List<String> parseUrlImage(String[] moviesArray) {
        return parseAttribute(moviesArray, 5);
    }

    private static List<String> parseYears(String[] moviesArray) {
        return parseAttribute(moviesArray, 4);
    }

    private static List<String> parseRating(String[] moviesArray) {
        return parseAttribute(moviesArray, 7);
    }


    private static List<String> parseAttribute(String[] moviesArray, int pos) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e-> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }
}
