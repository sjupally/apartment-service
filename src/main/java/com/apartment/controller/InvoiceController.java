package com.apartment.controller;


import com.apartment.entity.Invoice;
import com.apartment.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    public static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @PostMapping("/unit/{unitId}/invoice")
    public ResponseEntity<?> createInvoice(@PathVariable(value = "unitId") Long unitId,
                                           @Valid @RequestBody Invoice invoice) {
        logger.info("InvoiceController :: Creating Invoice : {}", invoice);
        try {
            invoiceService.save(invoice, unitId);
        } catch (Exception e) {
            logger.error("InvoiceController :: Error While Creating Invoice :: ", e);
            return new ResponseEntity<String>("Internel Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Invoice Added Successfully", HttpStatus.CREATED);
    }

}
