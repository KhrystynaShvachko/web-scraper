package com.webscraper.parser;

import com.webscraper.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookPageParserTest {

    @Test
    void shouldParseBookFromHtml() {

        String html =
                "<article class='product_pod'>" +
                        "<p class='star-rating Three'></p>" +
                        "<h3>" +
                        "<a title='Test Book' href='../test-book/index.html'>Book</a>" +
                        "</h3>" +
                        "<p class='price_color'>£51.77</p>" +
                        "</article>";

        Document document = Jsoup.parse(html);

        BookPageParser parser = new BookPageParser();

        List<Book> books = parser.parse(document);

        assertEquals(1, books.size());

        Book book = books.get(0);

        assertEquals("Test Book", book.getTitle());
        assertEquals(51.77, book.getPrice());
        assertEquals(3, book.getRating());

        assertTrue(
                book.getDetailUrl().contains("test-book/index.html")
        );
    }
}