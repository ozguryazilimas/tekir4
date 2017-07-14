package com.ozguryazilim.tekir.core.territory;

import com.ozguryazilim.tekir.core.location.LocationLookup;
import com.ozguryazilim.tekir.entities.Location;
import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.tekir.entities.TerritoryItem;
import com.ozguryazilim.telve.lookup.LookupSelectTuple;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 * Home Control Class
 * 
 * @author
 */
@ParamEdit
public class TerritoryHome extends ParamBase<Territory, Long> {

	@Inject
	private TerritoryRepository repository;
        
        @Inject
        private LocationLookup yerLookup;

	public TerritoryRepository getRepository() {
		return this.repository;
	}

	public void setRepository(final TerritoryRepository repository) {
		this.repository = repository;
	}       
        
        // Location fonksiyonları
        /**
         * Yer Seçim dialog sonucu çağrılır
         *
         * @param event
         */
        
        public void onLocationSelect(SelectEvent event) {
            LookupSelectTuple tuple = (LookupSelectTuple) event.getObject();
            
            List<Location> ls = new ArrayList<>();
            if(tuple != null){
                if(tuple.getValue() instanceof List) {
                    ls.addAll((List<Location>) tuple.getValue());
                } else {
                    ls.add((Location) tuple.getValue());
                }
                
                for(Location loc: ls) {
                    if (!isLocationAdded(loc)){
                        TerritoryItem ti = new TerritoryItem();
                        ti.setParent(getEntity());
                        ti.setLocation(loc);
                        getEntity().getItems().add(ti);
                    }
                }
            }
        }
        
        /**
         * Territory elemanları için seçili olan location'ları döndürür.
         * 
         * @return TerritoryItems'ların location bilgileri
         *         
        public List<Location> getItemsLocations() {
            return getEntity().getItems().stream().map(x -> x.getLocation()).collect(Collectors.toList());
        }
        
        */

        /**
         * Verilen yerin daha önce listeye eklenip eklenmediğine bakar.
         *
         * @param l
         * @return
         */
        protected boolean isLocationAdded(Location l) {
            return getEntity().getItems().stream().anyMatch((ti) -> (ti.getLocation().equals(l)));
        }

        /**
         * Removes territory item line
         *
         * @param index
         */
        public void removeLocation(int index) {
            getEntity().getItems().remove(index);
        }
         
}