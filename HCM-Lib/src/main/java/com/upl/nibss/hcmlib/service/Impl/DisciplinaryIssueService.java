package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.DisciplinaryIssue;
import com.upl.nibss.hcmlib.repo.DisciplinaryIssueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 08/12/2017.
 */
@Service
public class DisciplinaryIssueService {

    @Autowired
    private DisciplinaryIssueRepo disciplinaryIssueRepo;

    public List<DisciplinaryIssue> getAll() throws Exception{
        return disciplinaryIssueRepo.findAll();
    }

    public List<DisciplinaryIssue> getAllByEmployee(Long employeeId) throws Exception{
        return disciplinaryIssueRepo.findByEmployee(employeeId);
    }

    public DisciplinaryIssue get(Long id) throws Exception{
        return disciplinaryIssueRepo.findOne(id);
    }

    public DisciplinaryIssue save(DisciplinaryIssue disciplinaryIssue) throws Exception{
        return disciplinaryIssueRepo.save(disciplinaryIssue);
    }

    public DisciplinaryIssue getByIdAndEmployeeId(Long id, Long employeeId) throws Exception{
        return disciplinaryIssueRepo.findOneByEmployee(id,employeeId);
    }
}
