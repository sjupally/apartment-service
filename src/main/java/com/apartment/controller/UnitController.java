package com.apartment.controller;

import com.apartment.entity.Unit;
import com.apartment.service.UnitService;
import com.apartment.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
public class UnitController {

    @Autowired
    private UnitService unitService;
    public static final Logger logger = LoggerFactory.getLogger(UnitController.class);


    @RequestMapping(value = "/unit/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Unit unit) {
        logger.info("UnitController :: Creating Unit : {}", unit);
        try {
            unitService.save(unit);
        } catch (Exception e) {
            logger.error("UnitController :: Error While Creating Unit :: ", e);
            return new ResponseEntity<String>("Internel Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Unit Added Successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/unit/", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> listAll() {
        logger.info("UnitController :: Getting All Units ");
        List<Unit> units = unitService.findAllUnits();
        if (units.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Unit>>(units, HttpStatus.OK);
    }

    @RequestMapping(value = "/unitsByFloor", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUnitsByFloor(@QueryParam("floorNo") String floorNo) {
        logger.info("Fetching Units with floorNo {}", floorNo);
        List<Unit> units = unitService.getUnitsByFloor(floorNo);
        if (units.isEmpty()) {
            logger.error("Units with floorNo {} not found.", floorNo);
            return new ResponseEntity(new CustomErrorType("Units with floorNo {} " + floorNo
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Unit>>(units, HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUnit(@PathVariable("id") long id) {
        logger.info("Fetching Unit with id {}", id);
        Unit unit = unitService.findById(id);
        if (unit == null) {
            logger.error("Unit with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unit with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Unit>(unit, HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUnit(@PathVariable("id") long id, @RequestBody Unit unit) {
        logger.info("Updating Unit with id {}", id);

        Unit currentUnit = unitService.findById(id);

        if (currentUnit == null) {
            logger.error("Unable to update. Unit with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Unit with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentUnit.setBillerName(unit.getBillerName());
        currentUnit.setBlock(unit.getBlock());
        currentUnit.setFlatNo(unit.getFlatNo());
        currentUnit.setFloor(unit.getFloor());
        currentUnit.setParkingSlots(unit.getParkingSlots());
        currentUnit.setParkingSqft(unit.getParkingSqft());
        currentUnit.setSqft(unit.getSqft());
        currentUnit.setUnitCategory(unit.getUnitCategory());

        unitService.update(currentUnit);

        return new ResponseEntity<Unit>(currentUnit, HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/blocks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUnitBlocks() {
        logger.info("UnitController :: Getting All Unit Blocks ");
        List<String> blocks = unitService.getUnitBlocks();
        if (blocks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<String>>(blocks, HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/floors", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUnitFloors() {
        logger.info("UnitController :: Getting All Unit Floors ");
        List<String> floors = unitService.getUnitFloors();
        if (floors.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<String>>(floors, HttpStatus.OK);
    }
}
