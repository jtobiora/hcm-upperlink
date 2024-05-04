package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Bank;
import com.upl.nibss.hcmlib.repo.BankRepo;
import com.upl.nibss.hcmlib.service.interfaces.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Service
public class BankService implements IBankService {

    @Autowired
    private BankRepo bankRepo;

    @Override
    public List<Bank> getAll() throws Exception{
        return bankRepo.findAll();
    }

    @Override
    public List<String> getAllBankNames() throws Exception {
        return bankRepo.getBankNames();
    }

    @Override
    public Bank get(Long id) throws Exception{
        return bankRepo.findOne(id);
    }



}
