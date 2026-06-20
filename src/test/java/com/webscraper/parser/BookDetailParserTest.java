package com.webscraper.parser;

import com.webscraper.model.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDetailParserTest {

    @Test
    void shouldExtractBookDetails() {

        String html = "<table class='table-striped'>"
                + "<tr><th>UPC</th><td>a12345</td></tr>"
                + "<tr><th>Availability</th><td>In stock (22 available)</td></tr>"
                + "</table>";

        Document document = Jsoup.parse(html);

        Book book = new Book();

        BookDetailParser parser = new BookDetailParser();

        parser.enrich(book, document);

        assertEquals("a12345", book.getUpc());

        assertEquals("In stock", book.getAvailability());

        assertEquals(22, book.getAvailableQuantity());
    }
}