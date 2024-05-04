package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 30/10/2017.
 */
@Repository
@Transactional
public interface SettingRepo extends JpaRepository<Settings, Long>{

    @Query("select s.value from Settings s where s.name = :name")
    String findValueByName(@Param("name") String name);

    @Query("select s from Settings s where s.name = :name")
    Settings findByName(@Param("name") String name);

    @Query("select s from Settings s where s.name in :names")
    List<Settings> findByNames(@Param("names") List<String> names);

}
