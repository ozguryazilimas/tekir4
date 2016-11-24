/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.voucher;

import java.io.Serializable;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

/**
 *
 * @author oyas
 */
@GroupedConversationScoped
@Named
public class VoucherReleatedWidget implements Serializable{
    
    private String filter = "ALL";

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
    
}
