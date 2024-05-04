package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.PortalModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by toyin.oladele on 14/11/2017.
 */
@Repository
@Transactional
public interface PortalModuleRepo extends JpaRepository<PortalModule, Long> {

    @Query("select p from PortalModule p where p.name = :portalModuleName")
    PortalModule findByPortalmoduleName(@Param("portalModuleName")PortalModuleName portalModuleName);
}
