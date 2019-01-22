package com.apartment.repositories;

import com.apartment.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query(value = "select distinct block from Unit order by block asc", nativeQuery = true)
    List<String> getUnitBlocks();

    @Query(value = "select distinct floor from Unit order by floor asc", nativeQuery = true)
    List<String> getUnitFloors();

    List<Unit> findByFloor(String floorNo);
}
