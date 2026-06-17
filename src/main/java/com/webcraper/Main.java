package com.webcraper;

import com.webcraper.scraper.BooksScraper;

public class Main {
    public static void main(String[] args) {

        System.out.println("Scraping fiction books...");
        new BooksScraper().scrape();
    }
}