package com.webscraper.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserService {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonNode parse(String json) throws Exception {
        return mapper.readTree(json);
    }
}