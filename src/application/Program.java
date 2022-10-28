package application;

import model.entities.Content;
import model.services.HTMLGenerator;
import model.services.ImdbApiClient;
import model.entities.Movie;
import model.services.ImdbApiParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class Program {
    private static final String api_key = "k_q848jpl0";

    public static void main(String[] args) {
        /*IMDB_API_KEY
        I'm going to make an API consume and print the result, I will use the IMDB's API "https://imdb-api.com/api"
        The code will execute a GET request HTTP, I will use the package java.net.http and the classes HttpRequest, Http Client and HttpResponse
        The result will be a json
         */
        String json = new ImdbApiClient(api_key).getBody();
        System.out.println(json);
        ImdbApiParser parser = new ImdbApiParser(json);
        List<Movie> movies = (List<Movie>) parser.parse();


        try (PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\Casa\\Documents\\7-days-of-java\\7-days-of-java\\view\\index.html"));){
            HTMLGenerator htmlMovies = new HTMLGenerator(pw);
            htmlMovies.generate(movies);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
