package com.apartment;

import com.apartment.entity.Member;
import com.apartment.entity.Unit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class TestMember {

    public static void main(String args[]) throws UnsupportedEncodingException {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/member/");
            ObjectMapper mapper = new ObjectMapper();
            Member member = new Member();
            member.setFirstname("Srikanth");
            member.setLastname("Jupally");
            member.setBlock("3rd");
            member.setFloor("3");
            member.setFlatNo("3rd");
            member.setIsdCode("+91");
            member.setContactNumber("9533783774");
            member.setIntercom("198");
            member.setOwnerShip("O");
            member.setEmail("srikanth.j825@gmail.com");

            String input = mapper.writeValueAsString(member);
            System.out.print(input);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Reader getReader(String relativePath) throws UnsupportedEncodingException {
        try {
            //URL url = Test.class.getResource("application.properties");
            //InputStream strm = url.openStream();
            return new InputStreamReader(TestMember.class.getResourceAsStream(relativePath), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to read input", e);
        }
    }
}
