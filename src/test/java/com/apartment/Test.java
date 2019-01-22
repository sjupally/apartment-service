package com.apartment;

import com.apartment.entity.Unit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.*;
import java.net.URL;
import java.util.List;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Test {

    public static void main(String args[]) throws UnsupportedEncodingException {
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);

        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);

        // parses all rows in one go.
        Test test = new Test();
        List<String[]> allRows = parser.parseAll(test.getReader("/Units.csv"));
        System.out.print(allRows);
        for (String[] s : allRows) {
            Unit unit = new Unit();
            unit.setBlock(s[0]);
            unit.setFloor(s[1]);
            unit.setFlatNo(s[2]);
            unit.setSqft(Integer.valueOf(s[3]));
            unit.setParkingSlots(Integer.valueOf(s[4]));
            unit.setParkingSqft(Integer.valueOf(s[5]));
            unit.setBillerName(s[6]);
            unit.setUnitCategory(s[7]);
            try {

                Client client = Client.create();

                WebResource webResource = client
                        .resource("http://localhost:8080/v1/unit/");
                ObjectMapper mapper = new ObjectMapper();

                String input = mapper.writeValueAsString(unit);
                ClientResponse response = webResource.type("application/json")
                        .post(ClientResponse.class, input);

                if (response.getStatus() != 201) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                System.out.println("Output from Server .... \n");
                String output = response.getEntity(String.class);
                System.out.println(output);

            } catch (Exception e) {

                e.printStackTrace();

            }
        }


    }

    public static Reader getReader(String relativePath) throws UnsupportedEncodingException {
        try {
            //URL url = Test.class.getResource("application.properties");
            //InputStream strm = url.openStream();
            return new InputStreamReader(Test.class.getResourceAsStream(relativePath), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to read input", e);
        }
    }
}
