package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 20/11/2017.
 */
@Transactional
@Repository
public interface BankRepo extends JpaRepository<Bank, Long> {

    @Query("select b.name from Bank b where b.deleted = false ")
    List<String> getBankNames();
}
