package model.services;

import model.entities.Movie;

import java.io.IOException;
import java.io.Writer;
import java.util.List;


public class HTMLGenerator {

    private String head = """
            <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" "integrity= "sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm "crossorigin= "anonymous">
            </head>                                                                                                      
            """;

    private Writer outputWriter;

    public HTMLGenerator(Writer outputWriter) {
        this.outputWriter = outputWriter;
    }

    public void generate(List<Movie> movies) {
        try {
            outputWriter.write(head);
           outputWriter.write("<body><h1>Top 250 movies</h1>");
            for (Movie m: movies) {
                outputWriter.write("<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">" +
                                "<h4 class=\"card-header\">" + m.getTitle() + "</h4>" +
                        "<div class=\"card-body\">" +
                        "<img class=\"card-img\" src=\"" + m.getUrlImage() + "\" alt=\"Movie Image\">" +
                        "<p class=\"card-text mt-2\">Nota: " +  m.getRating() + " - Ano: " + m.getYear() +" </p>" +
                        "</div>" +
                        "</div>");
            }
            outputWriter.write("</body>");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
