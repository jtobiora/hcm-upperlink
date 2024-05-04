package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.PromotionRecord;
import com.upl.nibss.hcmlib.model.PromotionRecordHistory;
import com.upl.nibss.hcmlib.repo.PromotionRecordHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 23/11/2017.
 */
@Service
public class PromotionRecordHistoryService {

    @Autowired
    private PromotionRecordHistoryRepo promotionRecordHistoryRepo;

    public List<PromotionRecordHistory> getAll() throws Exception{
        return promotionRecordHistoryRepo.findAll();
    }

    public List<PromotionRecordHistory> getAllByEmployeeId(Long employeeId) throws Exception{
        return promotionRecordHistoryRepo.getAllByEmployee(employeeId);
    }

    public PromotionRecordHistory get(Long id) throws Exception{
        return promotionRecordHistoryRepo.findOne(id);
    }

    public PromotionRecordHistory getByEmployeeIdAndId(Long employeeId, Long id) throws Exception{
        return promotionRecordHistoryRepo.getPromotionRecordHistoryByEmployeeAndId(employeeId,id);
    }

    public PromotionRecordHistory save(PromotionRecordHistory promotionRecordHistory) throws Exception{
        return promotionRecordHistoryRepo.save(promotionRecordHistory);
    }

    public List<PromotionRecordHistory> save(List<PromotionRecordHistory> promotionRecordHistories) throws Exception{
        return promotionRecordHistoryRepo.save(promotionRecordHistories);
    }
}
