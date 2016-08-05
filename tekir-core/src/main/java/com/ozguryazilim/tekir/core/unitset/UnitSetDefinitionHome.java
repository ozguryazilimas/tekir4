package com.ozguryazilim.tekir.core.unitset;

import com.google.common.base.Strings;
import com.ozguryazilim.tekir.entities.Quantity;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.UnitSetDefinition;
import com.ozguryazilim.tekir.entities.UnitSetItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class UnitSetDefinitionHome extends ParamBase<UnitSetDefinition, Long> {

	@Inject
	private UnitSetDefinitionRepository repository;

	public UnitSetDefinitionRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final UnitSetDefinitionRepository repository) {
		this.repository = repository;
	}
        
        public void addItem(){
            UnitSetItem usi = new UnitSetItem();
            usi.setMaster(getEntity());
            usi.setQuantity(new Quantity(BigDecimal.ZERO, getEntity().getBaseUnit()));
            getEntity().getItems().add(usi);
        }
        
        public void deleteItem(UnitSetItem item ){
            getEntity().getItems().remove(item);
        }
        
        /**
         * Geriye ilgili UnitSet içinde tanımlanmış olan birimlerin listesini döndürür.
         * @return 
         */
        public List<String> getUnitList(){
            List<String> result = new ArrayList<>();
            
            //Önce base uniti bir ekliyelim
            result.add( getEntity().getBaseUnit());
            
            //şimdi de diğer tanıtılanları ekliyelim. Ama boş olanı pass geçelim.
            getEntity().getItems().stream()
                    .filter(it -> !Strings.isNullOrEmpty(it.getName()))
                    .map(it -> it.getName())
                    .forEach( s -> result.add(s));
                    
            
            
            return result;
        }
}