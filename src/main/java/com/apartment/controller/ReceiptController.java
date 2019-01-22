package com.apartment.controller;


import com.apartment.entity.Receipt;
import com.apartment.service.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;
    public static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    @PostMapping("/invoice/{invoiceId}/receipt")
    public ResponseEntity<?> createInvoice(@PathVariable(value = "invoiceId") Long invoiceId,
                                           @Valid @RequestBody Receipt receipt) {
        logger.info("ReceiptController :: Creating Receipt : {}", receipt);
        try {
            receiptService.save(receipt, invoiceId);
        } catch (Exception e) {
            logger.error("ReceiptController :: Error While Creating Receipt :: ", e);
            return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Receipt Added Successfully", HttpStatus.CREATED);
    }

}
