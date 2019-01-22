package com.apartment.entity;

import com.apartment.util.InstrumentType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private InstrumentType instrumentType;
    private String instrumentNumber;
    private String instrumentBank;
    private String comment;
    private Date postedOn;
    private String postedBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }

    public String getInstrumentBank() {
        return instrumentBank;
    }

    public void setInstrumentBank(String instrumentBank) {
        this.instrumentBank = instrumentBank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", instrumentType=" + instrumentType +
                ", instrumentNumber='" + instrumentNumber + '\'' +
                ", instrumentBank='" + instrumentBank + '\'' +
                ", comment='" + comment + '\'' +
                ", postedOn=" + postedOn +
                ", postedBy='" + postedBy + '\'' +
                ", invoice=" + invoice +
                '}';
    }
}
