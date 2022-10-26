package application;

import model.entities.HTMLGenerator;
import model.entities.Movie;
import model.exceptions.DomainException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class Program {
    private static final String api_key = "IMDB_API_KEY";
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

            String[] moviesArray = Movie.parseJsonMovies(json);

            List<String> titles = Movie.parseTitles(moviesArray);
            List<String> urlImages = Movie.parseUrlImage(moviesArray);
            List<String> years = Movie.parseYears(moviesArray);
            List<String> rating = Movie.parseRating(moviesArray);


            List<Movie> movieList= new ArrayList<>();
            for (int i = 0; i < moviesArray.length; i++) {
                movieList.add(new Movie(titles.get(i), urlImages.get(i), rating.get(i), years.get(i)));
            }
            movieList.forEach(System.out::println);


            PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\Casa\\Documents\\7-days-of-java\\7-days-of-java\\view\\index.html"));
            HTMLGenerator htmlMovies = new HTMLGenerator(pw);
            htmlMovies.generate(movieList);
            pw.close();
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

}
