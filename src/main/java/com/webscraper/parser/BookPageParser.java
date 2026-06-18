package com.webscraper.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.webscraper.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookPageParser {

    public List<Book> parse(Document doc) {

        List<Book> books = new ArrayList<>();

        Elements items = doc.select("article.product_pod");

        for (Element item : items) {

            String title = item.select("h3 a").attr("title");

            String priceText =
                    item.select(".price_color").text();

            double price =
                    Double.parseDouble(
                            priceText.replaceAll("[^0-9.]", "")
                    );

            int rating = parseRating(item.select("p.star-rating").attr("class"));

            String relativeUrl = item.select("h3 a").attr("href");

            String detailUrl = buildDetailUrl(relativeUrl);

            Book book = new Book(
                    title,
                    price,
                    null,
                    0,
                    rating,
                    detailUrl,
                    null
            );

            books.add(book);
        }

        return books;
    }

    private String buildDetailUrl(String relative) {
        return "https://books.toscrape.com/catalogue/"
                + relative.replace("../", "");
    }

    private int parseRating(String className) {
        if (className.contains("One")) return 1;
        if (className.contains("Two")) return 2;
        if (className.contains("Three")) return 3;
        if (className.contains("Four")) return 4;
        if (className.contains("Five")) return 5;
        return 0;
    }
}
