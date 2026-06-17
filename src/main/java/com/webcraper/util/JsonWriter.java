package com.webcraper.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonWriter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeToFile(String path, Object data) {
        try {
            File file = new File(path);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, data);

        } catch (Exception e) {
            throw new RuntimeException("Error writing JSON to file: " + path, e);
        }
    }
}