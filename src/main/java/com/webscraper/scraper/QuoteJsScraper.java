package com.webscraper.scraper;

import com.webscraper.model.Quote;
import com.webscraper.parser.QuoteJsPageParser;
import com.webscraper.service.HttpClientService;
import com.webscraper.util.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class QuoteJsScraper {

    private static final String BASE_URL =
            "https://quotes.toscrape.com";

    private final HttpClientService httpClientService;
    private final QuoteJsPageParser parser;

    public QuoteJsScraper(
            HttpClientService httpClientService,
            QuoteJsPageParser parser
    ) {
        this.httpClientService = httpClientService;
        this.parser = parser;
    }

    public void scrape() {

        List<Quote> quotes =
                new ArrayList<>();

        String url =
                BASE_URL + "/js/";

        try {

            while (url != null) {

                String html =
                        httpClientService.get(url);

                Document document =
                        Jsoup.parse(html);

                quotes.addAll(
                        parser.parse(document)
                );

                Element next =
                        document.selectFirst(
                                "li.next a"
                        );

                if (next != null) {

                    url =
                            BASE_URL
                                    + next.attr("href");

                } else {
                    url = null;
                }
            }

            System.out.println(
                    "Found "
                            + quotes.size()
                            + " quotes."
            );

            System.out.println(
                    "Writing output/quotes-js.json..."
            );

            JsonWriter.writeToFile(
                    "output/quotes-js.json",
                    quotes
            );

        } catch (Exception e) {

            System.err.println(
                    "JS scraper failed"
            );

            e.printStackTrace();
        }
    }
}