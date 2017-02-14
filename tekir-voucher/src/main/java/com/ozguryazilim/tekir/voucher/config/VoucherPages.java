/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher.config;

import com.ozguryazilim.tekir.core.config.ParamNavigationSection;
import com.ozguryazilim.tekir.core.config.SalesNavigationSection;
import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.nav.Navigation;
import com.ozguryazilim.telve.view.PageTitle;
import com.ozguryazilim.telve.view.Pages;
import javax.enterprise.context.ApplicationScoped;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

/**
 *
 * @author oyas
 */
@ApplicationScoped
@Folder(name = "./voucher")
public interface VoucherPages extends Pages {	
	
	@View
	@SecuredPage("process")
	@PageTitle("module.caption.ProcessBrowse")
	@Navigation(label = "module.caption.ProcessBrowse", icon = "flaticon-speech-bubble", section = ParamNavigationSection.class)
	class ProcessBrowse implements VoucherPages {
	}
    
    @SecuredPage() @View
    class ProcessLookup implements VoucherPages {}
    
    @SecuredPage() @View
    class VoucherGroupLookup implements VoucherPages {}
    
    @SecuredPage() @View
    class VoucherMatchableLookup implements VoucherPages {}
    
    @SecuredPage("voucherSerial") @View
    @Navigation(label = "module.caption.VoucherSerial", icon = "fa fa-book", section = ParamNavigationSection.class)
    class VoucherSerial implements VoucherPages {}
}
