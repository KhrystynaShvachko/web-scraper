package com.webscraper.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webscraper.model.Quote;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class QuoteJsPageParser {

    private final ObjectMapper mapper = new ObjectMapper();
    private final QuoteParser quoteParser;

    public QuoteJsPageParser(QuoteParser quoteParser) {
        this.quoteParser = quoteParser;
    }

    public List<Quote> parse(Document document) throws Exception {

        List<Quote> quotes = new ArrayList<>();

        String scriptContent = null;

        for (Element script : document.select("script")) {

            String data = script.data();

            if (data.contains("var data =")) {
                scriptContent = data;
                break;
            }
        }

        if (scriptContent == null) {
            return quotes;
        }

        int start = scriptContent.indexOf("[");
        int end = scriptContent.indexOf("];") + 1;

        String jsonArray =
                scriptContent.substring(start, end);

        JsonNode root =
                mapper.readTree(jsonArray);

        for (JsonNode node : root) {
            quotes.add(
                    quoteParser.parse(node)
            );
        }

        return quotes;
    }
}