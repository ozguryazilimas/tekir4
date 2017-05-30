/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir.core.feature;

import com.ozguryazilim.tekir.core.config.CorePages;
import com.ozguryazilim.telve.feature.search.AbtsractFeatureLookup;
import com.ozguryazilim.telve.lookup.Lookup;
import java.util.Map;

/**
 * FeatureLookup için Tekir'e özgü filtre genişlemesi.
 * 
 * @author Hakan Uygun
 */
@Lookup(dialogPage = CorePages.Core.TekirFeatureLookup.class)
public class TekirFeatureLookup extends AbtsractFeatureLookup{
    
    private Boolean mineOnly = Boolean.TRUE;
    private Boolean actives = Boolean.TRUE;

    @Override
    protected void initModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Boolean getMineOnly() {
        return mineOnly;
    }

    public void setMineOnly(Boolean mineOnly) {
        this.mineOnly = mineOnly;
    }

    public Boolean getActives() {
        return actives;
    }

    public void setActives(Boolean actives) {
        this.actives = actives;
    }

    @Override
    protected void decorateParams(Map<String, Object> params) {
        params.put("MINE_ONLY", this.mineOnly);
        params.put("ACTIVES", this.actives);
    }
    
    
}
