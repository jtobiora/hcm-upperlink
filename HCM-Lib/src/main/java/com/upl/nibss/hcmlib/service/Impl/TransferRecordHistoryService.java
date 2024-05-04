package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.PromotionRecordHistory;
import com.upl.nibss.hcmlib.model.TransferRecord;
import com.upl.nibss.hcmlib.model.TransferRecordHistory;
import com.upl.nibss.hcmlib.repo.PromotionRecordHistoryRepo;
import com.upl.nibss.hcmlib.repo.TransferRecordHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 25/11/2017.
 */
@Service
public class TransferRecordHistoryService {

    @Autowired
    private TransferRecordHistoryRepo transferRecordHistoryRepo;

    public List<TransferRecordHistory> getAll() throws Exception{
        return transferRecordHistoryRepo.findAll();
    }

    public List<TransferRecordHistory> getAllByEmployeeId(Long employeeId) throws Exception{
        return transferRecordHistoryRepo.getAllByEmployee(employeeId);
    }

    public TransferRecordHistory get(Long id) throws Exception{
        return transferRecordHistoryRepo.findOne(id);
    }

    public TransferRecordHistory getByEmployeeIdAndId(Long employeeId, Long id) throws Exception{
        return transferRecordHistoryRepo.getTransferRecordHistoryByEmployeeAndId(employeeId,id);
    }

    public TransferRecordHistory save(TransferRecordHistory transferRecordHistory) throws Exception{
        return transferRecordHistoryRepo.save(transferRecordHistory);
    }

    public List<TransferRecordHistory> save(List<TransferRecordHistory> transferRecordHistories) throws Exception{
        return transferRecordHistoryRepo.save(transferRecordHistories);
    }

}
