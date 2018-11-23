package com.apartment.service;

import com.apartment.entity.Invoice;
import com.apartment.entity.Unit;
import com.apartment.exception.ResourceNotFoundException;
import com.apartment.repositories.InvoiceRepository;
import com.apartment.repositories.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice save(Invoice invoice, Long unitId) {
        LOGGER.info("InvoiceService:::save:::Method Start End");
        return unitRepository.findById(unitId).map(unit -> {
            invoice.setUnit(unit);
            return invoiceRepository.save(invoice);
        }).orElseThrow(() -> new ResourceNotFoundException("UnitId " + unitId + " not found"));
    }

    public List<Unit> findAllUnits() {
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
    }
}


