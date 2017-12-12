/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.hr.credit;

import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.tekir.entities.EmployeeCreditNote;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.tekir.voucher.Voucher;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;

import javax.enterprise.inject.Default;

/**
 *
 * @author Erdem Uslu
 */
@Feature(permission = "employeeCreditNote", forEntity = EmployeeCreditNote.class )
@Page( type = PageType.BROWSE, page = EmployeePages.EmployeeCreditNoteBrowse.class )
@Page( type = PageType.VIEW, page = EmployeePages.EmployeeCreditNoteView.class )
@Page( type = PageType.MASTER_VIEW, page = EmployeePages.EmployeeCreditNoteMasterView.class )
@Page( type = PageType.EDIT, page = EmployeePages.EmployeeCreditNote.class )
@Search(handler = EmployeeCreditNoteSearchHandler.class )
@Voucher @Default
public class EmployeeCreditNoteFeature extends AbstractFeatureHandler{
    
}
