package com.apartment;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Test {

    public static void main(String args[]) throws UnsupportedEncodingException {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);

        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);

        // parses all rows in one go.
        List<String[]> allRows = parser.parseAll(getReader("C:\\Users\\jupalsr\\Downloads\\Units.csv"));
        System.out.print(allRows);
    }
    public static Reader getReader(String relativePath) throws UnsupportedEncodingException {
        return new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(relativePath), "UTF-8");
    }
}
