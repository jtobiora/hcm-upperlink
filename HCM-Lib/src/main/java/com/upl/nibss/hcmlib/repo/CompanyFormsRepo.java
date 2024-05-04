package com.upl.nibss.hcmlib.repo;

import com.upl.nibss.hcmlib.embeddables.Document;
import com.upl.nibss.hcmlib.enums.PortalModuleName;
import com.upl.nibss.hcmlib.model.CompanyForms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by toyin.oladele on 19/11/2017.
 */
@Transactional
@Repository
public interface CompanyFormsRepo extends JpaRepository<CompanyForms, Long> {

    @Query("select c from CompanyForms c ")
    List<CompanyForms> getAllAjax();

    @Query("select c from CompanyForms c where c.formId = :id")
    CompanyForms getAjax(@Param("id") Long id);

    @Query("select c from CompanyForms c where c.formId = :id and c.formCategory.name = :portalModuleName")
    CompanyForms getAjaxByPortalName(@Param("id") Long id, @Param("portalModuleName") PortalModuleName portalModuleName);

    @Query("select c from CompanyForms c where c.formId = :id and c.activated = true ")
    CompanyForms getAjaxAndActivated(@Param("id") Long id);

    @Query("select c from CompanyForms c where c.formId = :id and c.formCategory.name = :portalModuleName and c.activated = true ")
    CompanyForms getAjaxByPortalNameAndActivated(@Param("id") Long id, @Param("portalModuleName") PortalModuleName portalModuleName);

    @Query("select c.companyDoc from CompanyForms c where c.formId = :id")
    Document getCompanyFormDoc(@Param("id") Long id);
}
