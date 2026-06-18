package com.webscraper.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.webscraper.model.Quote;
import com.webscraper.parser.QuoteParser;
import com.webscraper.service.HttpClientService;
import com.webscraper.service.JsonParserService;
import com.webscraper.util.JsonWriter;

import java.util.ArrayList;
import java.util.List;


public class QuoteScrollScraper {

    private static final String BASE_URL =
            "https://quotes.toscrape.com/api/quotes?page=";

    private final HttpClientService httpClientService;
    private final JsonParserService jsonParserService;
    private final QuoteParser parser;

    public QuoteScrollScraper(
            HttpClientService httpClientService,
            JsonParserService jsonParserService,
            QuoteParser parser
    ) {
        this.httpClientService = httpClientService;
        this.jsonParserService = jsonParserService;
        this.parser = parser;
    }

    public void scrape() {

        List<Quote> quotes = new ArrayList<>();

        int page = 1;
        boolean hasNext = true;

        try {

            while (hasNext) {

                String json = httpClientService.get(BASE_URL + page);

                JsonNode root = jsonParserService.parse(json);

                JsonNode results = root.get("quotes");

                for (JsonNode q : results) {
                    quotes.add(parser.parse(q));
                }

                hasNext = root.get("has_next").asBoolean();
                page++;
            }

            System.out.println("Found " + quotes.size() + " quotes.");
            System.out.println("Writing output/quotes-scroll.json...");

            JsonWriter.writeToFile(
                    "output/quotes-scroll.json",
                    quotes
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
