package application;

import model.entities.HTMLGenerator;
import model.entities.ImdbApiClient;
import model.entities.Movie;
import model.entities.DomainException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class Program {
    private static final String api_key = "IMDB_API_KEY";

    public static void main(String[] args) {
        /*IMDB_API_KEY
        I'm going to make an API consume and print the result, I will use the IMDB's API "https://imdb-api.com/api"
        The code will execute a GET request HTTP, I will use the package java.net.http and the classes HttpRequest, Http Client and HttpResponse
        The result will be a json
         */
        String json = ImdbApiClient.getBody(api_key);
        System.out.println(json);

        List<Movie> movies = new ImdbApiClient(json).parse();

        try (PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\Casa\\Documents\\7-days-of-java\\7-days-of-java\\view\\index.html"));){
            HTMLGenerator htmlMovies = new HTMLGenerator(pw);
            htmlMovies.generate(movies);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
