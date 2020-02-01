package com.tensorflow.model.server.Utility;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class CsvReader {

    public static <T> List<T> readCsv(Class<T> type, InputStream stream) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(type).withHeader();
            ObjectReader reader = mapper.readerFor(type).with(schema);
            return reader.<T>readValues(stream).readAll();
        } catch (IOException e) {
            System.out.println("Error: IO Exception");
            return Collections.emptyList();
        }
    }
}
