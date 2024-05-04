package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Allowance;
import com.upl.nibss.hcmlib.model.LoanRecord;
import com.upl.nibss.hcmlib.repo.AllowanceRepo;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 23/11/2017.
 */
@Service
public class AllowanceService {

    @Autowired
    private AllowanceRepo allowanceRepo;

    public List<Allowance> getAll() throws Exception{
        return allowanceRepo.findAll();
    }

    public List<Allowance> getAllUndeleted() throws Exception{
        return allowanceRepo.findAllUndeleted();
    }

    public List<Allowance> getAllByIds(List<Long> ids) throws Exception{
        return allowanceRepo.findAllByIds(ids);
    }

    public Allowance get(Long id) throws Exception{
        return allowanceRepo.findOne(id);
    }

    public Allowance getByName(String name){
        if (name == null){
            return null;
        }
        return allowanceRepo.getAllowanceByName(name);
    }

    public Allowance getByNameAndNotId(String name, Long id){
        if (name == null || id == null){
            return null;
        }
        return allowanceRepo.getAllowanceByNameAndNotId(name, id);
    }

    public Allowance save(Allowance allowance) throws Exception {
        return allowanceRepo.save(allowance);
    }

    public List<Allowance> getAllActivated() throws Exception{
        return allowanceRepo.findAllowanceByActivated();
    }

    public List<Allowance> getAllActivatedByIds(List<Long> ids) throws Exception{
        return allowanceRepo.findAllActivatedByIds(ids);
    }

    public boolean toggle(Long id) throws  Exception{
        int affectedRows = allowanceRepo.toggle(id);

        if (affectedRows > 0){
            return true;
        }

        return false;
    }
}
