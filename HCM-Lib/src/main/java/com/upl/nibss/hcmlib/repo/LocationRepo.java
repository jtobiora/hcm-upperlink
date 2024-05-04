package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 05/11/2017.
 */
@Repository
@Transactional
public interface LocationRepo extends JpaRepository<Location, Long> {

    @Query("select l from Location l")
    List<Location> findAllAjaxLocation();

    @Query("select l from Location l where l.id = :id")
    Location findAjaxLocationById(@Param("id") Long id);

    @Query("select l.locationName from Location l")
    List<String> findLocationNames();
}
