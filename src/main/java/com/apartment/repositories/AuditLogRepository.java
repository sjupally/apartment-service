package com.apartment.repositories;

import com.apartment.entity.AuditLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditLogRepository extends CrudRepository<AuditLog, Long> {

}
