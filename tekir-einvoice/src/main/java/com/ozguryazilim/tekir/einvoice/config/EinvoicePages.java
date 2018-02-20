package com.ozguryazilim.tekir.einvoice.config;

import com.ozguryazilim.telve.auth.SecuredPage;
import com.ozguryazilim.telve.view.Pages;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Folder(name = "./einvoice")
public interface EinvoicePages extends Pages {

    @SecuredPage()
    @View
    class EinvoiceOptionPane implements EinvoicePages {}

}
