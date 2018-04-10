package com.ozguryazilim.tekir.core.territory;

import com.ozguryazilim.tekir.core.code.AutoCode;
import com.ozguryazilim.tekir.core.code.AutoCodeService;

import com.ozguryazilim.tekir.entities.Location;

import com.ozguryazilim.telve.forms.ParamEdit;
import com.ozguryazilim.telve.forms.ParamBase;
import com.ozguryazilim.tekir.entities.Territory;
import com.ozguryazilim.tekir.entities.TerritoryItem;
import com.ozguryazilim.telve.lookup.LookupSelectTuple;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 * Home Control Class
 *
 * @author
 */
@ParamEdit
@AutoCode(caption = "module.caption.Territory", size = 3)
public class TerritoryHome extends ParamBase<Territory, Long> {

    @Inject
    private TerritoryRepository repository;

    @Inject
    private AutoCodeService codeService;

    @Override
    protected Territory getNewEntity() {

        Territory result = new Territory();
        result.setCode(codeService.getNewSerialNumber(TerritoryHome.class.getSimpleName()));

        return result;
    }

    @Override
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
        if (tuple != null) {
            if (tuple.getValue() instanceof List) {
                ls.addAll((List<Location>) tuple.getValue());
            } else {
                ls.add((Location) tuple.getValue());
            }

            for (Location loc : ls) {
                if (!isLocationAdded(loc)) {
                    TerritoryItem ti = new TerritoryItem();
                    ti.setParent(getEntity());
                    ti.setLocation(loc);
                    getEntity().getItems().add(ti);
                }
            }
        }
    }

    /**
     * Verilen location için hiyerarşik path oluşturur.
     *
     * @param loc Ağaç şeklinde biçimlendirilecek location
     * @return Ağaç şeklinde biçimlendirilmiş location path'i
     */
    public String locationTree(Location loc) {

        String path = loc.getName();

        while (loc.getParent() != null) {
            loc = loc.getParent();
            path = loc.getName() + " ⇒ " + path;
        }

        return path;
    }

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
