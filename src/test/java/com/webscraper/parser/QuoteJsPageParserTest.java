package com.webscraper.parser;

import com.webscraper.model.Quote;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuoteJsPageParserTest {


    private final QuoteParser quoteParser = new QuoteParser();

    private final QuoteJsPageParser parser = new QuoteJsPageParser(quoteParser);


    @Test
    void shouldParseQuotesFromJavaScriptJson() throws Exception {

        String html = """
                <html>
                <body>

                <script>
                    var data = [
                        {
                            "text": "The world as we have created it",
                            "author": {
                                "name": "Albert Einstein"
                            },
                            "tags": [
                                "world",
                                "change"
                            ]
                        },
                        {
                            "text": "Life is beautiful",
                            "author": {
                                "name": "Unknown"
                            },
                            "tags": [
                                "life"
                            ]
                        }
                    ];
                </script>

                </body>
                </html>
                """;


        Document document = Jsoup.parse(html);

        List<Quote> quotes = parser.parse(document);


        assertEquals(2, quotes.size());

        assertEquals("The world as we have created it", quotes.get(0).getText());

        assertEquals("Albert Einstein", quotes.get(0).getAuthor());


        assertEquals("Life is beautiful", quotes.get(1).getText());

        assertEquals("Unknown", quotes.get(1).getAuthor());
    }


    @Test
    void shouldReturnEmptyListWhenScriptWithJsonDoesNotExist() throws Exception {


        String html = """
                <html>
                <body>
                    <h1>No quotes</h1>
                </body>
                </html>
                """;


        Document document = Jsoup.parse(html);


        List<Quote> quotes = parser.parse(document);


        assertTrue(quotes.isEmpty());
    }


    @Test
    void shouldParseEmptyJsonArray() throws Exception {


        String html = """
                <script>
                    var data = [];
                </script>
                """;


        Document document = Jsoup.parse(html);


        List<Quote> quotes = parser.parse(document);


        assertNotNull(quotes);
        assertTrue(quotes.isEmpty());
    }
}