package com.apartment;

import com.apartment.entity.Invoice;
import com.apartment.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TestInvoice {
    public static void main(String args[]) throws UnsupportedEncodingException {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8090/unit/1/invoice/");
            ObjectMapper mapper = new ObjectMapper();
            Invoice invoice = new Invoice();
            invoice.setInvoiceDate(new Date());
            invoice.setDueDate(new Date());
            invoice.setInvoiceAmount(123.00);
            invoice.setAccountId(123L);
            invoice.setComment("Nov Month Maintanance 2018");

            String input = mapper.writeValueAsString(invoice);
            System.out.print(input);
            ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            System.out.println("Output from Server .... \n"+response);
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
