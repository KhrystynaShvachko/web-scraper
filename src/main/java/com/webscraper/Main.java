package com.webscraper;

import com.webscraper.parser.BookDetailParser;
import com.webscraper.parser.BookPageParser;
import com.webscraper.parser.QuoteJsPageParser;
import com.webscraper.parser.QuoteParser;
import com.webscraper.scraper.BookScraper;
import com.webscraper.scraper.QuoteJsScraper;
import com.webscraper.scraper.QuoteScrollScraper;
import com.webscraper.service.HttpClientService;
import com.webscraper.service.JsonParserService;

public class Main {

    public static void main(String[] args) {

        HttpClientService http = new HttpClientService();

        BookPageParser pageParser = new BookPageParser();
        BookDetailParser detailParser = new BookDetailParser();

        JsonParserService jsonParser = new JsonParserService();
        QuoteParser quoteParser = new QuoteParser();
        QuoteJsPageParser jsParser = new QuoteJsPageParser(quoteParser);

        System.out.println("Scraping fiction books...");

        new BookScraper(
                http,
                pageParser,
                detailParser
        ).scrape();

        System.out.println();

        System.out.println("Scraping scroll quotes...");

        new QuoteScrollScraper(
                http,
                jsonParser,
                quoteParser
        ).scrape();

        System.out.println();

        System.out.println("Scraping JavaScript quotes...");

        new QuoteJsScraper(
                http,
                jsParser
        ).scrape();
    }
}