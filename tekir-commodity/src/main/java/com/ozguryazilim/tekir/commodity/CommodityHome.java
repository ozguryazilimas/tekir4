package com.ozguryazilim.tekir.commodity;

import com.ozguryazilim.tekir.core.currency.CurrencyService;
import com.ozguryazilim.telve.forms.FormEdit;
import com.ozguryazilim.telve.forms.FormBase;
import com.ozguryazilim.tekir.entities.Commodity;
import com.ozguryazilim.telve.data.RepositoryBase;
import com.ozguryazilim.telve.unit.UnitName;
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
        
        
}