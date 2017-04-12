/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.account.virement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.AccountVirement;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.feature.search.AbstractFeatureSearchHandler;
import com.ozguryazilim.telve.feature.search.FeatureSearchResult;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;

/**
 *
 * @author oyas
 */
@Dependent
public class AccountVirementSearchHandler extends AbstractFeatureSearchHandler{

    @Inject
    private AccountVirementRepository repository;
    
    @Inject
    private Identity indentity;
    
    @Override
    public List<FeatureSearchResult> search(String searchText, Map<String,Object> params ) {
        
        QueryDefinition<AccountVirement, AccountVirementViewModel> query = new QueryDefinition<>();
        
        query.setSearchText(searchText);
        
        
        if( params.get("MINE_ONLY") != null ){
            Boolean b = (Boolean) params.get("MINE_ONLY");
            if( b ){
                StringFilter sf = new StringFilter<>(VoucherBase_.owner, "voucher.label.Owner");
                sf.setOperand(FilterOperand.Equal);
                sf.setValue(indentity.getLoginName());
                query.addFilter( sf );
            }
        }
        
        if( params.get("ACTIVES") != null ){
            Boolean b = (Boolean) params.get("ACTIVES");
            if( b ){
                VoucherStateTypeFilter filter = new VoucherStateTypeFilter<>(VoucherBase_.state, "general.label.state");
                filter.setOperand(FilterOperand.NotEqual);
                filter.setValue(VoucherStateType.CLOSE);
                query.addFilter( filter );
            }
        }
        
        List<FeatureSearchResult> result = new ArrayList<>();
        for( AccountVirementViewModel o : repository.browseQuery(query) ){
            FeatureSearchResult sr = new FeatureSearchResult(
            		AccountVirementFeature.class.getSimpleName(),
                    o.getVoucherNo(),
                    o.getId(),
                    o.getTopic(),
                    o.getInfo()
            );
            
            result.add(sr);
        }
        
        return result;
    }
    
}
