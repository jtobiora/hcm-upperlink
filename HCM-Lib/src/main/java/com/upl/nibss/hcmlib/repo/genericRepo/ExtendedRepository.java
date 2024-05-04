package com.upl.nibss.hcmlib.repo.genericRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stanlee on 12/03/2018.
 */
@NoRepositoryBean
public interface ExtendedRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findByAttributeContainsText(String attributeName, String text);
}