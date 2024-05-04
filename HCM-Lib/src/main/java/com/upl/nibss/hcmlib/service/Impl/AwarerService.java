package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.model.Awarers;
import com.upl.nibss.hcmlib.repo.AwarerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 24/11/2017.
 */
@Service
public class AwarerService {

    @Autowired
    private AwarerRepo awarerRepo;

    public List<Awarers> save(List<Awarers> awarers){
        return awarerRepo.save(awarers);
    }
}
