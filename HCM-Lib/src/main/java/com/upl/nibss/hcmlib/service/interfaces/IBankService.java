package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.model.Bank;

import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
public interface IBankService {

    List<Bank> getAll() throws Exception;

    List<String> getAllBankNames() throws Exception;

    Bank get(Long id) throws Exception;
}
