package model.services;

import model.entities.Content;

import java.util.List;

public interface JsonParser {
    List<? extends Content> parse();
}
