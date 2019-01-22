package com.apartment;

import com.apartment.entity.Invoice;
import com.apartment.entity.Receipt;
import com.apartment.util.InstrumentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TestReceipt {
    public static void main(String args[]) throws UnsupportedEncodingException {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/invoice/8/receipt/");
            ObjectMapper mapper = new ObjectMapper();
            Receipt receipt = new Receipt();
            receipt.setComment("Paid Maintenance");
            receipt.setInstrumentBank("ICICI");
            receipt.setInstrumentNumber("123345JDJ");
            receipt.setInstrumentType(InstrumentType.CHEQUE);
            receipt.setPostedOn(new Date());
            receipt.setPostedBy("SomeX");

            String input = mapper.writeValueAsString(receipt);
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
