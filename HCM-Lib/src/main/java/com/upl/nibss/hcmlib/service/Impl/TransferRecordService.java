package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.enums.ApprovalStatus;
import com.upl.nibss.hcmlib.model.PromotionRecord;
import com.upl.nibss.hcmlib.model.TransferRecord;
import com.upl.nibss.hcmlib.repo.PromotionRecordRepo;
import com.upl.nibss.hcmlib.repo.TransferRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by toyin.oladele on 25/11/2017.
 */
@Service
public class TransferRecordService {

    @Autowired
    private TransferRecordRepo transferRecordRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TransferRecord> getAll() throws Exception{
        return transferRecordRepo.findAll();
    }

    public List<TransferRecord> getAllByEmployeeId(Long employeeId) throws Exception{
        return transferRecordRepo.findAllByEmployeeAndDeleted(employeeId);
    }

    public TransferRecord get(Long id)throws Exception{
        return transferRecordRepo.findOne(id);
    }

    public TransferRecord save(TransferRecord transferRecord)throws Exception{
        return transferRecordRepo.save(transferRecord);
    }

    public List<TransferRecord> save(List<TransferRecord> transferRecord)throws Exception{
        return transferRecordRepo.save(transferRecord);
    }

    public void delete(Long id) throws Exception{
        transferRecordRepo.delete(id);
    }

    public void delete(List<Long> ids) throws Exception{
        transferRecordRepo.deleteByIds(ids);
    }

    public void deleteByObject(List<TransferRecord> transferRecords){

        List<Long> ids = new ArrayList<>();
        transferRecords.forEach(transferRecord -> ids.add(transferRecord.getId()));
        if (!ids.isEmpty()){
            transferRecordRepo.deleteByIds(ids);
        }
    }

    public List<TransferRecord> getTransferRecordByEffectiveDateAndAndApprovedApprovalStatus(Date date){
        return transferRecordRepo.getTransferRecordByEffectiveDateAndAndApprovedApprovalStatus(date);
    }

    public List<TransferRecord> findAllByEmployeeAndApprovalStatus(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return transferRecordRepo.findAllByEmployeeAndApprovalStatus(employeeId, approvalStatus);
    }

    public List<TransferRecord> getTransferApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return transferRecordRepo.getTransferApprovals(employeeId, approvalStatus.name());
    }

    public List<TransferRecord> getTransferApplicationBaseOnMyApprovals(Long employeeId, ApprovalStatus approvalStatus) throws Exception{
        return transferRecordRepo.getTransferApplicationBaseOnMyApprovals(employeeId, approvalStatus.name());
    }

    public List<TransferRecord> getReport() throws Exception{
        return jdbcTemplate.query("select t.* from transfer_record t UNION ALL " +
                "SELECT th.* from training_record_history th", new BeanPropertyRowMapper<>(TransferRecord.class));
    }
}
