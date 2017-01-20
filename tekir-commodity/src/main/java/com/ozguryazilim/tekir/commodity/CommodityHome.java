package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.tekir.entities.TaxDefinition;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.entities.FeaturePointer;
import com.ozguryazilim.telve.unit.UnitName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@FormEdit( feature = CommodityFeature.class )
public class CommodityHome extends FormBase<Commodity, Long> {

	@Inject
	private CurrencyService currencyService;

	@Inject
	private CommodityRepository repository;

	private List<TaxDefinition> taxList;

	@Override
	public void createNew() {
		super.createNew(); 
		getEntity().setDefaultCurrency(currencyService.getDefaultCurrency());
	}
		
	@Override
	protected RepositoryBase<Commodity, CommodityViewModel> getRepository() {
		return repository;
	}

	public void onUnitSetChange(){
		getEntity().setUnitName( new UnitName( getEntity().getUnitSet().getCode(), getEntity().getUnitSet().getBaseUnit()) );
	}

	public List<TaxDefinition> getTaxList() {		
		buildTaxList();
		return taxList;
	}

	public void setTaxList(List<TaxDefinition> taxList) {
		this.taxList = taxList;
	}

	public FeaturePointer getFeaturePointer(){
		FeaturePointer result = new FeaturePointer();
		result.setBusinessKey(getEntity().getName());
		result.setFeature(getFeatureClass().getSimpleName());
		result.setPrimaryKey(getEntity().getId());
		return result;
	}

	private void buildTaxList(){

		taxList = new ArrayList<TaxDefinition>();

		if(getEntity().getTax1() != null){
			taxList.add(getEntity().getTax1());
		}

		if(getEntity().getTax2() != null){
			taxList.add(getEntity().getTax2());
		}

		if(getEntity().getTax3() != null){
			taxList.add(getEntity().getTax3());
		}
	}
}