package com.apartment.service;

import com.apartment.entity.Invoice;
import com.apartment.entity.Receipt;
import com.apartment.entity.Unit;
import com.apartment.exception.ResourceNotFoundException;
import com.apartment.repositories.InvoiceRepository;
import com.apartment.repositories.ReceiptRepository;
import com.apartment.repositories.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReceiptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptService.class);

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    public Receipt save(Receipt receipt, Long invoiceId) {
        LOGGER.info("ReceiptService:::save:::Method Start End");
        return invoiceRepository.findById(invoiceId).map(invoice -> {
            receipt.setInvoice(invoice);
            return receiptRepository.save(receipt);
        }).orElseThrow(() -> new ResourceNotFoundException("invoiceId " + invoiceId + " not found"));
    }

    /*public List<Unit> findAllUnits() {
        LOGGER.info("UnitService:::findAllUnits:::Method Start End");
        return (List<Unit>) unitRepository.findAll();
    }

    public Unit findById(long id) {
        LOGGER.info("UnitService:::findById:::Method Start End");
        return unitRepository.findById(id).get();
    }

    public void update(Unit currentUnit) {
        LOGGER.info("UnitService:::update:::Method Start End");
        unitRepository.save(currentUnit);
    }

    public List<String> getUnitBlocks() {
        LOGGER.info("UnitService:::getUnitBlocks:::Method Start End");
        return unitRepository.getUnitBlocks();
    }

    public List<String> getUnitFloors() {
        LOGGER.info("UnitService:::getUnitFloors:::Method Start End");
        return unitRepository.getUnitFloors();
    }*/
}


