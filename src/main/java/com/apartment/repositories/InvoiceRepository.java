package com.apartment.repositories;

import com.apartment.entity.Invoice;
import com.apartment.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
