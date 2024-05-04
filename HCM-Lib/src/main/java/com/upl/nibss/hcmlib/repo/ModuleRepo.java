package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by toyin.oladele on 10/10/2017.
 */
@Repository
@Transactional
public interface ModuleRepo extends JpaRepository<Module, Long> {

}
