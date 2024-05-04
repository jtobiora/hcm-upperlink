package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.LoanType;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
public interface ILoanTypeService {

    List<LoanType> getAll() throws Exception;

    LoanType get(Long id) throws Exception;

    List<LoanType> getAllActivated() throws Exception;

    List<String> getAllLoanTypeNamesByActivated() throws Exception;

    LoanType getActivated(Long id) throws Exception;

    int countOfSimilarName(String loanType) throws Exception;

    int countOfSimilarNameNotId(String loanType, Long id) throws Exception;

    LoanType save(LoanType loanType) throws Exception;

    boolean toggle(Long id) throws Exception;
}
