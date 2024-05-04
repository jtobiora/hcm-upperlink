package com.upl.nibss.hcmlib.service.Impl;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.CompanyForms;
import com.upl.nibss.hcmlib.repo.CompanyFormsRepo;
import com.upl.nibss.hcmlib.service.interfaces.ICompanyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by toyin.oladele on 19/11/2017.
 */
@Service
public class CompanyFormService implements ICompanyFormService{

    @Autowired
    private CompanyFormsRepo companyFormsRepo;

    @Override
    public List<CompanyForms> getAllAjax() throws Exception {
        return companyFormsRepo.getAllAjax();
    }

    @Override
    public List<CompanyForms> getAll() throws Exception {
        return companyFormsRepo.findAll();
    }

    @Override
    public CompanyForms getAjax(Long id) throws Exception {
        return companyFormsRepo.getAjax(id);
    }

    @Override
    public CompanyForms getAjaxByPortalModule(Long id, PortalModuleName portalModuleName) throws Exception {
        return companyFormsRepo.getAjaxByPortalName(id, portalModuleName);
    }

    @Override
    public CompanyForms get(Long id) throws Exception {
        return companyFormsRepo.findOne(id);
    }

    @Override
    public CompanyForms save(CompanyForms companyForms) throws Exception {
        return companyFormsRepo.save(companyForms);
    }

    @Override
    public Document getDoc(Long id) throws Exception {
        return companyFormsRepo.getCompanyFormDoc(id);
    }

    @Override
    public Boolean toggle(Long id) throws Exception {

        CompanyForms companyForm = companyFormsRepo.findOne(id);
        if(companyForm != null) {
            companyForm.setActivated(!companyForm.isActivated());
            this.companyFormsRepo.save(companyForm);
            return true;
        }

        return false;
    }
}
