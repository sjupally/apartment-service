package com.apartment.service;

import com.apartment.entity.Unit;
import com.apartment.repositories.UnitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogService.class);

    @Autowired
    private UnitRepository unitRepository;

    public Unit save(Unit unit) {
        LOGGER.info("UnitService:::save:::Method Start End");
        return unitRepository.save(unit);
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
}
