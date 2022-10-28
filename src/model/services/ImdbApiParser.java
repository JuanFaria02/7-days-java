package model.services;
import model.entities.Content;
import model.entities.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImdbApiParser implements  JsonParser{
    private static String json;

    public ImdbApiParser(String json) {
        this.json = json;
    }

    public static String getJson() {
        return json;
    }


    public List<? extends Content> parse() {

        String[] moviesArray = parseJsonMovies(getJson());
        List<String> titles = parseTitles(moviesArray);
        List<String> urlImages = parseUrlImage(moviesArray);
        List<String> years = parseYears(moviesArray);
        List<String> rating = parseRating(moviesArray);

        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < moviesArray.length; i++)
            movieList.add(new Movie(titles.get(i), urlImages.get(i), rating.get(i), years.get(i)));
        return movieList;
    }
    private String[] parseJsonMovies(String json) {

        String[] moviesArray = json.substring(11).split("\\},\\{");
        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, 370);

        return moviesArray;
    }
    private List<String> parseTitles(String[] moviesArray) {
        return parseAttribute(moviesArray, 3);
    }

    private List<String> parseUrlImage(String[] moviesArray) {
        return parseAttribute(moviesArray, 5);
    }

    private List<String> parseYears(String[] moviesArray) {
        return parseAttribute(moviesArray, 4);
    }

    private List<String> parseRating(String[] moviesArray) {
        return parseAttribute(moviesArray, 7);
    }


    private List<String> parseAttribute(String[] moviesArray, int pos) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e-> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }

}
