package com.upl.nibss.hcmlib.service.interfaces;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.CompanyForms;

import java.util.List;

/**
 * Created by toyin.oladele on 19/11/2017.
 */
public interface ICompanyFormService {

    List<CompanyForms> getAllAjax() throws Exception;

    List<CompanyForms> getAll() throws Exception;

    CompanyForms getAjax(Long id) throws Exception;

    CompanyForms getAjaxByPortalModule(Long id, PortalModuleName portalModuleName) throws Exception;

    CompanyForms get(Long id) throws Exception;

    CompanyForms save(CompanyForms companyForms) throws Exception;

    Document getDoc(Long id) throws Exception;

    Boolean toggle(Long id) throws Exception;

}
