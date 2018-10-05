package com.apartment.service;

import com.apartment.entity.AuditLog;
import com.apartment.repositories.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuditLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogService.class);

    @Autowired
    private AuditLogRepository auditLogRepository;

    public AuditLog save(AuditLog auditLog) {
        LOGGER.info("AuditLogService:::save:::Method Start End");
        return auditLogRepository.save(auditLog);
    }
}
