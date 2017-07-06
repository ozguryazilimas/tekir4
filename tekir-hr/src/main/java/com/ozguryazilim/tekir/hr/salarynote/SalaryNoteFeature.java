/**
 * 
 */
package com.ozguryazilim.tekir.hr.salarynote;

import com.ozguryazilim.tekir.entities.SalaryNote;
import com.ozguryazilim.tekir.hr.config.EmployeePages;
import com.ozguryazilim.telve.feature.AbstractFeatureHandler;
import com.ozguryazilim.telve.feature.Feature;
import com.ozguryazilim.telve.feature.Page;
import com.ozguryazilim.telve.feature.PageType;
import com.ozguryazilim.telve.feature.search.Search;

/**
 * @author oktay
 *
 */
@Feature(permission = "salaryNote", forEntity = SalaryNote.class )
@Page( type = PageType.BROWSE, page = EmployeePages.SalaryNoteBrowse.class )
@Page( type = PageType.EDIT, page = EmployeePages.SalaryNote.class )
public class SalaryNoteFeature extends AbstractFeatureHandler{
}
