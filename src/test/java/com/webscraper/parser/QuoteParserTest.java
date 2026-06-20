package com.webscraper.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webscraper.model.Quote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuoteParserTest {

    @Test
    void shouldParseQuoteFromJson() throws Exception {

        String json = "{\n"
                + "  \"text\":\"Test quote\",\n"
                + "  \"author\":{\n"
                + "     \"name\":\"Albert Einstein\"\n"
                + "  },\n"
                + "  \"tags\":[\"life\",\"truth\"]\n"
                + "}";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(json);

        QuoteParser parser = new QuoteParser();

        Quote quote = parser.parse(node);

        assertEquals("Test quote", quote.getText());

        assertEquals("Albert Einstein", quote.getAuthor());

        assertEquals(2, quote.getTags().size());

        assertTrue(quote.getTags().contains("life"));

        assertTrue(quote.getTags().contains("truth"));
    }
}