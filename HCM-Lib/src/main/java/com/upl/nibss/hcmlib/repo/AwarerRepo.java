package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Awarers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by toyin.oladele on 24/11/2017.
 */
@Transactional
@Repository
public interface AwarerRepo extends JpaRepository<Awarers,Long> {
}
