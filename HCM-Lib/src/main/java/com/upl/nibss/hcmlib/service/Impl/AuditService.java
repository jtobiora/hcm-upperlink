package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Audits;
import com.upl.nibss.hcmlib.repo.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditRepo auditRepo;

    public Audits save(Audits audits){
        return auditRepo.save(audits);
    }

    public void delete(Long id){
        auditRepo.delete(id);
    }

    public Audits getAudit(Long id){
        return auditRepo.findOne(id);
    }

    public List<Audits> getAudits(){
        return auditRepo.findAll();
    }

    public List<Audits> findAuditByUsername(String username){
        return auditRepo.findByUsername(username);
    }

    public List<Audits> findAuditByDateRange(Date startDate, Date endDate){
        return auditRepo.findByDateRange(startDate, endDate);
    }

    public List<Audits> findAuditByusernameAndDateRange(String username, Date startDate, Date endDate){
        return auditRepo.findByUsernameAndDateRange(username, startDate, endDate);
    }
}
