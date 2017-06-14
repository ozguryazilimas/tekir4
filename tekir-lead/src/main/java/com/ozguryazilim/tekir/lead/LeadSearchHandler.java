package com.ozguryazilim.tekir.lead;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ozguryazilim.tekir.entities.Lead;
import com.ozguryazilim.tekir.entities.VoucherStateType;
import com.ozguryazilim.tekir.voucher.filter.VoucherStateTypeFilter;
import com.ozguryazilim.telve.auth.Identity;
import com.ozguryazilim.telve.feature.search.AbstractFeatureSearchHandler;
import com.ozguryazilim.telve.feature.search.FeatureSearchResult;
import com.ozguryazilim.telve.query.QueryDefinition;
import com.ozguryazilim.telve.query.filters.FilterOperand;
import com.ozguryazilim.telve.query.filters.StringFilter;

import com.ozguryazilim.tekir.entities.VoucherBase_;

@Dependent
public class LeadSearchHandler extends AbstractFeatureSearchHandler {

	@Inject
	private LeadRepository repository;

	@Inject
	private Identity identity;

	@Override
	public List<FeatureSearchResult> search(String searchText, Map<String, Object> params) {
		
		QueryDefinition<Lead, LeadViewModel> query = new QueryDefinition<>();

		query.setSearchText(searchText);

		if (params.get("MINE_ONLY") != null) {
			Boolean b = (Boolean) params.get("MINE_ONLY");
			if (b) {
				StringFilter filter = new StringFilter<>(VoucherBase_.owner, "voucher.label.Owner");
				filter.setOperand(FilterOperand.Equal);
				filter.setValue(identity.getLoginName());
				query.addFilter(filter);
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
		for (LeadViewModel l : repository.browseQuery(query)) {
			FeatureSearchResult sr = new FeatureSearchResult(LeadFeature.class.getSimpleName(), l.getVoucherNo(),
					l.getId(), l.getTopic(), l.getInfo());

			result.add(sr);
		}

		return result;
	}

}
