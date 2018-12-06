package com.apartment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String block;
    private String floor;
    private String flatNo;
    private Integer sqft;
    private Integer parkingSlots;
    private Integer parkingSqft;
    private String billerName;
    private String unitCategory;
    @OneToOne(mappedBy = "unit")
    private Member member;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public Integer getSqft() {
        return sqft;
    }

    public void setSqft(Integer sqft) {
        this.sqft = sqft;
    }

    public Integer getParkingSlots() {
        return parkingSlots;
    }

    public void setParkingSlots(Integer parkingSlots) {
        this.parkingSlots = parkingSlots;
    }

    public Integer getParkingSqft() {
        return parkingSqft;
    }

    public void setParkingSqft(Integer parkingSqft) {
        this.parkingSqft = parkingSqft;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getUnitCategory() {
        return unitCategory;
    }

    public void setUnitCategory(String unitCategory) {
        this.unitCategory = unitCategory;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }


}
