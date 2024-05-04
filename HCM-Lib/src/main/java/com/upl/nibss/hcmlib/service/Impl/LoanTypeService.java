package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.LoanType;
import com.upl.nibss.hcmlib.repo.LoanTypeRepo;
import com.upl.nibss.hcmlib.service.interfaces.ILoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Service
public class LoanTypeService implements ILoanTypeService {

    @Autowired
    private LoanTypeRepo loanTypeRepo;

    @Override
    public List<LoanType> getAll() throws Exception{
        return loanTypeRepo.findAll();
    }

    @Override
    public LoanType get(Long id) throws Exception {
        return loanTypeRepo.findOne(id);
    }

    @Override
    public List<LoanType> getAllActivated() throws Exception {
        return loanTypeRepo.findAllByActivated();
    }

    @Override
    public List<String> getAllLoanTypeNamesByActivated() throws Exception {
        return loanTypeRepo.findAllLoanNamesByActivated();
    }

    @Override
    public LoanType getActivated(Long id) throws Exception {
        return loanTypeRepo.findByActivated(id);
    }

    @Override
    public int countOfSimilarName(String loanType) throws Exception {
        return loanTypeRepo.getCountOfSimilarName(loanType);
    }

    @Override
    public int countOfSimilarNameNotId(String loanType, Long id) throws Exception {
        return loanTypeRepo.getCountOfSimilarNameNotId(loanType,id);
    }

    @Override
    public LoanType save(LoanType loanType) throws Exception {
        return loanTypeRepo.save(loanType);
    }

    @Override
    public boolean toggle(Long id) throws Exception {
        int affectedRows = loanTypeRepo.toggleStatus(id);
        return affectedRows > 0;
    }
}
