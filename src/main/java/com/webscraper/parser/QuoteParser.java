package com.webscraper.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.webscraper.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class QuoteParser {

    public Quote parse(JsonNode node) {

        String text = node.get("text").asText();

        String author = node.get("author").get("name").asText();

        List<String> tags = new ArrayList<>();
        for (JsonNode tag : node.get("tags")) {
            tags.add(tag.asText());
        }

        return new Quote(text, author, tags);
    }
}
