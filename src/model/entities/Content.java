package model.entities;


public interface Content {
    String getTitle();

    String getUrlImage();

    String getRating();

    String getYear();

    int compareTo(Content other);
}
