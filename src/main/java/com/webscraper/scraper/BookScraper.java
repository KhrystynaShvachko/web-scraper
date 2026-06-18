package com.webscraper.scraper;

import com.webscraper.model.Book;
import com.webscraper.parser.BookDetailParser;
import com.webscraper.parser.BookPageParser;
import com.webscraper.service.HttpClientService;
import com.webscraper.util.JsonWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class BookScraper {

    private static final String BASE_URL =
            "https://books.toscrape.com/catalogue/category/books/fiction_10/";

    private final HttpClientService httpClientService;
    private final BookPageParser pageParser;
    private final BookDetailParser detailParser;

    public BookScraper(
            HttpClientService httpClientService,
            BookPageParser pageParser,
            BookDetailParser detailParser
    ) {
        this.httpClientService = httpClientService;
        this.pageParser = pageParser;
        this.detailParser = detailParser;
    }

    public void scrape() {

        List<Book> books = new ArrayList<>();

        String url = BASE_URL + "index.html";

        try {

            while (url != null) {

                String html = httpClientService.get(url);

                Document doc = Jsoup.parse(html);

                List<Book> pageBooks = pageParser.parse(doc);

                for (Book book : pageBooks) {

                    String detailHtml = httpClientService.get(book.getDetailUrl());

                    Document detailDoc = Jsoup.parse(detailHtml);

                    detailParser.enrich(book, detailDoc);

                    books.add(book);
                }

                var next = doc.selectFirst("li.next a");

                url = (next != null)
                        ? BASE_URL + next.attr("href")
                        : null;
            }

            System.out.println("Found " + books.size() + " books.");
            System.out.println("Writing output/books.json...");

            JsonWriter.writeToFile(
                    "output/books.json",
                    books
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
