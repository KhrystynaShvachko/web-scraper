package com.webcraper.scraper;

import com.webcraper.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.webcraper.util.JsonWriter;

import java.util.ArrayList;
import java.util.List;

public class BooksScraper {

    private static final String BASE_URL =
            "https://books.toscrape.com/catalogue/category/books/fiction_10/";

    public void scrape() {

        List<Book> books = new ArrayList<>();

        String url = BASE_URL + "index.html";

        while (url != null) {

            try {
                Document doc = Jsoup.connect(url).get();

                Elements items = doc.select("article.product_pod");

                for (Element item : items) {

                    String title = item.select("h3 a").attr("title");

                    String priceText = item.select(".price_color").text();
                    double price = Double.parseDouble(
                            priceText.replace("£", "")
                    );

                    int rating = parseRating(
                            item.select("p.star-rating").attr("class")
                    );

                    String relativeDetailUrl = item.select("h3 a").attr("href");
                    String detailUrl = buildDetailUrl(relativeDetailUrl);

                    Document detailDoc = Jsoup.connect(detailUrl).get();

                    Element table = detailDoc.select("table.table-striped").first();

                    String upc = table.select("tr").get(0).select("td").text();

                    String availabilityText = table.select("tr").get(5).select("td").text();

                    String availability = availabilityText.contains("In stock")
                            ? "In stock"
                            : "Out of stock";

                    int quantity = 0;
                    if (availabilityText.contains("(")) {
                        String inside = availabilityText.substring(
                                availabilityText.indexOf("(") + 1,
                                availabilityText.indexOf(" available")
                        );
                        quantity = Integer.parseInt(inside);
                    }

                    books.add(new Book(
                            title,
                            price,
                            availability,
                            quantity,
                            rating,
                            detailUrl,
                            upc
                    ));
                }

                Element next = doc.selectFirst("li.next a");
                if (next != null) {
                    url = BASE_URL + next.attr("href");
                } else {
                    url = null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        System.out.println("Found " + books.size() + " books.");
        JsonWriter.writeToFile("output/books.json", books);
        System.out.println("Writing output/books.json...");
    }

    private String buildDetailUrl(String relative) {
        return "https://books.toscrape.com/catalogue/" +
                relative.replace("../", "");
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
