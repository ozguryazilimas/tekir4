package com.ozguryazilim.tekir.hr.employee.leave;

import com.ozguryazilim.tekir.entities.EmployeeLeave;
import com.ozguryazilim.tekir.entities.VoucherBase_;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.feature.search.AbstractFeatureSearchHandler;
import com.ozguryazilim.telve.feature.search.FeatureSearchResult;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author oyas
 */
@Dependent
public class EmployeeLeaveHandler extends AbstractFeatureSearchHandler{

    @Inject
    private EmployeeLeaveRepository repository;

    @Inject
    private Identity indentity;

    @Override
    public List<FeatureSearchResult> search(String searchText, Map<String,Object> params ) {

        QueryDefinition<EmployeeLeave, EmployeeLeaveViewModel> query = new QueryDefinition<>();

        query.setSearchText(searchText);


        if (params.get("MINE_ONLY") != null) {
            Boolean b = (Boolean) params.get("MINE_ONLY");
            if (b) {
                StringFilter sf = new StringFilter<>(VoucherBase_.owner, "voucher.label.Owner");
                sf.setOperand(FilterOperand.Equal);
                sf.setValue(indentity.getLoginName());
                query.addFilter(sf);
            }
        }

        if (params.get("ACTIVES") != null) {
            Boolean b = (Boolean) params.get("ACTIVES");
            if (b) {
                VoucherStateTypeFilter filter = new VoucherStateTypeFilter<>(VoucherBase_.state, "general.label.state");
                filter.setOperand(FilterOperand.NotEqual);
                filter.setValue(VoucherStateType.CLOSE);
                query.addFilter(filter);
            }
        }

        List<FeatureSearchResult> result = new ArrayList<>();
        for( EmployeeLeaveViewModel eml : repository.browseQuery(query) ){
            FeatureSearchResult sr = new FeatureSearchResult(
                    EmployeeLeaveFeature.class.getSimpleName(),
                    eml.getVoucherNo(),
                    eml.getId(),
                    eml.getTopic(),
                    eml.getInfo()
            );

            result.add(sr);
        }

        return result;
    }

}
