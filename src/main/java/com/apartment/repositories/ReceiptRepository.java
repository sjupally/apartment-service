package com.apartment.repositories;

import com.apartment.entity.Invoice;
import com.apartment.entity.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

}
