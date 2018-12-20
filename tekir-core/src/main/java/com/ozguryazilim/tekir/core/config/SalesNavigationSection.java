package com.ozguryazilim.tekir.core.config;

import com.ozguryazilim.telve.nav.AbstractNavigationSection;

/**
 * Satış işlemleri için menu grubu
 * @author Hakan Uygun
 */
public class SalesNavigationSection extends AbstractNavigationSection{

    @Override
    public Integer getOrder() {
        return 20;
    }
    
}
