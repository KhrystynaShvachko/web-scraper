package com.webcraper.scraper;

import com.webcraper.model.Quote;
import com.webcraper.util.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class QuotesScraper {

    public void scrapeHtmlPages() {

        List<Quote> quotes = new ArrayList<>();

        String url = "https://quotes.toscrape.com/";

        while (url != null) {

            try {
                Document doc = Jsoup.connect(url).get();

                Elements items = doc.select(".quote");

                for (Element item : items) {

                    String text = item.select(".text").text();
                    String author = item.select(".author").text();

                    Elements tagElements = item.select(".tags .tag");

                    List<String> tags = new ArrayList<>();
                    for (Element t : tagElements) {
                        tags.add(t.text());
                    }

                    quotes.add(new Quote(text, author, tags));
                }

                Element next = doc.selectFirst(".next a");
                url = (next != null)
                        ? "https://quotes.toscrape.com" + next.attr("href")
                        : null;

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("Found " + quotes.size() + " quotes.");
        JsonWriter.writeToFile("output/quotes-html.json", quotes);
    }
}