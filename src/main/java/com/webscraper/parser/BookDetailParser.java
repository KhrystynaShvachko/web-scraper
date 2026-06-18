package com.webscraper.parser;

import com.webscraper.model.Book;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDetailParser {

    public Book enrich(Book book, Document detailDoc) {

        Element table = detailDoc.selectFirst("table.table-striped");

        String upc = "";
        String availabilityText = "";

        for (Element row : table.select("tr")) {

            String header = row.select("th").text();
            String value = row.select("td").text();

            if ("UPC".equals(header)) {
                upc = value;
            }

            if ("Availability".equals(header)) {
                availabilityText = value;
            }
        }

        String availability =
                availabilityText.contains("In stock")
                        ? "In stock"
                        : "Out of stock";

        int quantity = 0;

        Pattern pattern =
                Pattern.compile("\\((\\d+) available\\)");

        Matcher matcher =
                pattern.matcher(availabilityText);

        if (matcher.find()) {
            quantity =
                    Integer.parseInt(matcher.group(1));
        }

        book.setUpc(upc);
        book.setAvailability(availability);
        book.setAvailableQuantity(quantity);

        return book;
    }
}