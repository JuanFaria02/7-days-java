package model.entities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Movie {
    private String title;
    private String urlImage;
    private String rating;
    private String year;

    public Movie(String title, String urlImage, String rating, String year) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }

    public static String[] parseJsonMovies(String json) {

        String[] moviesArray = json.substring(11).split("\\},\\{");
        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, 370);

        return moviesArray;
    }

    public static List<String> parseTitles(String[] moviesArray) {
        return parseAttribute(moviesArray, 3);
    }
    public static List<String> parseUrlImage(String[] moviesArray) {
        return parseAttribute(moviesArray, 5);
    }

    public static List<String> parseYears(String[] moviesArray) {
        return parseAttribute(moviesArray, 4);
    }

    public static List<String> parseRating(String[] moviesArray) {
        return parseAttribute(moviesArray, 7);
    }


    private static List<String> parseAttribute(String[] moviesArray, int pos) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[pos])
                .map(e -> e.split(":\"")[1])
                .map(e-> e.replaceAll("\"", ""))
                .collect(Collectors.toList());
    }
    @Override
    public String toString() {
        return "Movie {" +
                "title: " + title  +
                ", urlImage: " + urlImage +
                ", rating: " + rating +
                ", year :" + year +
                '}';
    }
}
