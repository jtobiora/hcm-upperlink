package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.PromotionRecord;
import com.upl.nibss.hcmlib.repo.PromotionRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PromotionRecordService {

    @Autowired
    private PromotionRecordRepo promotionRecordRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PromotionRecord> getAll() throws Exception{
        return promotionRecordRepo.findAll();
    }

    public List<PromotionRecord> getAllByEmployeeId(Long employeeId) throws Exception{
        return promotionRecordRepo.findAllByEmployeeAndDeleted(employeeId);
    }

    public PromotionRecord get(Long id)throws Exception{
        return promotionRecordRepo.findOne(id);
    }

    public PromotionRecord save(PromotionRecord promotionRecord)throws Exception{
        return promotionRecordRepo.save(promotionRecord);
    }

    public List<PromotionRecord> save(List<PromotionRecord> promotionRecord)throws Exception{
        return promotionRecordRepo.save(promotionRecord);
    }

    public void delete(Long id) throws Exception{
        promotionRecordRepo.delete(id);
    }

    public void delete(List<Long> ids) throws Exception{
        promotionRecordRepo.deleteByIds(ids);
    }

    public void deleteByObject(List<PromotionRecord> promotionRecords){
        List<Long> ids = new ArrayList<>();
        promotionRecords.forEach(promotionRecord -> ids.add(promotionRecord.getId()));
        if (!ids.isEmpty()){
            promotionRecordRepo.deleteByIds(ids);
        }
    }

    public List<Long> getAllIds() throws Exception{
        return promotionRecordRepo.findAllIds();
    }

    public List<PromotionRecord> getPromotionsByEffectiveDate(Date date) throws Exception{
        return promotionRecordRepo.getPromotionRecordsByEffectiveDate(date);
    }

    public List<PromotionRecord> getReport() throws Exception{
        return jdbcTemplate.query("select p.* from promotion_record p UNION ALL " +
                "SELECT ph.* from promotion_record_history ph", new BeanPropertyRowMapper<>(PromotionRecord.class));
    }

}
